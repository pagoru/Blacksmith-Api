package net.darkaqua.blacksmith.api.intermod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cout970 on 16/01/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface InterfaceIdentifierInject {

    Class<?> value();
}
