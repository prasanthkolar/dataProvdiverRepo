/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package readExcel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author redcrow
 */
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface Column {

    String name() default "";

    String pattern() default "";
}
