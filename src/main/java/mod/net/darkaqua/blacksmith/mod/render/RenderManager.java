package net.darkaqua.blacksmith.mod.render;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.*;

/**
 * Created by cout970 on 25/11/2015.
 */
public class RenderManager {

    public static final RenderManager INSTANCE = new RenderManager();
    private static List<IBlockRenderHandler> handlers = new LinkedList<>();
    private static final List<RenderRegistrationData> renders = new LinkedList<>();
    private static final HashSet<String> registeredDomains = new HashSet<>();
    private static final List<ResourceLocation> registeredResources = new LinkedList<>();

    public void registerItemRenders() {
        for (RenderRegistrationData data : renders) {
            for (ItemRegistrationData render : data.getItems())
                render.register();
        }
    }

    public void registerBlockRenders() {
        for (RenderRegistrationData data : renders) {
            for (BlockRegistrationData render : data.getBlocks())
                render.register();
        }
    }

    public void register(Block block, Item item, IBlockDefinition def, String identifier) {

        if (def.getBlockRenderHandler() != null) {
            RenderRegistrationData rend = ModelUtils.setupBlock(block, item, def, identifier);
            renders.add(rend);
            for (ItemRegistrationData r : rend.getItems()) {
                registeredDomains.add(r.getModel().getResourceDomain());
                registeredResources.add(r.getModel());
            }

            for (BlockRegistrationData r : rend.getBlocks()) {
                registeredResources.addAll(r.getStateMap().values());
                for (ModelResourceLocation m : r.getStateMap().values()) {
                    registeredDomains.add(m.getResourceDomain());
                }
            }
        }
    }

    public Set<String> getRegisteredDomains() {
        return new HashSet<String>(registeredDomains);
    }

    public List<ResourceLocation> registeredResourceLocations() {
        return registeredResources;
    }

    public static class RenderRegistrationData {

        private List<ItemRegistrationData> items;
        private List<BlockRegistrationData> blocks;

        public RenderRegistrationData() {
            items = new ArrayList<>();
            blocks = new ArrayList<>();
        }

        public void addItem(Item item, int meta, ModelResourceLocation model) {
            items.add(new ItemRegistrationData(item, meta, model));
        }

        public void addBlock(Block block, Map<IBlockState, ModelResourceLocation> map) {
            blocks.add(new BlockRegistrationData(block, new HashMap<>(map)));
        }

        public List<ItemRegistrationData> getItems() {
            return items;
        }

        public List<BlockRegistrationData> getBlocks() {
            return blocks;
        }
    }

    private static class BlockRegistrationData {

        private Block block;
        private Map<IBlockState, ModelResourceLocation> map;

        public BlockRegistrationData(Block block, Map<IBlockState, ModelResourceLocation> map) {
            this.block = block;
            this.map = map;
        }

        public Block getBlock() {
            return block;
        }

        public Map<IBlockState, ModelResourceLocation> getStateMap() {
            return map;
        }

        public void register(){
            Minecraft.getMinecraft()
                    .getBlockRendererDispatcher()
                    .getBlockModelShapes()
                    .getBlockStateMapper()
                    .registerBlockStateMapper(block, new BS_StateMapper(map));
        }
    }

    private static class ItemRegistrationData {

        private Item item;
        private int meta;
        private ModelResourceLocation model;

        public ItemRegistrationData(Item item, int meta, ModelResourceLocation model) {
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

        public void register() {
            Minecraft.getMinecraft().
                    getRenderItem()
                    .getItemModelMesher()
                    .register(getItem(), getMeta(), getModel());
        }
    }
}
