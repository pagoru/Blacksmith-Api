package net.darkaqua.blacksmith.mod;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

/**
 * Created by cout970 on 28/01/2016.
 */
@IFMLLoadingPlugin.MCVersion("1.8.9")
public class BlacksmithLoader implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return "net.darkaqua.blacksmith.mod.Blacksmith";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
