/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package readExcel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author redcrow
 */
class TypeConverters {
    private static Map<Class, TypeConverter> converter;

    TypeConverters() {
    }

    private static void registerConverter() {
        converter = new HashMap();
        converter.put(String.class, new StringTypeConverter());
    }

    public static Map<Class, TypeConverter> getConverter() {
        if (converter == null) {
            registerConverter();
        }

        return converter;
    }
}
