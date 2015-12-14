package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.darkaqua.blacksmith.api.util.Cube;
import net.darkaqua.blacksmith.mod.util.MCInterface;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_Block extends Block{

    private IBlockDefinition definition;

    public BS_Block(IBlockDefinition def) {
        super(Material.iron);//Temp
        definition = def;
        setUnlocalizedName(def.getUnlocalizedName());
        setCreativeTab(MCInterface.fromCreativeTab(def.getCreativeTab()));
        setBlockBounds(def.getBounds());
        setHardness(def.getHardness());
        setLightLevel(def.getLightEmitted());
        setLightOpacity((int)(def.getLightOpacity()*255f));
        setResistance(def.getResistance());
    }

    private void setBlockBounds(Cube c){
        setBlockBounds((float)c.minX(), (float)c.minY(), (float)c.minZ(), (float)c.maxX(), (float)c.maxY(), (float)c.maxZ());
    }
}
