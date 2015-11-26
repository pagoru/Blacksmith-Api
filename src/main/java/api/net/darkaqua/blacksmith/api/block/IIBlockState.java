package net.darkaqua.blacksmith.api.block;

import java.util.Map;

public interface IIBlockState {

	IBlock getBlock();

	Object getInternalBlockState();

	Map<Object, Object> getProperties();
}
