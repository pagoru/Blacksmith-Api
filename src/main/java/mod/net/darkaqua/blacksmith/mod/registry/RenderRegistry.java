package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.registry.IRenderRegistry;
import net.darkaqua.blacksmith.api.render.model.generated.IGenModel;
import net.darkaqua.blacksmith.api.render.model.json.IJsonModelWrapper;
import net.darkaqua.blacksmith.mod.Blacksmith;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.*;

/**
 * Created by cout970 on 07/12/2015.
 */
public class RenderRegistry implements IRenderRegistry {

    public static final RenderRegistry INSTANCE = new RenderRegistry();
    private static Map<ResourceLocation, IGenModel> models = new HashMap<>();
    private static List<ItemRenderRegister> itemsToRegister = new LinkedList<>();

    private RenderRegistry() {
    }

    public Map<ResourceLocation, IGenModel> getRegisteredModels() {
        return models;
    }

    public Set<String> getRegisteredDomains() {
        HashSet<String> domains = new HashSet<>();
        for (ResourceLocation res : models.keySet()) {
            domains.add(res.getResourceDomain());
        }
        return domains;
    }

    @Override
    public boolean registerCustomBlockModel(IBlockDefinition def, IGenModel model) {
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if (mod == null) {
            throw new IllegalStateException("Block models should be registered only in preInit");
        }
        BlockRegistry.RegisteredBlock reg = BlockRegistry.INSTANCE.getRegistrationData(def);
        final String domain = Blacksmith.MOD_ID + "@" + mod.getModId().toLowerCase();

        //itemblock
        ModelBakery.addVariantName(reg.getItemBlock(), new ResourceLocation(domain, reg.getIdentifier().toLowerCase()).toString());
        ModelLoader.setCustomModelResourceLocation(reg.getItemBlock(), 0, new ModelResourceLocation(domain+ ":" + reg.getIdentifier().toLowerCase(), "inventory"));
        models.put(new ResourceLocation(domain, reg.getIdentifier().toLowerCase()), model);
        itemsToRegister.add(new ItemRenderRegister(reg.getItemBlock(), 0, new ModelResourceLocation(domain+ ":" + reg.getIdentifier().toLowerCase(), "inventory")));

        //block
        ModelLoader.setCustomStateMapper(reg.getMcBlock(), new StateMapperBase() {

            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                ResourceLocation block = (ResourceLocation) Block.blockRegistry.getNameForObject(state.getBlock());
                block = new ResourceLocation(domain, block.getResourcePath());
                String variant = getPropertyString(state.getProperties());
                return new ModelResourceLocation(block, variant);
            }
        });

        return true;
    }

    @Override
    public boolean registerJsonBlockModel(IBlockDefinition def, IJsonModelWrapper model) {

        return false;
    }

    public void onInit(){
        for(ItemRenderRegister r : itemsToRegister){
            Minecraft.getMinecraft()
                    .getRenderItem()
                    .getItemModelMesher()
                    .register(r.getItem(), r.getMeta(), r.getLocation());
        }
    }

    public static class ItemRenderRegister{

        private Item item;
        private int meta;
        private ModelResourceLocation location;

        public ItemRenderRegister(Item item, int meta, ModelResourceLocation location) {
            this.item = item;
            this.meta = meta;
            this.location = location;
        }

        public Item getItem() {
            return item;
        }

        public int getMeta() {
            return meta;
        }

        public ModelResourceLocation getLocation() {
            return location;
        }
    }
}
