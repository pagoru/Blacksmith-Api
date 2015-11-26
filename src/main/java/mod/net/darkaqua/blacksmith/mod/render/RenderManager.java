package net.darkaqua.blacksmith.mod.render;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 25/11/2015.
 */
public class RenderManager {

    public static final RenderManager INSTANCE = new RenderManager();
    private static List<IBlockRenderHandler> handlers = new LinkedList<>();
    private static final List<RenderRegistration> renders = new LinkedList<>();


    public static void registerRenders(){
        for(RenderRegistration render : renders){
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(render.getItem(), render.getMeta(), render.getModel());
        }
    }

    public void register(Block block, Item item, IBlockDefinition def, String identifier){

        if (def.getBlockRenderHandler() != null) {
            for (int i = 0; i < def.getValidStates().size(); i++) {

                RenderRegistration render = new RenderRegistration(item, i, ModelUtils.getModelResourceLocation(item, i, def, identifier));
                ModelBakery.addVariantName(render.getItem(), render.getModel().getResourceDomain()+":"+render.getModel().getResourcePath());
                renders.add(render);
            }
        }
    }
    private class RenderRegistration{
        private Item item;
        private int meta;
        private ModelResourceLocation model;

        public RenderRegistration(Item item, int meta, ModelResourceLocation model) {
            this.item = item;
            this.meta = meta;
            this.model = model;
        }

        public Item getItem() {
            return item;
        }

        public int getMeta() {
            return meta;
        }

        public ModelResourceLocation getModel() {
            return model;
        }
    }
}
