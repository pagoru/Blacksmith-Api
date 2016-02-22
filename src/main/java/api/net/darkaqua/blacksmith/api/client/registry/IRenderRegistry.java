package net.darkaqua.blacksmith.api.client.registry;

import net.darkaqua.blacksmith.api.common.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.common.item.IItemDefinition;
import net.darkaqua.blacksmith.api.client.render.block.IBlockModelProvider;
import net.darkaqua.blacksmith.api.client.render.item.IItemModelProvider;
import net.darkaqua.blacksmith.api.client.render.tileentity.ITileEntityRenderer;
import net.darkaqua.blacksmith.api.common.registry.IModelRegistry;
import net.darkaqua.blacksmith.api.common.tileentity.ITileEntityDefinition;

/**
 * Created by cout970 on 07/12/2015.
 */
public interface IRenderRegistry {

    boolean registerBlockModelProvider(IBlockDefinition def, IBlockModelProvider provider);

    boolean registerItemModelProvider(IItemDefinition def, IItemModelProvider provider);

    boolean registerTileEntityRenderer(Class<? extends ITileEntityDefinition> def, ITileEntityRenderer<? extends ITileEntityDefinition> renderer);

    IModelRegistry getModelRegistry();
}