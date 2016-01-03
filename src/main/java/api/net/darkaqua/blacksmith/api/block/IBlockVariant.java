package net.darkaqua.blacksmith.api.block;

import java.util.Collection;
import java.util.Map;

public interface IBlockVariant {

	IBlock getBlock();

	Collection<IIProperty> getProperties();

	<T extends Comparable<T>> T getValue(IIProperty<T> property);

	<T extends Comparable<T>> IBlockVariant withProperty(IIProperty<T> property, T value);

	IBlockVariant cycleProperty(IIProperty<?> property);

	<T extends Comparable<T>> Map<IIProperty<T>, T> getPropertyMap();

	Object getInternalBlockState();
}
