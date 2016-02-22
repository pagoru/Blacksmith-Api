package net.darkaqua.blacksmith.api.common.event;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by cout970 on 07/11/2015.
 */
@Retention(value = RUNTIME)
@Target(value = METHOD)
public @interface EventSubscribe {
}
