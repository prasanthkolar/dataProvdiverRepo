/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package readExcel;

import java.lang.reflect.Field;

/**
 * @author redcrow
 */
interface EachFieldCallback {
    void each(Field var1, String var2) throws Throwable;
}

