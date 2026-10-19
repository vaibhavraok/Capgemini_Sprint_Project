package com.Bitm.NinzaHRM.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtility {

    public static String getData(String sheetName, int row, int cell) throws IOException {
        FileInputStream fis = new FileInputStream("./src/test/resources/Testing Data.xlsx");
        Workbook workbook = WorkbookFactory.create(fis);
        String data = workbook.getSheet(sheetName).getRow(row).getCell(cell).toString();
        workbook.close();
        fis.close();
        return data;
    }
}