package net.darkaqua.blacksmith.api.block;

import java.util.Collection;
import java.util.Map;

public interface IBlockVariant {

	IBlock getBlock();

	Collection<IIProperty> getProperties();

	Comparable<?> getValue(IIProperty property);

	IBlockVariant withProperty(IIProperty property, Comparable<?> value);

	IBlockVariant cycleProperty(IIProperty property);

	Map<IIProperty, Comparable<?>> getPropertyMap();

	Object getInternalBlockState();
}
