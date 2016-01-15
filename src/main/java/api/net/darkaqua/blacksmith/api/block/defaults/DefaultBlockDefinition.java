package net.darkaqua.blacksmith.api.block.defaults;

import net.darkaqua.blacksmith.api.block.BlockMaterialFactory;
import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.block.IBlockMaterial;
import net.darkaqua.blacksmith.api.block.variants.BlockDataFactory;
import net.darkaqua.blacksmith.api.block.variants.IBlockData;
import net.darkaqua.blacksmith.api.block.variants.IBlockDataGenerator;
import net.darkaqua.blacksmith.api.creativetab.CreativeTabFactory;
import net.darkaqua.blacksmith.api.creativetab.ICreativeTab;
import net.darkaqua.blacksmith.api.util.Cube;

/**
 * Created by cout970 on 08/11/2015.
 */
public class DefaultBlockDefinition implements IBlockDefinition {

    protected String blockName;
    protected IBlock parent;

    public DefaultBlockDefinition(String name){
        blockName = name;
    }

    public DefaultBlockDefinition(){
        blockName = "noname";
    }

    @Override
    public void onCreate(IBlock block) {
        parent = block;
    }

    @Override
    public IBlockMaterial getBlockMaterial() {
        return BlockMaterialFactory.IRON;
    }

    @Override
    public IBlockDataGenerator getBlockDataGenerator() {
        return BlockDataFactory.createBlockDataGenerator(parent);
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

    @Override
    public IBlockData translateMetadataToVariant(int meta) {
        return parent.getDefaultBlockData();
    }

    @Override
    public int translateVariantToMetadata(IBlockData variant) {
        return 0;
    }

    @Override
    public IBlock getBlock() {
        return parent;
    }
}
