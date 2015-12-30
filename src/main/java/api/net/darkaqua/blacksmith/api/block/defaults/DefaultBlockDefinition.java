package net.darkaqua.blacksmith.api.block.defaults;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.creativetab.CreativeTabFactory;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.util.Cube;

/**
 * Created by cout970 on 08/11/2015.
 */
public abstract class DefaultBlockDefinition implements IBlockDefinition {

    protected String blockName;
    protected IBlock parent;

    public DefaultBlockDefinition(String name){
        blockName = name;
    }

    @Override
    public void onCreate(IBlock block) {
        parent = block;
    }

    @Override
    public String getUnlocalizedName(){
        return blockName;
    }

    @Override
    public Cube getBounds() {
        return Cube.fullBlock();
    }

    @Override
    public float getHardness() {
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
    public float getResistance() {
        return 10F;
    }

    @Override
    public ICreativeTab getCreativeTab() {
        return CreativeTabFactory.BLOCKS_TAB;
    }

    @Override
    public boolean shouldRender() {
        return true;
    }

    @Override
    public boolean isFullCube() {
        return true;
    }
}
