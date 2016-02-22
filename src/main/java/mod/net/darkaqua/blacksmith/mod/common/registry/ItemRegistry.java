package net.darkaqua.blacksmith.mod.common.registry;

import net.darkaqua.blacksmith.api.common.item.IItem;
import net.darkaqua.blacksmith.api.common.item.IItemDefinition;
import net.darkaqua.blacksmith.api.common.registry.IItemRegistry;
import net.darkaqua.blacksmith.mod.common.exceptions.BlacksmithInternalException;
import net.darkaqua.blacksmith.mod.common.item.BS_Item;
import net.darkaqua.blacksmith.mod.common.modloader.BlacksmithModContainer;
import net.darkaqua.blacksmith.mod.common.modloader.ModLoaderManager;
import net.darkaqua.blacksmith.mod.common.util.MCInterface;
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
        if (mod == null)
            throw new BlacksmithInternalException("Invalid mod container in item registration: null");

        identifier = mod.getModId().toLowerCase() + "/" + identifier;

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
    public IItem findItem(String domain, String name) {
        Item i = GameRegistry.findItem(domain, name);
        if (i == null) {
            i = Item.itemRegistry.getObject(new ResourceLocation(domain, name));
        }
        return MCInterface.fromItem(i);
    }

    @Override
    public String getItemDomain(IItem item) {
        GameRegistry.UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor(MCInterface.toItem(item));
        return ui.modId;
    }

    @Override
    public String getItemName(IItem item) {
        GameRegistry.UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor(MCInterface.toItem(item));
        return ui.name;
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

        public RegisteredItem(IItemDefinition definition, IItem item, Item mcItem, String modID, String identifier) {
            this.definition = definition;
            this.iItem = item;
            this.mcItem = mcItem;
            this.identifier = identifier;
            this.modID = modID;
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
    }
}
