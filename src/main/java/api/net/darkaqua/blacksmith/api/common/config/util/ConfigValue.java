package net.darkaqua.blacksmith.api.common.config.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cout970 on 09/01/2016.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface ConfigValue {

    String category() default "general";

    //if the key is empty then the field name will be used as a key
    String key() default "";

    String comment();
}
