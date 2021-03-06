package net.darkaqua.blacksmith.api.common.modloader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cout970 on 06/12/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ModSidedProxy {

    String clientSide() default "";

    String serverSide() default "";

    String modId() default "";
}
