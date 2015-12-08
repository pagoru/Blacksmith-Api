package com.cout970.testmod.blocks;

import net.darkaqua.blacksmith.api.block.DefaultBlockDefinition;
import net.darkaqua.blacksmith.api.block.blockstate.IBlockVariant;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.render.ResourceReference;
import net.darkaqua.blacksmith.api.render.model.default_models.SimpleJsonModel;
import net.darkaqua.blacksmith.api.render.model.default_models.SimpleJsonModelWrapper;
import net.darkaqua.blacksmith.api.render.model.json.IJsonModel;
import net.darkaqua.blacksmith.api.render.model.json.IJsonModelWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cout970 on 24/11/2015.
 */
public class TestBlock extends DefaultBlockDefinition {

    public TestBlock() {
        super("testBlock");
    }

    @Override
    public IBlockRenderHandler getBlockRenderHandler() {
        final IJsonModel model = new SimpleJsonModel("block_model_name", new ResourceReference("mod_id", "blocks/texture_name"));
        return new IBlockRenderHandler() {

            @Override
            public List<IJsonModelWrapper> getBlockModelsForState(IBlockVariant state) {
                List<IJsonModelWrapper> list = new ArrayList<>(1);
                list.add(new SimpleJsonModelWrapper(model));
                return list;
            }
        };
    }
}
