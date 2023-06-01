package tests;
import TestComponents.BaseTest;
import dataProvider.dataDriven;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DataDrivenTests extends BaseTest {

    DataFormatter formatter = new DataFormatter();

    @Test
    public void loginWithCorrectData() throws IOException {

        dataDriven dd = new dataDriven();
        ArrayList<String> arr = dd.getData("Login");

        LoginPage loginPage = homePage.goToLogin();
        loginPage.login(arr.get(1), arr.get(2));

    }

    @Test(dataProvider = "getData")
    public void loginWithDataProvider(String email, String password){

        LoginPage loginPage = homePage.goToLogin();
        loginPage.login(email,password);
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        //Object [][] data = {{"ilyas@email.com","password"},{"tata@email.com","password"},{"ilyas@ilyas.com","password"}};
        //return data;

        FileInputStream file = new FileInputStream("C:\\Users\\izaman\\Documents\\GitHub\\JavaUIAutomation\\JavaUIAutomationProject\\Datas\\loginDatas.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int columnCount = row.getLastCellNum();
        Object data[][] = new Object[rowCount - 1][columnCount];

        /**
         * data [0][0] = ilyas@email.com , data [0][1] = password
         * data [1][0] = tata@email.com , data [1][1] = password
         */

        for (int i = 0; i < rowCount - 1; i++) {
            row = sheet.getRow(i + 1);
            for (int j = 0; j < columnCount; j++) {
                XSSFCell cell = row.getCell(j);
                data[i][j] = formatter.formatCellValue(cell);
            }
        }
        return data;

    }



}
