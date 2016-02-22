package net.darkaqua.blacksmith.api.common.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface ClientSideOnly {

}
