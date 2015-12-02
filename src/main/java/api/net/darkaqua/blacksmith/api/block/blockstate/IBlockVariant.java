package net.darkaqua.blacksmith.api.block.blockstate;

import net.darkaqua.blacksmith.api.block.IBlock;

import java.util.Collection;
import java.util.Map;

public interface IBlockVariant {

	IBlock getBlock();

	Collection<String> getPropertyNames();

	Comparable<?> getValue(IIProperty property);

	IBlockVariant withProperty(IIProperty property, Comparable<?> value);

	IBlockVariant cycleProperty(IIProperty property);

	Map<IIProperty, Comparable<?>> getProperties();

	Object getInternalBlockState();
}
