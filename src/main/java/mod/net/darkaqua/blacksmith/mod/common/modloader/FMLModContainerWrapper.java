package net.darkaqua.blacksmith.mod.common.modloader;

import com.google.common.base.Throwables;
import net.minecraftforge.fml.common.FMLModContainer;
import net.minecraftforge.fml.common.ILanguageAdapter;
import net.minecraftforge.fml.common.discovery.ModCandidate;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by cout970 on 06/02/2016.
 */
public class FMLModContainerWrapper extends FMLModContainer {

    private String className2;

    public FMLModContainerWrapper(String className, ModCandidate container, Map<String, Object> modDescriptor) {
        super(className, container, translate(modDescriptor));
        this.className2 = className;
    }

    public static Map<String, Object> translate(Map<String, Object> base){
        base.put("modid", base.get("id"));
        return base;
    }

    public String getClassName() {
        return className2;
    }

    public ILanguageAdapter getLanguageAdapter2() {
        try {
            Field languageAdapter = FMLModContainer.class.getDeclaredField("languageAdapter");
            languageAdapter.setAccessible(true);
            return (ILanguageAdapter) languageAdapter.get(this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Throwables.propagate(e);
        }
        return null;
    }
}
