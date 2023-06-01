package dataProvider;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class dataDriven {

    public ArrayList<String> getData(String testCaseName) throws IOException {
        ArrayList <String> arr = new ArrayList<String>();


        FileInputStream file = new FileInputStream("C:\\Users\\izaman\\Documents\\GitHub\\JavaUIAutomation\\JavaUIAutomationProject\\Datas\\datademo.xlsx");
        // FileInputStream is a class where it will create an object which have the power to read any file
        // So here we create a "file" object which can read this "datademo.xlsx" file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        int sheets = workbook.getNumberOfSheets();
        // Getting access to particular sheet
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                // Identify TestCases column by scanning the entire 1st row
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> cell = firstRow.cellIterator();
                int k = 0;
                int column = 0;
                while (cell.hasNext()) {
                    Cell value = cell.next();

                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        column = k;
                    }
                    k++;
                }
                System.out.println("HERE " + column);

                // Once column is identified, then scan entire testcase column to identify "Login" test case row

                while (rows.hasNext()) {
                    Row r = rows.next();
                    if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName))
                    {
                        // After you grab "Login" testcase row, pull all the data from that row and feed into test
                        Iterator <Cell> cv = r.cellIterator();
                        while (cv.hasNext())
                        {
                            Cell c = cv.next();
                            if(c.getCellType() == CellType.STRING)
                            {
                                arr.add(c.getStringCellValue());
                            }
                            else
                            {
                                arr.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }

                }
            }
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {

    }
}




