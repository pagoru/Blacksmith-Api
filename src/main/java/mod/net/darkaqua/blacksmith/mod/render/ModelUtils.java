package net.darkaqua.blacksmith.mod.render;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.render.TextureLocation;
import net.darkaqua.blacksmith.api.util.Log;
import net.darkaqua.blacksmith.api.util.Tuple2;
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

    public static ModelResourceLocation getModelResourceLocation(Item item, int i, IBlockDefinition definition, String identifier) {
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null) {
            throw new NullPointerException();
        }
        String path = Blacksmith.MOD_ID + ":" + mod.getName().toLowerCase()+"/"+identifier.toLowerCase();
        ModelResourceLocation res = new ModelResourceLocation(path, "inventory");
        File file = getFile(Blacksmith.MOD_ID, mod.getName().toLowerCase(), "/models/block/", identifier.toLowerCase() + ".json");
        Log.debug(file);
        Log.debug(res);
        createIfNeeded(definition.getBlockRenderHandler(), file);
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
            Tuple2<String, TextureLocation>[] textures = handler.getTextureProvider().getAllTextures();
            JsonObject textureArray = new JsonObject();

            for (Tuple2<String, TextureLocation> tex : textures) {
                textureArray.addProperty(tex.getFist(), tex.getLast().toString());
            }
            data.addProperty("parent", "block/cube_all");
            data.add("textures", textureArray);

            String jsonText = g.toJson(data);
            Log.debug("=====================================================================================================");
            Log.debug(jsonText);
            writer.write(jsonText);

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getFile(String domain, String mod, String path, String resource) {
        URL i = ModelUtils.class.getResource("/assets/" + domain + "/");
        File baseFile = null;
        if (i == null) {
            Log.error("No resource found on: " + "/assets/" + domain + "/");
            return null;
        } else {
            try {
                baseFile = new File(i.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
        File endFile = new File(baseFile, path+"/"+mod);
        if (!endFile.exists()) {
            endFile.mkdirs();
        }

        return new File(endFile, resource);
    }
}
