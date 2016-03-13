package net.darkaqua.blacksmith.api.client.render.item;

import net.darkaqua.blacksmith.api.client.render.model.IStaticModel;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;

import java.util.Map;

/**
 * Created by cout970 on 13/03/2016.
 */
public interface IItemModelSelector {

    IStaticModel select(IItemStack data, Map<String, IStaticModel> map);
}
