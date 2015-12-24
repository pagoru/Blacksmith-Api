package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.item.IItem;
import net.darkaqua.blacksmith.api.item.IItemDefinition;
import net.darkaqua.blacksmith.api.registry.IItemRegistry;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.mod.exceptions.BlacksmithInternalException;
import net.darkaqua.blacksmith.mod.item.BS_Item;
import net.darkaqua.blacksmith.mod.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cout970 on 19/12/2015.
 */
public class ItemRegistry implements IItemRegistry {

    public static final ItemRegistry INSTANCE = new ItemRegistry();
    private static final Map<IItemDefinition, RegisteredItem> registeredItems = new HashMap<>();

    private ItemRegistry() {
    }

    @Override
    public IItem registerItemDefinition(IItemDefinition definition, String identifier) {

        if (ModLoaderManager.getLoadingState() != ModLoaderManager.LoadingState.PREINIT) {
            throw new IllegalStateException("Item definition should be registered only in preInit");
        }
        if (definition == null)
            throw new NullPointerException("ItemRegistry cannot use a null IItemDefinition to newCreativeTab a new item");
        if (identifier == null)
            throw new NullPointerException("ItemRegistry cannot use a null identifier to newCreativeTab a new item");
        BlacksmithModContainer mod = ModLoaderManager.getActiveMod();
        if(mod == null)
            throw new BlacksmithInternalException("Invalid mod container in item registration: null");

        identifier = mod.getModId().toLowerCase()+"/"+identifier;

        BS_Item item = new BS_Item(definition);
        GameRegistry.registerItem(item, identifier);
        IItem result = MCInterface.fromItem(item);

        RegisteredItem reg = new RegisteredItem(definition, result, item, mod.getModId(), identifier);
        registeredItems.put(definition, reg);

        return result;
    }

    @Override
    public List<IItem> getRegisteredItems() {
        List<IItem> list = new LinkedList<>();

        for (Object b : GameData.getItemRegistry()) {
            if (b instanceof Item) {
                list.add(MCInterface.fromItem((Item) b));
            }
        }
        return list;
    }

    @Override
    public List<IItemDefinition> getRegisteredItemDefinitions() {
        return new LinkedList<>(registeredItems.keySet());
    }

    @Override
    public IItem getItemFromDefinition(IItemDefinition def) {
        RegisteredItem reg = registeredItems.get(def);
        if (reg == null){
            return null;
        }
        return reg.getIItem();
    }

    @Override
    public IItem findItem(String domain, String name) {
        Item i = GameRegistry.findItem(domain, name);
        if (i == null) {
            i = Item.itemRegistry.getObject(new ResourceLocation(domain, name));
        }
        return MCInterface.fromItem(i);
    }

    public RegisteredItem getRegistrationData(IItemDefinition def) {
        return registeredItems.get(def);
    }

    public static class RegisteredItem {

        private IItemDefinition definition;
        private IItem iItem;
        private Item mcItem;
        private String identifier;
        private String modID;
        private Map<IModelIdentifier, ModelResourceLocation> itemModels;

        public RegisteredItem(IItemDefinition definition, IItem item, Item mcItem, String modID, String identifier) {
            this.definition = definition;
            this.iItem = item;
            this.mcItem = mcItem;
            this.identifier = identifier;
            this.modID = modID;
            itemModels = new HashMap<>();
        }

        public IItemDefinition getDefinition() {
            return definition;
        }

        public IItem getIItem() {
            return iItem;
        }

        public Item getItem() {
            return mcItem;
        }

        public String getIdentifier() {
            return identifier;
        }

        public String getModID() {
            return modID;
        }

        public ModelResourceLocation getModelResourceLocation(IModelIdentifier i) {
            return itemModels.get(i);
        }

        public void addModel(IModelIdentifier i, ModelResourceLocation model){
            itemModels.put(i, model);
        }
    }
}
