package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.item.IItemDefinition;
import net.darkaqua.blacksmith.api.render.model.IBlockModelProvider;
import net.darkaqua.blacksmith.api.render.model.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.IModelIdentifier;
import net.darkaqua.blacksmith.api.render.tileentity.ITileEntityRenderer;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;
import net.darkaqua.blacksmith.api.util.ResourceReference;

/**
 * Created by cout970 on 07/12/2015.
 */
public interface IRenderRegistry {

    boolean registerBlockModelProvider(IBlockDefinition def, IBlockModelProvider provider);
    boolean registerItemModelProvider(IItemDefinition def, IItemModelProvider provider);
    boolean registerTileEntityRenderer(Class<? extends ITileEntityDefinition> def, ITileEntityRenderer renderer);

    IModelIdentifier registerModelFile(ResourceReference file);
    IModelIdentifier registerFlatItemModel(ResourceReference texture);
}