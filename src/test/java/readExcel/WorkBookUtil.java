package readExcel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;

public class WorkBookUtil {
    private WorkBookUtil() {
    }

    public static Workbook loadWorkbook(String spreadsheet) {
        Logger log = LoggerFactory.getLogger("Spreadsheet Operations");
        File excelFile = null;
        Object workbook = null;

        try {
            excelFile = new File(spreadsheet);
        } catch (Exception var16) {
            log.error("Could not open spreadsheet: " + spreadsheet);
        }

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(excelFile);
            if (excelFile.getName().endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception var15) {
            log.error("Could not open spreadsheet: " + spreadsheet);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception var14) {
                    log.debug("Could not close: " + spreadsheet);
                }
            }

        }

        return (Workbook)workbook;
    }
}
