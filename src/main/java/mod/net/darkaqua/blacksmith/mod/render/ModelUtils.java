package net.darkaqua.blacksmith.mod.render;

import com.google.gson.*;
import javafx.util.Pair;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.block.blockstate.IIBlockState;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.render.TextureLocation;
import net.darkaqua.blacksmith.api.render.model.*;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Log;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.util.Vector4d;
import net.darkaqua.blacksmith.mod.Blacksmith;
import net.darkaqua.blacksmith.mod.exceptions.BlacksmithInternalException;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cout970 on 16/11/2015.
 */
public class ModelUtils {

    public static RenderManager.RenderRegistrationData setupBlock(Block block, Item itemBlock, IBlockDefinition definition, String identifier) {
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null) {
            throw new BlacksmithInternalException();
        }
        RenderManager.RenderRegistrationData data = new RenderManager.RenderRegistrationData();
        String domain = Blacksmith.MOD_ID + "@" + mod.getModId().toLowerCase();

        File blockstatesFile = getFile(domain, "/blockstates/", identifier.toLowerCase() + ".json");

        IBlockRenderHandler handler = definition.getBlockRenderHandler();

        createIfNeededBlockState(blockstatesFile, handler.getBlockStateModels(), definition, domain);

        Map<IBlockState, ModelResourceLocation> stateMap = new HashMap<>();

        for (IBlockStateModel e : handler.getBlockStateModels()) {

            String stateName = e.getBlockStateName();
            if (handler.getBlockStateModels().size() == 1)
                stateName = "normal";

//            List<IBlockState> list = block.getBlockState().getValidStates();
//            for(IBlockState s : list){
//                stateMap.put(s, new ModelResourceLocation(domain+":models/block/"+identifier, "normal"));
//            }

            {
                if (e.getModelName() == null) {
                    Log.error("Trying to load a model for block: " + identifier + ", with null name");
                    continue;
                }
                //block
                ModelResourceLocation blockModel = new ModelResourceLocation(domain + ":" + e.getModelName().toLowerCase(), stateName);
                File blockModelFile = getFile(domain, "/models/block/", e.getModelName().toLowerCase() + ".json");

                createIfNeededBlockModel(blockModelFile, stateName, handler.getModel(e.getModelName()), definition);
                IIBlockState state = handler.getBlockState(e.getBlockStateName());
                stateMap.put(MCInterface.toIBlockState(state), blockModel);

                //item
                File itemModelFile = getFile(domain, "/models/item/", e.getModelName().toLowerCase()+".json");
                ModelResourceLocation itemModel = new ModelResourceLocation(domain + ":" + e.getModelName().toLowerCase(), "inventory");

                createIfNeededItemModel(itemModelFile, itemBlock, handler.getModel(e.getModelName()), definition, domain);
                data.addItem(itemBlock, 0, itemModel);
            }

            for (IBlockStateModel model : e.getAlternatives()) {
                if (model.getModelName() == null) {
                    Log.error("Trying to load a model for block: " + identifier + ", with null name");
                    continue;
                }
                ModelResourceLocation blockModel = new ModelResourceLocation(domain + ":" + model.getModelName().toLowerCase(), stateName);
                File blockModelFile = getFile(domain, "/models/block/", model.getModelName().toLowerCase() + ".json");

                createIfNeededBlockModel(blockModelFile, stateName, handler.getModel(model.getModelName()), definition);
                stateMap.put(MCInterface.toIBlockState(handler.getBlockState(e.getBlockStateName())), blockModel);
            }
        }
        data.addBlock(block, stateMap);


