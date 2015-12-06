package com.cout970.testmod.blocks;

import net.darkaqua.blacksmith.api.block.DefaultBlockDefinition;
import net.darkaqua.blacksmith.api.block.blockstate.IBlockVariant;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.render.TextureLocation;
import net.darkaqua.blacksmith.api.render.model.default_models.SimpleBlockModel;
import net.darkaqua.blacksmith.api.render.model.default_models.SimpleBlockModelWrapper;
import net.darkaqua.blacksmith.api.render.model.json.*;
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
                        return new Vector3i(16, 16, 16);
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
            public List<IBlockModelWrapper> getBlockModelsForState(IBlockVariant state) {
                List<IBlockModelWrapper> list = new ArrayList<>(1);
                list.add(new SimpleBlockModelWrapper(model));
                return list;
            }
        };
    }

}
