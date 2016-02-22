package net.darkaqua.blacksmith.api.common.registry;

import net.darkaqua.blacksmith.api.common.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntityDefinition;

/**
 * Created by cout970 on 14/11/2015.
 */
public interface ITileEntityRegistry {

    boolean registerTileEntityDefinition(Class<? extends ITileEntityDefinition> clazz, String id);

    String getDefinitionID(ITileEntityDefinition def);

    Class<? extends ITileEntityDefinition> getDefinitionClass(String id);

    ITileEntity createTileEntity(ITileEntityDefinition def);
}
