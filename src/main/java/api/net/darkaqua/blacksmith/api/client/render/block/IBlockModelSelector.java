package net.darkaqua.blacksmith.api.client.render.block;

import net.darkaqua.blacksmith.api.client.render.item.IItemModelSelector;
import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;

import java.util.Map;

/**
 * Created by cout970 on 13/03/2016.
 */
public interface IBlockModelSelector extends IItemModelSelector {

    IStaticModel select(IBlockData data, Map<String, IStaticModel> map);

    default IStaticModel select(IItemStack data, Map<String, IStaticModel> map){
        return select((IBlockData) null, map);
    }
}
