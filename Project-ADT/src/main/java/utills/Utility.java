package utills;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {

	public static void captureScreen (WebDriver driver, String testID) throws IOException
	{
		TakesScreenshot ss = (TakesScreenshot) driver;
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(dateTime);
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH mm ss");   
	    String formattedDate = dateTime.format(myFormatObj); 
		File source = ss.getScreenshotAs(OutputType.FILE);
		File destination = new File(".\\screenShots\\"+testID+""+"_"+""+formattedDate+".jpeg");
		FileHandler.copy(source, destination);			
		
	}
	
	public static String getDataFromExcel (int rowNo, int cellNo) throws IOException
	{
		FileInputStream file = new FileInputStream ("D:\\Velocity\\Automation\\Framework\\Tetst data_Adactin.xlsx");
		Workbook work = WorkbookFactory.create(file);
		Sheet sheet = work.getSheet("Adactin");
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);
		
		try {
			String data = cell.getStringCellValue();
			return data;
		}
//		catch (ArithmeticException e) {
//			long data = (long) cell.getNumericCellValue();
//			String result = String.valueOf(data);
//			return result;
//		}
		catch (Exception e){
			Date data = cell.getDateCellValue();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
			String strDate = dateFormat.format(data);
			return strDate;
		}
	}
}