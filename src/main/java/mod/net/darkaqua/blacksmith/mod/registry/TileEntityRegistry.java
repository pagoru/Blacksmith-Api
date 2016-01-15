package net.darkaqua.blacksmith.mod.registry;

import net.darkaqua.blacksmith.api.registry.ITileEntityRegistry;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.mod.tileentity.BS_TileEntity;
import net.darkaqua.blacksmith.mod.util.MCInterface;

import java.util.HashMap;

/**
 * Created by cout970 on 14/11/2015.
 */
public class TileEntityRegistry implements ITileEntityRegistry {

    public static final TileEntityRegistry INSTANCE = new TileEntityRegistry();
    public static final HashMap<Class<? extends ITileEntityDefinition>, String> classID = new HashMap<>();
    public static final HashMap<String, Class<? extends ITileEntityDefinition>> idClass = new HashMap<>();

    private TileEntityRegistry() {
    }

    @Override
    public ITileEntity createTileEntity(ITileEntityDefinition def) {
        BS_TileEntity tile = new BS_TileEntity();
        tile.setDefinition(def);
        return MCInterface.fromTileEntity(tile);
    }

    @Override
    public boolean registerTileEntityDefinition(Class<? extends ITileEntityDefinition> clazz, String id) {
        if (!classID.containsKey(clazz)) {
            classID.put(clazz, id);
            idClass.put(id, clazz);
            return true;
        }
        return false;
    }

    public String getDefinitionID(ITileEntityDefinition def) {
        if (def == null) return null;
        return classID.get(def.getClass());
    }

    @Override
    public Class<? extends ITileEntityDefinition> getDefinitionClass(String id) {
        return idClass.get(id);
    }
}
