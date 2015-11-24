package net.darkaqua.blacksmith.mod.render;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.util.Log;
import net.darkaqua.blacksmith.mod.Blacksmith;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by cout970 on 16/11/2015.
 */
public class ModelUtils {

    public static ModelResourceLocation getModelResourceLocation(Item itemBlock, int i, IBlockDefinition definition, String identifier) {
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null) {
            throw new NullPointerException();
        }
        String path = Blacksmith.MOD_ID +"@"+ mod.getModId()+ ":" + identifier.toLowerCase()+".json";
        ModelResourceLocation res = new ModelResourceLocation(path, "inventory");

        File block = getFile(Blacksmith.MOD_ID +"@"+ mod.getModId(), "/models/block/", identifier.toLowerCase() + ".json");
        File item = getFile(Blacksmith.MOD_ID +"@"+ mod.getModId(), "/models/item/", identifier.toLowerCase() + ".json");

        Log.debug("JSON =====================================================================================================");

        createIfNeeded(definition.getBlockRenderHandler(), block);
        createIfNeeded(definition.getBlockRenderHandler(), item);
        return res;
    }

    private static void createIfNeeded(IBlockRenderHandler handler, File file) {
        if (file == null) return;

        if (file.exists())
            return;
        try {

            FileWriter writer = new FileWriter(file);
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            JsonObject data = new JsonObject();
//            Tuple2<String, TextureLocation>[] textures = handler.getBlockModel().getTextureProvider().getAllTextures();
//            JsonObject textureArray = new JsonObject();
//
//            for (Tuple2<String, TextureLocation> tex : textures) {
//                textureArray.addProperty(tex.getFist(), tex.getLast().toString());
//            }
//            data.addProperty("parent", "block/cube_all");
//            data.add("textures", textureArray);
//
            String jsonText = "";//g.toJson(data);
            Log.debug(jsonText);
            Log.debug("JSON =====================================================================================================");
            writer.write(jsonText);


            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getFile(String domain, String path, String resource) {
        URL i = ModelUtils.class.getResource("/assets/");
        File assets = null;
        if (i == null) {
            Log.error("No resource found on: " + "/assets/");
            return null;
        } else {
            try {
                assets = new File(i.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
        File endFile = new File(assets, domain+"/"+path+"/");
        if (!endFile.exists()) {
            endFile.mkdirs();
        }

        return new File(endFile, resource);
    }
}
