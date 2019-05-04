/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package readExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class Mapper {
    private final Workbook workbook;
    private final String sheetName;
    Logger log = LoggerFactory.getLogger("Spreadsheet Operations");
    private Class classe;

    private Mapper(Workbook workbook, String sheetName) {
        this.workbook = workbook;
        this.sheetName = sheetName;
    }

    public static Mapper mapFromExcel(Workbook workbook, String sheetName) {
        return new Mapper(workbook, sheetName);
    }

    public Mapper toObjectOf(Class classe) {
        this.classe = classe;
        return this;
    }

    private String getValueByName(String name, Row row, Map<String, Integer> cells) {
        if (cells.get(name) == null) {
            return null;
        } else {
            Cell cell = row.getCell((Integer)cells.get(name), Row.RETURN_BLANK_AS_NULL);
            return this.getCellValue(cell);
        }
    }

    private void mapNameToIndex(String name, Row row, Map<String, Integer> cells) {
        int index = this.findIndexCellByName(name, row);
        if (index != -1) {
            cells.put(name, index);
        }

    }

    private void readExcelHeader(Row row, Map<String, Integer> cells) throws Throwable {
        ReflectionUtils.eachFields(this.classe, (field, name) -> {
            this.mapNameToIndex(name, row, cells);
        });
    }

    private Object readExcelContent(Row row, Map<String, Integer> cells) throws Throwable {
        Object instance = this.classe.newInstance();
        ReflectionUtils.eachFields(this.classe, (field, name) -> {
            ReflectionUtils.setValueOnField(instance, field, this.getValueByName(name, row, cells));
        });
        return instance;
    }

    public <T> List<T> map() {
        List<T> items = new ArrayList();
        Sheet sheet = null;

        try {
            sheet = this.workbook.getSheet(this.sheetName);
            Iterator<Row> rowIterator = sheet.iterator();
            Map<String, Integer> nameIndexMap = new HashMap();
            int var5 = sheet.getLastRowNum();

            while(rowIterator.hasNext()) {
                Row row = (Row)rowIterator.next();
                if (row.getRowNum() == 0) {
                    this.readExcelHeader(row, nameIndexMap);
                } else if (row.getCell(0) != null && row.getCell(0).getStringCellValue().trim().length() > 0) {
                    items.add((T)readExcelContent(row,nameIndexMap));

                }
            }
        } catch (Throwable var7) {
            this.log.error("\nCould not read sheet \"" + this.sheetName + "\" - if name seems correct, please make sure there is no space(s) before or after the sheet name.\n");
            System.exit(1);
        }

        return items;
    }

    private int findIndexCellByName(String name, Row row) {
        Iterator iterator = row.cellIterator();

        Cell cell;
        do {
            if (!iterator.hasNext()) {
                return -1;
            }

            cell = (Cell)iterator.next();
        } while(!this.getCellValue(cell).trim().equalsIgnoreCase(name));

        return cell.getColumnIndex();
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        } else {
            String value = "";
            switch(cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = value + String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    value = value + (new BigDecimal(cell.getNumericCellValue())).toString();
                    break;
                case Cell.CELL_TYPE_STRING:STRING:
                    value = value + cell.getStringCellValue();
            }

            return value;
        }
    }
}
