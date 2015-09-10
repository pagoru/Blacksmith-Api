package net.darkaqua.blacksmith.mod.block;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockHolder extends Block{

	public IBlock block;

	protected BlockHolder(Material materialIn) {
		super(materialIn);
	}
}
