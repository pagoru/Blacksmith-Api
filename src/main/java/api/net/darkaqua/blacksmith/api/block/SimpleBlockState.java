package net.darkaqua.blacksmith.api.block;

public class SimpleBlockState implements IIBlockState{

	private IBlock block;

	public SimpleBlockState(IBlock block) {
		this.block = block;
	}
	
	@Override
	public IBlock getBlock() {
		return block;
	}
}
