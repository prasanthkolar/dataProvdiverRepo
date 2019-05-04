package dataSource;

import Model.LoginModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import readExcel.Mapper;
import readExcel.TabToUse;
import readExcel.WorkBookUtil;

import java.lang.reflect.Method;
import java.util.List;

public class DataProvider {

    private static final Logger log = LoggerFactory.getLogger(DataProvider.class);

    private DataProvider() {
    }

    public static Workbook workbook;

    public static void loadSpreadSheet(String sheetDataFileName) {
        try {
            workbook = WorkBookUtil.loadWorkbook(sheetDataFileName);
        } catch (Throwable e) {
            log.error("Could not read spreadsheet \n" + e.getMessage());
        }
    }

    @org.testng.annotations.DataProvider(name = "LoginModel")
    public static Object[][] getSimulatorData(Method m) {

        // Get the tab contents
        String tab = m.getAnnotation(TabToUse.class).value()[0];
        List<LoginModel> items = Mapper
                .mapFromExcel(workbook, tab)
                .toObjectOf(LoginModel.class)
                .map();

        Object[][] result = new Object[items.size()][1];
        int ind = 0;
        for (LoginModel item : items) {
            result[ind][0] = item;
            ind++;
        }
        return result;
    }

}
