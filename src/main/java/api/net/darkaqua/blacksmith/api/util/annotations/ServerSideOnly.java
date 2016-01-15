package net.darkaqua.blacksmith.api.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by cout970 on 15/01/2016.
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface ServerSideOnly {
}
