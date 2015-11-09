package net.darkaqua.blacksmith.api.inventory;

import net.darkaqua.blacksmith.api.block.IBlock;
import net.darkaqua.blacksmith.api.item.IItem;

public abstract class ItemStackFactory {

	protected static ItemStackFactory INSTANCE;

	public static IItemStack create(IItem item) {
		return INSTANCE.newItemStack(item, 1, 0);
	}

	public static IItemStack create(IItem item, int amount) {
		return INSTANCE.newItemStack(item, amount, 0);
	}

	public static IItemStack create(IItem item, int amount, int metadata) {
		return INSTANCE.newItemStack(item, amount, metadata);
	}

	public static IItemStack create(IBlock block) {
		return INSTANCE.newItemStack(block, 1, 0);
	}

	public static IItemStack create(IBlock block, int amount) {
		return INSTANCE.newItemStack(block, amount, 0);
	}

	public static IItemStack create(IBlock block, int amount, int metadata) {
		return INSTANCE.newItemStack(block, amount, metadata);
	}

	protected abstract IItemStack newItemStack(IItem item, int amount, int metadata);

	protected abstract IItemStack newItemStack(IBlock block, int amount, int metadata);

}
