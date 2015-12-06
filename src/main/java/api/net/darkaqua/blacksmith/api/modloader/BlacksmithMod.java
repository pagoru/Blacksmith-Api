package net.darkaqua.blacksmith.api.modloader;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
public @interface BlacksmithMod {

	String id();

	String name();

	String version();

	String dependencies() default "";

	String updateURL() default "";
}
