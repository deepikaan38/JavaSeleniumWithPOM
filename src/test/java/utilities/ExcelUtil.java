package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcelUtil {

	/*
	 * We use XSSFWorkbook because our test data files are stored in .xlsx format.
	 * It supports modern Excel features, handles more rows and columns than .xls,
	 * and is sufficient for automation test data management. For very large
	 * datasets, we can use SXSSFWorkbook to reduce memory usage.
	 */

	public static String inputDirectory;
	public static WebDriver driver;

	public static void createNewDirectory(String folderName) {
		try {
			inputDirectory = System.getProperty("user.dir") + "\\src\\test\\resources\\" + folderName;
			System.out.println(inputDirectory);
			File folder = new File(inputDirectory);
			if (!folder.exists()) {
				folder.mkdirs();
			}
		} catch (Exception e) {

		}
	}

	public static void writeExcelFile(String excelName, String sheetName) {
		try {
			createNewDirectory("testdata");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(sheetName);
			XSSFRow row1 = sheet.createRow(0);
			row1.createCell(0).setCellValue("UserName");
			row1.createCell(1).setCellValue("Passwords");
			XSSFRow row2 = sheet.createRow(1);
			row2.createCell(0).setCellValue("admin");
			row2.createCell(1).setCellValue("admin123");

			FileOutputStream fos = new FileOutputStream(inputDirectory + File.separator + excelName);
			workbook.write(fos);
			fos.close();
			workbook.close();
			

		} catch (Exception e) {

		}
	}

	public static void writeExcelFile2(String excelName, String sheetName) {
		try {
			createNewDirectory("testdata");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(sheetName);
			Thread.sleep(2000);

			List<WebElement> headers = driver.findElements(By.xpath("//thead/tr/th"));
			List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
			List<WebElement> columns = driver.findElements(By.xpath("//tbody/tr/td"));
			List<WebElement> productname = driver.findElements(By.xpath("//tbody/tr/th"));

			XSSFRow row1 = sheet.createRow(0);
			Thread.sleep(2000);
			for (int i = 0; i < headers.size(); i++) {
				String text = headers.get(i).getText();
				System.out.println(text);
				row1.createCell(i).setCellValue(text);
			}

			for (int i = 1; i <=rows.size(); i++) {
				XSSFRow ROW = sheet.createRow(i);
				for (int j = 0; j <= productname.size(); j++) {
					if (j == 0) {
						ROW.createCell(j)
								.setCellValue(driver.findElement(By.xpath("//tbody/tr[" + i + "]/th")).getText());
					} else {
						ROW.createCell(j).setCellValue(
								driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[" + j + "]")).getText());

					}
				}
			}

			FileOutputStream fos = new FileOutputStream(inputDirectory + File.separator + excelName);
			workbook.write(fos);
			fos.close();
			workbook.close();

		} catch (Exception e) {

		}
	}

	public static String getCellData(String path, String sheetName, int rowNum, int colNum) throws Exception {

		FileInputStream fis = new FileInputStream(path);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetName);

		String data = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();

		workbook.close();

		return data;
	}

	public static void main(String[] args) throws InterruptedException {
//		writeExcelFile("loginCreadentials.xlsx", "validedata");
//		try {
//			String value = getCellData(inputDirectory+File.separator+"loginCreadentials.xlsx","validedata",1,0);
//		System.out.println(value);
//		} catch (Exception e) {
//			
//			e.printStackTrace();

		driver = new ChromeDriver();
		driver.get("https://demoapps.qspiders.com/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='UI Testing Concepts']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//section[text()='Web Table']")).click();
		Thread.sleep(2000);
		writeExcelFile2("TableData.xlsx", "data");
//		System.out.println(driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText());

		driver.quit();

	}

}
