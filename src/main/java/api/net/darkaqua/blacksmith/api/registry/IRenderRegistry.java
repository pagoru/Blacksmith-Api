package net.darkaqua.blacksmith.api.registry;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.render.model.generated.IGenModel;
import net.darkaqua.blacksmith.api.render.model.json.IJsonModelWrapper;

/**
 * Created by cout970 on 07/12/2015.
 */
public interface IRenderRegistry {

    boolean registerCustomBlockModel(IBlockDefinition def, IGenModel model);
    boolean registerJsonBlockModel(IBlockDefinition def, IJsonModelWrapper model);
}
