package net.darkaqua.blacksmith.mod.render;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.darkaqua.blacksmith.mod.Blacksmith;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by cout970 on 16/11/2015.
 */
public class ModelUtils {

    public static ModelResourceLocation getModelResourceLocation(Item item, int i, IBlockDefinition definition) {
        String path = Blacksmith.MOD_ID + ":" + ModLoaderManager.getActiveMod().getName() +"/"+ definition.getUnlocalizedName();
        ModelResourceLocation res = new ModelResourceLocation(path, "inventory");
        createIfNeeded(definition.getBlockRenderHandler(), res);
        return res;
    }

    private static void createIfNeeded(IBlockRenderHandler handler, ResourceLocation path) {
        File file = getFile(path);

        //TODO crete Json file representing the block model
    }

    public static File getFile(ResourceLocation path) {
        URL i = ModelUtils.class.getResource("/assets/" + path.getResourceDomain() + "/" + path.getResourcePath());
        try {
            return new File(i.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
