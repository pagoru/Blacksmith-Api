package net.darkaqua.blacksmith.mod.render;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by cout970 on 25/11/2015.
 */
public class RenderManager {

    public static final RenderManager INSTANCE = new RenderManager();
    private static List<IBlockRenderHandler> handlers = new LinkedList<>();
    private static final List<RenderRegistration> renders = new LinkedList<>();
    private static final HashSet<String> registeredDomains = new HashSet<>();
    private static final List<ResourceLocation> registeredResources = new LinkedList<>();

    public void registerRenders(){
        for(RenderRegistration render : renders){
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(render.getItem(), render.getMeta(), render.getModel());
        }
    }

    public void register(Block block, Item item, IBlockDefinition def, String identifier){

        if (def.getBlockRenderHandler() != null) {
            List<RenderRegistration> render = ModelUtils.setupBlock(block, item, def, identifier);
            for (RenderRegistration rend: render) {
                renders.add(rend);
                registeredDomains.add(rend.getModel().getResourceDomain());
                registeredResources.add(rend.getModel());
//                ModelBakery.addVariantName(rend.getItem(), rend.getModel().getResourceDomain()+":"+rend.getModel().getResourcePath());
            }
        }
    }

    public Set getRegisteredDomains() {
        return registeredDomains;
    }

    public List<ResourceLocation> registeredResourceLocations() {
        return registeredResources;
    }

    public static class RenderRegistration{
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
