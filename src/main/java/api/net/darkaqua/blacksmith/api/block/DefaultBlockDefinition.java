package net.darkaqua.blacksmith.api.block;

import net.darkaqua.blacksmith.api.creativetab.CreativeTabFactory;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.render.IBlockRenderHandler;
import net.darkaqua.blacksmith.api.util.Cube;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cout970 on 08/11/2015.
 */
public class DefaultBlockDefinition implements IBlockDefinition{

    protected String blockName;

    public DefaultBlockDefinition(String name){
        blockName = name;
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
    public IIBlockState getDefaultBlockState() {
        return null;
    }

    @Override
    public List<IIBlockState> getValidStates() {
        return Arrays.asList(new IIBlockState[1]);
    }
}
