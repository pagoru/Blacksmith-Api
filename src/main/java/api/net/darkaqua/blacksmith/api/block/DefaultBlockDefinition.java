package net.darkaqua.blacksmith.api.block;

import net.darkaqua.blacksmith.api.block.blockstate.BlockStateFactory;
import net.darkaqua.blacksmith.api.block.blockstate.IBlockStateHandler;
import net.darkaqua.blacksmith.api.block.blockstate.IIBlockState;
import net.darkaqua.blacksmith.api.creativetab.CreativeTabFactory;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.util.Cube;
import net.minecraft.block.state.IBlockState;

/**
 * Created by cout970 on 08/11/2015.
 */
public class DefaultBlockDefinition implements IBlockDefinition{

    protected String blockName;
    protected IBlock parent;

    public DefaultBlockDefinition(String name){
        blockName = name;
    }

    @Override
    public void onBlockCreate(IBlock block) {
        parent = block;
    }

    @Override
    public String getUnlocalizedName(){
        return blockName;
    }

    @Override
    public Cube getBlockBounds() {
        return Cube.fullBlock();
    }

    @Override
    public float getBlockHardness() {
        return 1.5F;
    }

    @Override
    public float getLightEmitted() {
        return 0F;
    }

    @Override
    public float getLightOpacity() {
        return 1F;
    }

    @Override
    public float getBlockResistance() {
        return 10F;
    }

    @Override
    public ICreativeTab getCreativeTab() {
        return CreativeTabFactory.BLOCKS_TAB;
    }

    @Override
    public IBlockRenderHandler getBlockRenderHandler() {
        return null;
    }

    @Override
    public IBlockStateHandler getBlockStateHandler() {
        final IIBlockState state = BlockStateFactory.createBlockState(parent);
        return new IBlockStateHandler(){

            @Override
            public IIBlockState getDefaultBlockState() {
                return state;
            }

            @Override
            public IIBlockState getBlockStateFromMeta(int meta) {
                return state;
            }

            @Override
            public int getMetaFromBlockState(IBlockState state) {
                return 0;
            }
        };
    }
}
