package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class LoginDataDriven {

	@DataProvider(name = "LoginData")
	public Object[][] loginDataprovider() throws IOException {
		
		
		
		
		 String path = System.getProperty("user.dir")
	                + "/testData/loginData.xlsx";

	        ExcelFileUtils excel = new ExcelFileUtils();

	        return excel.getExcelData(path, "Sheet1");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		ExcelFileUtils excel=new ExcelFileUtils(System.getProperty("user.dir")+"//testData//loginData.xlsx");
//		int lastRowIndex = excel.getRowCount("Sheet1"); // returns last row index (0-based)
//		int rowCount = lastRowIndex; // assuming first row is header and data starts from row 1
//		int cellCount = excel.getCellCount("Sheet1", 0); // get cell count from header row (row 0)
//
//		String[][] data = new String[rowCount][cellCount];
//
//		// Read rows starting from 1 (first data row) to lastRowIndex inclusive
//		for (int i = 1; i <= lastRowIndex; i++) {
//			for (int j = 0; j < cellCount; j++) {
//				// store into zero-based array index (i-1)
//				data[i - 1][j] = excel.getCellData("Sheet1", i, j);
//			}
//		}
//		return data;

	
	}

}
