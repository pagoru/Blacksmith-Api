package net.darkaqua.blacksmith.api.common.intermod;

/**
 * Created by cout970 on 24/01/2016.
 */
public class InterModUtils {

    public static boolean matches(IInterfaceIdentifier<?> a, IInterfaceIdentifier<?> b){
        return a != null && b != null && a.matches(b);
    }
}
