package com.cout970.testmod.blocks;

import net.darkaqua.blacksmith.api.block.DefaultBlockDefinition;
import net.darkaqua.blacksmith.api.block.blockstate.IIBlockState;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.render.ITileEntityModel;
import net.darkaqua.blacksmith.api.render.TextureLocation;
import net.darkaqua.blacksmith.api.render.model.IBlockModel;
import net.darkaqua.blacksmith.api.render.model.IBlockStateModel;
import net.darkaqua.blacksmith.api.render.model.default_models.SimpleBlockModel;
import net.darkaqua.blacksmith.api.render.model.default_models.SimpleBlockStateModel;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;

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
        final IBlockModel model = new SimpleBlockModel("block_model_name", new TextureLocation("mod_domain", "blocks/texture_name"));
        return new IBlockRenderHandler() {
            @Override
            public List<IBlockStateModel> getBlockStateModels() {
                List<IBlockStateModel> list = new ArrayList<>(1);
                list.add(new SimpleBlockStateModel("block_identifier", "normal"));
                return list;
            }

            @Override
            public IBlockModel getModel(String modelName) {
                return model;
            }

            @Override
            public IIBlockState getBlockState(String id) {
                return TestBlock.this.getBlockStateHandler().getDefaultBlockState();
            }

            @Override
            public ITileEntityModel getTileEntityModel(ITileEntity tileEntity) {
                return null;
            }
        };
    }

}
