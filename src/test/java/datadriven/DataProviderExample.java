package datadriven;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class DataProviderExample {
    DataFormatter formatter = new DataFormatter();

    @Test
    public void testCaseData(String id, String password, String count) {
        System.out.println(id + password + count);

    }

    @DataProvider(name = "driveTest")
    public Object[][] getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\User\\Downloads\\Book1.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int numberOfSheets = workbook.getNumberOfSheets();

        XSSFSheet sheet = null;
        for (int i = 0; i < numberOfSheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("testData")) {
                sheet = workbook.getSheetAt(i);
            }
        }

        int rowCount = Objects.requireNonNull(sheet).getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int colCount = row.getLastCellNum();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 0; i < rowCount - 1; i++) {

            row = sheet.getRow(i + 1);
            for (int j = 0; j < colCount; j++) {

                XSSFCell cell = row.getCell(j);

                data[i][j] = formatter.formatCellValue(cell);
            }
        }
        return data;

    }
}
