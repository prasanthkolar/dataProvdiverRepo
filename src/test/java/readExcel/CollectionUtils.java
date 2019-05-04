/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package readExcel;

import java.util.Collection;

/**
 * @author redcrow
 */
class CollectionUtils {
    CollectionUtils() {
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Object[] object) {
        return object == null || object.length < 1;
    }
}
