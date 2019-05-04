/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package readExcel;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;


/**
 * @author redcrow
 */
class ReflectionUtils {
    ReflectionUtils() {
    }

    private static String toUpperCaseFirstCharacter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void setValueOnField(Object instance, Field field, Object value) throws Exception {
        Class clazz = instance.getClass();
        String setMethodName = "set" + toUpperCaseFirstCharacter(field.getName());
        Iterator var5 = TypeConverters.getConverter().entrySet().iterator();

        while(var5.hasNext()) {
            Map.Entry<Class, TypeConverter> entry = (Map.Entry)var5.next();
            if (field.getType().equals(entry.getKey())) {
                Method method = clazz.getDeclaredMethod(setMethodName, (Class)entry.getKey());
                Column column = (Column)field.getAnnotation(Column.class);
                method.invoke(instance, ((TypeConverter)entry.getValue()).convert(value, new String[]{column == null ? null : column.pattern()}));
            }
        }

    }

    public static void eachFields(Class clazz, EachFieldCallback callback) throws Throwable {
        Field[] fields = clazz.getDeclaredFields();
        if (!CollectionUtils.isEmpty(fields)) {
            Field[] var3 = fields;
            int var4 = fields.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Field field = var3[var5];
                callback.each(field, field.isAnnotationPresent(Column.class) ? ((Column)field.getAnnotation(Column.class)).name() : field.getName());
            }
        }

    }
}