        return data;
    }

    private static void createIfNeededItemModel(File file, Item itemBlock, IBlockModel model, IBlockDefinition definition, String domain) {
        if (file.exists())
            return;

        try {

            FileWriter writer = new FileWriter(file);
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            JsonObject data = new JsonObject();
            String jsonText = null;

            if (model.getParent() != null)
                data.addProperty("parent", model.getParent().getModelName());

            if (!model.useAmbientOcclusion())
                data.addProperty("ambientocclusion", false);

            JsonObject textures = new JsonObject();
            List<Pair<String, TextureLocation>> texList = model.getTextures();
            if(texList != null && !texList.isEmpty()) {
                for (Pair<String, TextureLocation> e : texList) {
                    textures.addProperty(e.getKey(), e.getValue().toString());
                }
                data.add("textures", textures);
            }

            JsonObject obj1 = null;

            for(RenderPlace place : RenderPlace.values()) {
                Display disp = model.getDisplay(place);

                if (disp != null) {
                    if(obj1 == null)
                        obj1 = new JsonObject();
                    JsonObject obj = new JsonObject();

                    JsonArray rot = new JsonArray();
                    rot.add(new JsonPrimitive(disp.getRotation().getX()));
                    rot.add(new JsonPrimitive(disp.getRotation().getY()));
                    rot.add(new JsonPrimitive(disp.getRotation().getZ()));
                    obj.add("rotation", rot);

                    JsonArray trans = new JsonArray();
                    rot.add(new JsonPrimitive(disp.getTranslation().getX()));
                    rot.add(new JsonPrimitive(disp.getTranslation().getY()));
                    rot.add(new JsonPrimitive(disp.getTranslation().getZ()));
                    obj.add("translation", trans);

                    JsonArray scale = new JsonArray();
                    rot.add(new JsonPrimitive(disp.getScale().getX()));
                    rot.add(new JsonPrimitive(disp.getScale().getY()));
                    rot.add(new JsonPrimitive(disp.getScale().getZ()));
                    obj.add("scale", scale);

                    obj1.add(place.getPropertyName(), obj);
                }
            }
            if(obj1 != null)
                data.add("display", obj1);

            List<IModelElement> element = model.getElements();
            if(element != null && !element.isEmpty()){
                JsonArray elems = new JsonArray();
                for(IModelElement e : element){
                    JsonObject obj = new JsonObject();

                    Vector3i from = e.getStartPoint();
                    JsonArray first = new JsonArray();
                    first.add(new JsonPrimitive(from.getX()));
                    first.add(new JsonPrimitive(from.getY()));
                    first.add(new JsonPrimitive(from.getZ()));
                    obj.add("from", first);

                    Vector3i to = e.getEndPoint();
                    JsonArray end = new JsonArray();
                    end.add(new JsonPrimitive(to.getX()));
                    end.add(new JsonPrimitive(to.getY()));
                    end.add(new JsonPrimitive(to.getZ()));
                    obj.add("to", end);

                    IModelRotation rot = e.getRotation();
                    if(rot != null){

                        JsonObject rotation = new JsonObject();

                        Vector3i origin = rot.getOrigin();
                        JsonArray orig = new JsonArray();
                        orig.add(new JsonPrimitive(origin.getX()));
                        orig.add(new JsonPrimitive(origin.getY()));
                        orig.add(new JsonPrimitive(origin.getZ()));
                        rotation.add("origin", first);

                        rotation.addProperty("axis", rot.getAxis().name().toLowerCase());

                        rotation.addProperty("angler", rot.getAngle());

                        rotation.addProperty("rescale", rot.rescale());

                        obj.add("rotation", rotation);
                    }

                    obj.addProperty("shade", e.shouldRenderShadows());

                    JsonObject faces = null;

                    for(Direction dir : Direction.values()) {
                        IModelFace face = e.getFace(dir);

                        if(face != null){
                            if(faces == null)
                                faces = new JsonObject();
                            JsonObject jsonFace = new JsonObject();

                            JsonArray uv = new JsonArray();
                            Vector4d vec = face.getUV();
                            uv.add(new JsonPrimitive(vec.getX()));
                            uv.add(new JsonPrimitive(vec.getY()));
                            uv.add(new JsonPrimitive(vec.getZ()));
                            uv.add(new JsonPrimitive(vec.getW()));

                            jsonFace.add("uv", uv);

                            jsonFace.addProperty("texture", "#"+face.getTextureID());

                            if(face.getCullFace() != null & face.getCullFace() != dir)
                                jsonFace.addProperty("cullface", face.getCullFace().name().toLowerCase());

                            if(face.getTextureRotation() != 0)
                                jsonFace.addProperty("rotation", face.getTextureRotation());

                            faces.add(dir.name().toLowerCase(), jsonFace);
                        }
                    }

                    if(faces != null)
                        obj.add("faces", faces);

                    elems.add(obj);
                }
                data.add("elements", elems);
            }

            jsonText = g.toJson(data);
            writer.write(jsonText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createIfNeededBlockModel(File file, String stateName, IBlockModel model, IBlockDefinition definition) {
        if (file.exists())
            return;

        try {

            FileWriter writer = new FileWriter(file);
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            JsonObject data = new JsonObject();
            String jsonText = null;

            if (model.getParent() != null)
                data.addProperty("parent", model.getParent().getModelName());

            if (!model.useAmbientOcclusion())
                data.addProperty("ambientocclusion", false);

            JsonObject textures = new JsonObject();
            List<Pair<String, TextureLocation>> texList = model.getTextures();
            if(texList != null && !texList.isEmpty()) {
                for (Pair<String, TextureLocation> e : texList) {
                    textures.addProperty(e.getKey(), e.getValue().toString());
                }
                data.add("textures", textures);
            }

            JsonObject obj1 = null;

            for(RenderPlace place : RenderPlace.values()) {
                Display disp = model.getDisplay(place);

                if (disp != null) {
                    if(obj1 == null)
                        obj1 = new JsonObject();
                    JsonObject obj = new JsonObject();

                    JsonArray rot = new JsonArray();
                    rot.add(new JsonPrimitive(disp.getRotation().getX()));
                    rot.add(new JsonPrimitive(disp.getRotation().getY()));
                    rot.add(new JsonPrimitive(disp.getRotation().getZ()));
                    obj.add("rotation", rot);

                    JsonArray trans = new JsonArray();
                    rot.add(new JsonPrimitive(disp.getTranslation().getX()));
                    rot.add(new JsonPrimitive(disp.getTranslation().getY()));
                    rot.add(new JsonPrimitive(disp.getTranslation().getZ()));
                    obj.add("translation", trans);

                    JsonArray scale = new JsonArray();
                    rot.add(new JsonPrimitive(disp.getScale().getX()));
                    rot.add(new JsonPrimitive(disp.getScale().getY()));
                    rot.add(new JsonPrimitive(disp.getScale().getZ()));
                    obj.add("scale", scale);

                    obj1.add(place.getPropertyName(), obj);
                }
            }
            if(obj1 != null)
                data.add("display", obj1);

            List<IModelElement> element = model.getElements();
            if(element != null && !element.isEmpty()){
                JsonArray elems = new JsonArray();
                for(IModelElement e : element){
                    JsonObject obj = new JsonObject();

                    Vector3i from = e.getStartPoint();
                    JsonArray first = new JsonArray();
                    first.add(new JsonPrimitive(from.getX()));
                    first.add(new JsonPrimitive(from.getY()));
                    first.add(new JsonPrimitive(from.getZ()));
                    obj.add("from", first);

                    Vector3i to = e.getEndPoint();
                    JsonArray end = new JsonArray();
                    end.add(new JsonPrimitive(to.getX()));
                    end.add(new JsonPrimitive(to.getY()));
                    end.add(new JsonPrimitive(to.getZ()));
                    obj.add("to", end);

                    IModelRotation rot = e.getRotation();
                    if(rot != null){

                        JsonObject rotation = new JsonObject();

                        Vector3i origin = rot.getOrigin();
                        JsonArray orig = new JsonArray();
                        orig.add(new JsonPrimitive(origin.getX()));
                        orig.add(new JsonPrimitive(origin.getY()));
                        orig.add(new JsonPrimitive(origin.getZ()));
                        rotation.add("origin", first);

                        rotation.addProperty("axis", rot.getAxis().name().toLowerCase());

                        rotation.addProperty("angler", rot.getAngle());

                        rotation.addProperty("rescale", rot.rescale());

                        obj.add("rotation", rotation);
                    }

                    obj.addProperty("shade", e.shouldRenderShadows());

                    JsonObject faces = null;

                    for(Direction dir : Direction.values()) {
                        IModelFace face = e.getFace(dir);

                        if(face != null){
                            if(faces == null)
                                faces = new JsonObject();
                            JsonObject jsonFace = new JsonObject();

                            JsonArray uv = new JsonArray();
                            Vector4d vec = face.getUV();
                            uv.add(new JsonPrimitive(vec.getX()));
                            uv.add(new JsonPrimitive(vec.getY()));
                            uv.add(new JsonPrimitive(vec.getZ()));
                            uv.add(new JsonPrimitive(vec.getW()));

                            jsonFace.add("uv", uv);

                            jsonFace.addProperty("texture", "#"+face.getTextureID());

                            if(face.getCullFace() != null & face.getCullFace() != dir)
                                jsonFace.addProperty("cullface", face.getCullFace().name().toLowerCase());

                            if(face.getTextureRotation() != 0)
                                jsonFace.addProperty("rotation", face.getTextureRotation());

                            faces.add(dir.name().toLowerCase(), jsonFace);
                        }
                    }

                    if(faces != null)
                        obj.add("faces", faces);

                    elems.add(obj);
                }
                data.add("elements", elems);
            }

            jsonText = g.toJson(data);
            writer.write(jsonText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createIfNeededBlockState(File file, List<IBlockStateModel> map, IBlockDefinition definition, String domain) {
        if (file.exists())
            return;

        try {
            FileWriter writer = new FileWriter(file);
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            JsonObject data = new JsonObject();
            JsonObject variants = new JsonObject();
            String jsonText = null;

            for (IBlockStateModel variant : map) {
                JsonArray models = new JsonArray();
                {
                    JsonObject jsonObject = new JsonObject();

                    jsonObject.addProperty("model", domain+":"+variant.getModelName());
                    if (variant.getRotationX() != 0)
                        jsonObject.addProperty("x", variant.getRotationX());
                    if (variant.getRotationY() != 0)
                        jsonObject.addProperty("y", variant.getRotationY());
                    if (variant.useUVLock())
                        jsonObject.addProperty("uvlock", true);
                    if (variant.getWeight() != 1)
                        jsonObject.addProperty("weight", variant.getWeight());
                    models.add(jsonObject);
                }

                for (IBlockStateModel imodel : variant.getAlternatives()) {
                    JsonObject jsonObject = new JsonObject();

                    jsonObject.addProperty("model", imodel.getModelName());
                    if (imodel.getRotationX() != 0)
                        jsonObject.addProperty("x", imodel.getRotationX());
                    if (imodel.getRotationY() != 0)
                        jsonObject.addProperty("y", imodel.getRotationY());
                    if (imodel.useUVLock())
                        jsonObject.addProperty("uvlock", true);
                    if (imodel.getWeight() != 1)
                        jsonObject.addProperty("weight", imodel.getWeight());
                    models.add(jsonObject);
                }
                variants.add(map.size() == 1 ? "normal" : variant.getBlockStateName(), models);
            }

            data.add("variants", variants);

            jsonText = g.toJson(data);
            writer.write(jsonText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getFile(String domain, String path, String resource) {
        URL root = ModelUtils.class.getResource("/");
        File rootFile = null;
        if (root == null) {
            Log.error("Error searching the root folder for blacksmith assets");
            return null;
        } else {
            try {
                rootFile = new File(root.toURI());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        File assets = new File(rootFile, "assets/");
        if(!assets.exists())
            assets.mkdirs();

        File endFile = new File(assets, domain + "/" + path + "/");
        if (!endFile.exists()) {
            endFile.mkdirs();
        }

        return new File(endFile, resource);
    }

    public static File getFile(String domain, String path) {
        URL root = ModelUtils.class.getResource("/");
        File rootFile = null;
        if (root == null) {
            Log.error("Error searching the root folder for blacksmith assets");
            return null;
        } else {
            try {
                rootFile = new File(root.toURI());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        File assets = new File(rootFile, "assets/");
        if(!assets.exists())
            assets.mkdirs();
        return new File(assets, domain + "/" + path + "/");
    }
}
