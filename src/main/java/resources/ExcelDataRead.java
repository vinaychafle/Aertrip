package resources;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataRead {
	private static final String FILE_PATH = System.getProperty("user.dir")+"/src/main/java/resources/FlightDetails.xlsx";
		//	"C:\\Users\\vchafle\\eclipse-workspace\\AertripFlight\\src\\main\\java\\resources\\FlightDetails.xlsx";
			//System.getProperty("user.dir")+"/src/main/java/resources/FlightDetails.xlsx";

	// Method to read data from a specified sheet
	private static Object[][] getTestDataFromExcel(String sheetName) throws IOException {
		FileInputStream fileInputStream = null;
		XSSFWorkbook workbook = null;

		try {
			fileInputStream = new FileInputStream(FILE_PATH);
			workbook = new XSSFWorkbook(fileInputStream);

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}

			int rowCount = sheet.getLastRowNum() + 1; // Get the number of rows (including header)
			int colCount = sheet.getRow(0).getLastCellNum(); // Get the number of columns from the first row

			Object[][] data = new Object[rowCount - 1][colCount]; // Create the data array (excluding header row)

			for (int i = 1; i < rowCount; i++) { // Loop through rows (skip header)
				Row row = sheet.getRow(i);
				if (row == null)
					continue; // Skip if the row is null

				for (int j = 0; j < colCount; j++) { // Loop through columns
					Cell cell = row.getCell(j);
					Object cellValue = null;

					if (cell != null) {
						switch (cell.getCellType()) {
						case STRING:
							cellValue = cell.getStringCellValue(); // Handle string cells
							break;
						case NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								cellValue = cell.getDateCellValue(); // Handle date cells
							} else {
								cellValue = cell.getNumericCellValue(); // Handle numeric cells
							}
							break;
						case BOOLEAN:
							cellValue = cell.getBooleanCellValue(); // Handle boolean cells
							break;
						case FORMULA:
							DataFormatter dataFormatter = new DataFormatter();
							cellValue = dataFormatter.formatCellValue(cell); // Handle formula cells
							break;
						default:
							cellValue = cell.toString(); // Handle other types (e.g., errors)
							break;
						}
					}
					data[i - 1][j] = cellValue; // Store data in the array
				}
			}
			return data;

		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace(); // Handle potential IOExceptions during closing
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace(); // Handle potential IOExceptions during closing
				}
			}
		}
	}
	
	@DataProvider(name = "flightBookingDetails")
	public static Object[][] getLoginData() throws IOException {
		return getTestDataFromExcel("Booking_Details"); // Pass the sheet name directly
	}
	
	@DataProvider(name = "loginDetails")
	public static Object[][] getSignInData() throws IOException{
		return getTestDataFromExcel("login");
	}

}
