package net.darkaqua.blacksmith.mod.render;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.darkaqua.blacksmith.mod.Blacksmith;
import net.darkaqua.blacksmith.mod.registry.BlockRegistry;
import net.darkaqua.blacksmith.mod.util.Log;
import net.minecraft.client.resources.model.ModelResourceLocation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by cout970 on 16/11/2015.
 */
public class JsonCreator {

    public static void createBlockStateJson(BlockRegistry.RegisteredBlock reg) {
        String domain = Blacksmith.MOD_ID;
        String identifier = reg.getIdentifier().toLowerCase();
        String path = "/blockstates/"+ identifier.substring(0, identifier.indexOf("/"));
        File blockStatesFile = getFile(domain, path, identifier.substring(identifier.indexOf("/")) + ".json");

        List<ModelResourceLocation> renderMap = reg.getJsonStates();
        createIfNeededBlockState(blockStatesFile, renderMap, domain);
    }

    private static void createIfNeededBlockState(File file, List<ModelResourceLocation> map, String domain) {
//        if (file.exists())
//            return;

        try {
            FileWriter writer = new FileWriter(file);
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            JsonObject data = new JsonObject();
            JsonObject variants = new JsonObject();
            String jsonText;

            for (ModelResourceLocation loc : map) {
                JsonArray models = new JsonArray();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("model", loc.getResourceDomain() + ":" + loc.getResourcePath());
                models.add(jsonObject);
                variants.add(loc.getVariant(), models);
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
        URL root = JsonCreator.class.getResource("/");
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
        if (!assets.exists())
            assets.mkdirs();

        File endFile = new File(assets, domain + "/" + path + "/");
        if (!endFile.exists()) {
            endFile.mkdirs();
        }

        return new File(endFile, resource);
    }

    public static File getFile(String domain, String path) {
        URL root = JsonCreator.class.getResource("/");
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
        if (!assets.exists())
            assets.mkdirs();
        return new File(assets, domain + "/" + path + "/");
    }

    //    public static RenderManager.RenderRegistrationData setupBlock(Block block, Item itemBlock, IBlockDefinition definition, String identifier) {
//        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
//        if (mod == null) {
//            throw new BlacksmithInternalException();
//        }
//        RenderManager.RenderRegistrationData data = new RenderManager.RenderRegistrationData();
//        String domain = Blacksmith.MOD_ID + "@" + mod.getModId().toLowerCase();
//
//        File blockstatesFile = getFile(domain, "/blockstates/", identifier.toLowerCase() + ".json");
//        Map<IBlockState, ModelResourceLocation> stateMap = new HashMap<>();
//        Map<IBlockState, ModelResourceLocation> modelMap = new HashMap<>();
//
//        ImmutableList<IBlockState> list = block.getBlockVariant().getValidStates();
//        for (IBlockState s : list) {
//            String state_name = list.size() == 1 ? "normal" : getStateName(s);
//            List<IJsonModelWrapper> variants = definition.getBlockRenderHandler().getBlockModelsForState(MCInterface.fromIBlockState(s));
//            for(IJsonModelWrapper wr : variants) {
//                IJsonModel model = wr.getModelIdentifier();
//                if (model == null) {
//                    Log.warn("Skipping block model for: "+s);
//                    continue;
//                }else if(model.getModelName() == null){
//                    Log.warn("Skipping block model: "+model+", invalid name (null)");
//                    continue;
//                }
//                modelMap.put(s, new ModelResourceLocation(domain + ":" + model.getModelName().toLowerCase(), state_name));
//                stateMap.put(s, new ModelResourceLocation(domain + ":" + identifier.toLowerCase(), state_name));
//                File blockmodelFile = getFile(domain, "/models/block/", model.getModelName().toLowerCase() + ".json");
//                createIfNeededBlockModel(blockmodelFile, model);
//            }
//        }
//        data.addBlock(block, stateMap);
//        createIfNeededBlockState(blockstatesFile, modelMap, definition, domain);
//
//
//        //item
//        List<IJsonModelWrapper> variants = definition.getBlockRenderHandler().getBlockModelsForState(MCInterface.fromIBlockState(block.getDefaultState()));
//        if(variants != null && !variants.isEmpty()) {
//            File itemModelFile = getFile(domain, "/models/item/", identifier.toLowerCase() + ".json");
//            ModelResourceLocation itemModel = new ModelResourceLocation(domain + ":" + identifier.toLowerCase(), "inventory");
//
//            createIfNeededItemBlockModel(itemModelFile, variants.get(0).getModelIdentifier());
//            ModelBakery.addVariantName(itemBlock, domain + ":" + identifier.toLowerCase());
//            ModelLoader.setCustomModelResourceLocation(itemBlock, 0, itemModel);
//        }
//
//        return data;
//    }

//    private static String getStateName(IBlockState s) {
//        Set<Map.Entry<IProperty, Comparable<?>>> properties = s.getPropertyMap().entrySet();
//        String name = "";
//        int index = 0;
//        for (Map.Entry<IProperty, Comparable<?>> prop : properties) {
//            name += prop.getKey().getName() + "=" + prop.getValue().toString();
//            if (index != properties.size() - 1)
//                name += ",";
//            index++;
//        }
//        return name;
//    }
//
//    private static void createIfNeededItemBlockModel(File file, IJsonModel model) {
////        if (file.exists())
////            return;
//
//        try {
//
//            FileWriter writer = new FileWriter(file);
//            Gson g = new GsonBuilder().setPrettyPrinting().newCreativeTab();
//            JsonObject data = new JsonObject();
//            String jsonText = null;
//
////            if (model.getParent() != null)
////                data.addProperty("parent", model.getParent().getModelName());
//
//            if (!model.useAmbientOcclusion())
//                data.addProperty("ambientocclusion", false);
//
//            JsonObject textures = new JsonObject();
//            List<Pair<String, ResourceReference>> texList = model.getTextures();
//            if (texList != null && !texList.isEmpty()) {
//                for (Pair<String, ResourceReference> e : texList) {
//                    textures.addProperty(e.getKey(), e.getValue().toString());
//                }
//                data.add("textures", textures);
//            }
//
//            JsonObject obj1 = null;
//
//            for (JsonRenderPlace place : JsonRenderPlace.values()) {
//                JsonRenderDisplay disp = model.getDisplay(place);
//
//                if (disp == null && place == JsonRenderPlace.THIRD_PERSON) {
//                    disp = new JsonRenderDisplay(new Vector3d(10, -45, 170), new Vector3d(0, 1.5, -2.75), new Vector3d(0.375, 0.375, 0.375));
//                }
//                if (disp != null) {
//                    if (obj1 == null)
//                        obj1 = new JsonObject();
//                    JsonObject obj = new JsonObject();
//
//                    JsonArray rot = new JsonArray();
//                    rot.add(new JsonPrimitive(disp.getRotation().getX()));
//                    rot.add(new JsonPrimitive(disp.getRotation().getY()));
//                    rot.add(new JsonPrimitive(disp.getRotation().getZ()));
//                    obj.add("rotation", rot);
//
//                    JsonArray trans = new JsonArray();
//                    trans.add(new JsonPrimitive(disp.getTranslation().getX()));
//                    trans.add(new JsonPrimitive(disp.getTranslation().getY()));
//                    trans.add(new JsonPrimitive(disp.getTranslation().getZ()));
//                    obj.add("translation", trans);
//
//                    JsonArray scale = new JsonArray();
//                    scale.add(new JsonPrimitive(disp.getScale().getX()));
//                    scale.add(new JsonPrimitive(disp.getScale().getY()));
//                    scale.add(new JsonPrimitive(disp.getScale().getZ()));
//                    obj.add("scale", scale);
//
//                    obj1.add(place.getPropertyName(), obj);
//                }
//            }
//            if (obj1 != null)
//                data.add("display", obj1);
//
//            List<IJsonModelElement> element = model.getElements();
//            if (element != null && !element.isEmpty()) {
//                JsonArray elems = new JsonArray();
//                for (IJsonModelElement e : element) {
//                    JsonObject obj = new JsonObject();
//
//                    Vector3i from = e.getStartPoint();
//                    JsonArray first = new JsonArray();
//                    first.add(new JsonPrimitive(from.getX()));
//                    first.add(new JsonPrimitive(from.getY()));
//                    first.add(new JsonPrimitive(from.getZ()));
//                    obj.add("from", first);
//
//                    Vector3i to = e.getEndPoint();
//                    JsonArray end = new JsonArray();
//                    end.add(new JsonPrimitive(to.getX()));
//                    end.add(new JsonPrimitive(to.getY()));
//                    end.add(new JsonPrimitive(to.getZ()));
//                    obj.add("to", end);
//
//                    IJsonModelRotation rot = e.getRotation();
//                    if (rot != null) {
//
//                        JsonObject rotation = new JsonObject();
//
//                        Vector3i origin = rot.getOrigin();
//                        JsonArray orig = new JsonArray();
//                        orig.add(new JsonPrimitive(origin.getX()));
//                        orig.add(new JsonPrimitive(origin.getY()));
//                        orig.add(new JsonPrimitive(origin.getZ()));
//                        rotation.add("origin", first);
//
//                        rotation.addProperty("axis", rot.getAxis().name().toLowerCase());
//
//                        rotation.addProperty("angler", rot.getAngle());
//
//                        rotation.addProperty("rescale", rot.rescale());
//
//                        obj.add("rotation", rotation);
//                    }
//
//                    obj.addProperty("shade", e.shouldRenderShadows());
//
//                    JsonObject faces = null;
//
//                    for (Direction dir : Direction.values()) {
//                        IJsonModelFace face = e.getFace(dir);
//
//                        if (face != null) {
//                            if (faces == null)
//                                faces = new JsonObject();
//                            JsonObject jsonFace = new JsonObject();
//
//                            JsonArray uv = new JsonArray();
//                            Vector4d vec = face.getUV();
//                            uv.add(new JsonPrimitive(vec.getX()));
//                            uv.add(new JsonPrimitive(vec.getY()));
//                            uv.add(new JsonPrimitive(vec.getZ()));
//                            uv.add(new JsonPrimitive(vec.getW()));
//
//                            jsonFace.add("uv", uv);
//
//                            jsonFace.addProperty("texture", "#" + face.getTextureID());
//
//                            if (face.getCullFace() != null & face.getCullFace() != dir)
//                                jsonFace.addProperty("cullface", face.getCullFace().name().toLowerCase());
//
//                            if (face.getTextureRotation() != 0)
//                                jsonFace.addProperty("rotation", face.getTextureRotation());
//
//                            faces.add(dir.name().toLowerCase(), jsonFace);
//                        }
//                    }
//
//                    if (faces != null)
//                        obj.add("faces", faces);
//
//                    elems.add(obj);
//                }
//                data.add("elements", elems);
//            }
//
//            jsonText = g.toJson(data);
//            writer.write(jsonText);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void createIfNeededBlockModel(File file, IJsonModel model) {
////        if (file.exists())
////            return;
//
//        try {
//
//            FileWriter writer = new FileWriter(file);
//            Gson g = new GsonBuilder().setPrettyPrinting().newCreativeTab();
//            JsonObject data = new JsonObject();
//            String jsonText = null;
//
////            if (model.getParent() != null)
////                data.addProperty("parent", model.getParent().getModelName());
//
//            if (!model.useAmbientOcclusion())
//                data.addProperty("ambientocclusion", false);
//
//            JsonObject textures = new JsonObject();
//            List<Pair<String, ResourceReference>> texList = model.getTextures();
//            if (texList != null && !texList.isEmpty()) {
//                for (Pair<String, ResourceReference> e : texList) {
//                    textures.addProperty(e.getKey(), e.getValue().toString());
//                }
//                data.add("textures", textures);
//            }
//
//            JsonObject obj1 = null;
//
//            for (JsonRenderPlace place : JsonRenderPlace.values()) {
//                JsonRenderDisplay disp = model.getDisplay(place);
//
//                if (disp != null) {
//                    if (obj1 == null)
//                        obj1 = new JsonObject();
//                    JsonObject obj = new JsonObject();
//
//                    JsonArray rot = new JsonArray();
//                    rot.add(new JsonPrimitive(disp.getRotation().getX()));
//                    rot.add(new JsonPrimitive(disp.getRotation().getY()));
//                    rot.add(new JsonPrimitive(disp.getRotation().getZ()));
//                    obj.add("rotation", rot);
//
//                    JsonArray trans = new JsonArray();
//                    rot.add(new JsonPrimitive(disp.getTranslation().getX()));
//                    rot.add(new JsonPrimitive(disp.getTranslation().getY()));
//                    rot.add(new JsonPrimitive(disp.getTranslation().getZ()));
//                    obj.add("translation", trans);
//
//                    JsonArray scale = new JsonArray();
//                    rot.add(new JsonPrimitive(disp.getScale().getX()));
//                    rot.add(new JsonPrimitive(disp.getScale().getY()));
//                    rot.add(new JsonPrimitive(disp.getScale().getZ()));
//                    obj.add("scale", scale);
//
//                    obj1.add(place.getPropertyName(), obj);
//                }
//            }
//            if (obj1 != null)
//                data.add("display", obj1);
//
//            List<IJsonModelElement> element = model.getElements();
//            if (element != null && !element.isEmpty()) {
//                JsonArray elems = new JsonArray();
//                for (IJsonModelElement e : element) {
//                    JsonObject obj = new JsonObject();
//
//                    Vector3i from = e.getStartPoint();
//                    JsonArray first = new JsonArray();
//                    first.add(new JsonPrimitive(from.getX()));
//                    first.add(new JsonPrimitive(from.getY()));
//                    first.add(new JsonPrimitive(from.getZ()));
//                    obj.add("from", first);
//
//                    Vector3i to = e.getEndPoint();
//                    JsonArray end = new JsonArray();
//                    end.add(new JsonPrimitive(to.getX()));
//                    end.add(new JsonPrimitive(to.getY()));
//                    end.add(new JsonPrimitive(to.getZ()));
//                    obj.add("to", end);
//
//                    IJsonModelRotation rot = e.getRotation();
//                    if (rot != null) {
//
//                        JsonObject rotation = new JsonObject();
//
//                        Vector3i origin = rot.getOrigin();
//                        JsonArray orig = new JsonArray();
//                        orig.add(new JsonPrimitive(origin.getX()));
//                        orig.add(new JsonPrimitive(origin.getY()));
//                        orig.add(new JsonPrimitive(origin.getZ()));
//                        rotation.add("origin", first);
//
//                        rotation.addProperty("axis", rot.getAxis().name().toLowerCase());
//
//                        rotation.addProperty("angler", rot.getAngle());
//
//                        rotation.addProperty("rescale", rot.rescale());
//
//                        obj.add("rotation", rotation);
//                    }
//
//                    obj.addProperty("shade", e.shouldRenderShadows());
//
//                    JsonObject faces = null;
//
//                    for (Direction dir : Direction.values()) {
//                        IJsonModelFace face = e.getFace(dir);
//
//                        if (face != null) {
//                            if (faces == null)
//                                faces = new JsonObject();
//                            JsonObject jsonFace = new JsonObject();
//
//                            JsonArray uv = new JsonArray();
//                            Vector4d vec = face.getUV();
//                            uv.add(new JsonPrimitive(vec.getX()));
//                            uv.add(new JsonPrimitive(vec.getY()));
//                            uv.add(new JsonPrimitive(vec.getZ()));
//                            uv.add(new JsonPrimitive(vec.getW()));
//
//                            jsonFace.add("uv", uv);
//
//                            jsonFace.addProperty("texture", "#" + face.getTextureID());
//
//                            if (face.getCullFace() != null & face.getCullFace() != dir)
//                                jsonFace.addProperty("cullface", face.getCullFace().name().toLowerCase());
//
//                            if (face.getTextureRotation() != 0)
//                                jsonFace.addProperty("rotation", face.getTextureRotation());
//
//                            faces.add(dir.name().toLowerCase(), jsonFace);
//                        }
//                    }
//
//                    if (faces != null)
//                        obj.add("faces", faces);
//
//                    elems.add(obj);
//                }
//                data.add("elements", elems);
//            }
//
//            jsonText = g.toJson(data);
//            writer.write(jsonText);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
