package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtils {

	public FileInputStream fileInputStream;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public String path;

	public ExcelFileUtils(String path) {
		this.path = path;
	}

	public ExcelFileUtils() {

	}

	public int getRowCount(String sheetName) throws IOException {
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		int totalRow;
		totalRow = sheet.getLastRowNum();
		workbook.close();
		fileInputStream.close();
		return totalRow;

	}

	public int getCellCount(String sheetName, int rowNumber) throws IOException {
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		workbook.close();
		fileInputStream.close();
		return row.getLastCellNum();
	}

	public String getCellData(String sheetName, int row, int cell) throws IOException {
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		this.row = sheet.getRow(row);
		this.cell = this.row.getCell(cell);

		DataFormatter formate = new DataFormatter();
		String data = formate.formatCellValue(this.cell);
		workbook.close();
		fileInputStream.close();

		return data;

	}

	public Object[][] getExcelData(String path, String sheetName) throws IOException {

		try (FileInputStream fis = new FileInputStream(path); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}

			int lastRow = sheet.getLastRowNum();
			int cellCount = sheet.getRow(0).getLastCellNum();

			Object[][] data = new Object[lastRow][cellCount];

			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i <= lastRow; i++) {
				for (int j = 0; j < cellCount; j++) {

					if (sheet.getRow(i) == null) {
						data[i - 1][j] = "";
					} else {
						data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
					}
				}
			}

			return data;
		}
	}
}
