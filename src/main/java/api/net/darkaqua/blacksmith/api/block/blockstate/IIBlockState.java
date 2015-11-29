package net.darkaqua.blacksmith.api.block.blockstate;

import net.darkaqua.blacksmith.api.block.IBlock;

import java.util.Collection;
import java.util.Map;

public interface IIBlockState {

	IBlock getBlock();

	Collection<String> getPropertyName();

	Comparable<?> getValue(IIProperty property);

	IIBlockState withProperty(IIProperty property, Comparable<?> value);

	IIBlockState cycleProperty(IIProperty property);

	Map<IIProperty, Comparable<?>> getProperties();

	Object getInternalBlockState();
}
