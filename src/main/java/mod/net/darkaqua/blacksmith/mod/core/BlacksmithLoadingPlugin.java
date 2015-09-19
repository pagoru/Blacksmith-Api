package net.darkaqua.blacksmith.mod.core;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.8")
public class BlacksmithLoadingPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return "net.darkaqua.blacksmith.mod.core.BlacksmithCore";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
