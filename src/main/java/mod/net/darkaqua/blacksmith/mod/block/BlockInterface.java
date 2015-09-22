package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInterface extends Block{

	public IBlock block;

	protected BlockInterface(IBlock block) {
		super(Material.iron);//TODO change material
		this.block = block;
	}	
}
