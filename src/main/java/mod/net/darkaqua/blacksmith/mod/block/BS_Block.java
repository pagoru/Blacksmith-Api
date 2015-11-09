package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlockDefinition;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by cout970 on 08/11/2015.
 */
public class BS_Block extends Block{

    public BS_Block(IBlockDefinition def) {
        super(Material.iron);//Temp
        setCreativeTab(CreativeTabs.tabRedstone);
    }

}
