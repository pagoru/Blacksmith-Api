package net.darkaqua.blacksmith.api.block.properties;

import java.util.List;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public interface IBlockCreativeTabHandler {
	
	IBlock getBlock();

	CreativeTabs getCreativeTab();
	
	void getSubBlocks(Item itemIn, CreativeTabs tab, List<IItemStack> list);
	
	IItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos);
}
