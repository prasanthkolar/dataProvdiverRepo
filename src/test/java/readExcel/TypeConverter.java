/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package readExcel;

/**
 * @author redcrow
 */
interface TypeConverter<T> {
    T convert(Object var1, String... var2);
}
