package com.cout970.testmod.blocks;

import net.darkaqua.blacksmith.api.block.DefaultBlockDefinition;
import net.darkaqua.blacksmith.api.block.blockstate.IIBlockState;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.render.ITileEntityModel;
import net.darkaqua.blacksmith.api.render.TextureLocation;
import net.darkaqua.blacksmith.api.render.model.*;
import net.darkaqua.blacksmith.api.render.model.default_models.SimpleBlockModel;
import net.darkaqua.blacksmith.api.tileentity.ITileEntity;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.Vector3i;
import net.darkaqua.blacksmith.api.util.Vector4d;

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
        final IBlockModel model = new SimpleBlockModel("block_model_name", new TextureLocation("mod_id", "blocks/texture_name")){
            @Override
            public List<IModelElement> getElements() {
                List<IModelElement> e = new ArrayList<>(1);
                e.add(new IModelElement() {
                    @Override
                    public Vector3i getStartPoint() {
                        return new Vector3i(0,0,0);
                    }

                    @Override
                    public Vector3i getEndPoint() {
                        return new Vector3i(8, 8, 8);
                    }

                    @Override
                    public IModelRotation getRotation() {
                        return null;
                    }

                    @Override
                    public boolean shouldRenderShadows() {
                        return true;
                    }

                    @Override
                    public IModelFace getFace(Direction dir) {
                        return new IModelFace() {
                            @Override
                            public Vector4d getUV() {
                                return new Vector4d(0, 0, 16, 16);
                            }

                            @Override
                            public String getTextureID() {
                                return "all";
                            }

                            @Override
                            public Direction getCullFace() {
                                return null;
                            }

                            @Override
                            public int getTextureRotation() {
                                return 0;
                            }
                        };
                    }
                });
                return e;
            }
        };
        return new IBlockRenderHandler() {

            @Override
            public ITileEntityModel getTileEntityModel(ITileEntity tileEntity) {
                return null;
            }

            @Override
            public List<IBlockStateModelMapper> getBlockModelsForState(IIBlockState state) {
                List<IBlockStateModelMapper> list = new ArrayList<>(1);
                list.add(new IBlockStateModelMapper() {

                    @Override
                    public String getModelName() {
                        return "block_model_name";
                    }

                    @Override
                    public int getRotationX() {
                        return 0;
                    }

                    @Override
                    public int getRotationY() {
                        return 0;
                    }

                    @Override
                    public boolean useUVLock() {
                        return false;
                    }

                    @Override
                    public int getWeight() {
                        return 1;
                    }
                });
                return list;
            }
        };
    }

}
