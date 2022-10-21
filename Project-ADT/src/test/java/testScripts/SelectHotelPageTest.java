package testScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import adactin.pages.LoginPage;
import adactin.pages.SearchHotelPage;
import adactin.pages.SelectHotelPage;
import setup.BaseClass;
import utills.Utility;

public class SelectHotelPageTest extends BaseClass {

	private WebDriver driver;
	private LoginPage loginPage;
	private SearchHotelPage searchHotelPage;
	private SelectHotelPage selectHotelPage;
	private SoftAssert soft;
	private String testID;
	@Parameters ("browser")
	@BeforeTest
	public void openBrowser (String browserName)
	{
		if(browserName.equals("Chrome"))
		{
			driver = openChromeBrowser();
		}
		if(browserName.equals("Firefox"))
		{
			driver = openFireFoxBrowser();
		}
		if(browserName.equals("Edge"))
		{
			driver = openEdgeBrowser();
		}	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeClass
	public void cretePOMObjects ()
	{
		loginPage = new LoginPage(driver);
		searchHotelPage = new SearchHotelPage(driver);
		selectHotelPage = new SelectHotelPage (driver);
	}
	@BeforeMethod 
	public void openApplication() throws IOException
	{
		driver.navigate().to("https://adactinhotelapp.com/");
		String user = Utility.getDataFromExcel(1, 1);
		String pass = Utility.getDataFromExcel(2, 1);
		loginPage.loginToApplication(user, pass);
		 

		String dateIN = Utility.getDataFromExcel(3, 1);
		String dateOut = Utility.getDataFromExcel(4, 1);
		searchHotelPage.searchHotelFields(dateIN, dateOut);
		 searchHotelPage.clickOnSearchButton();
		 
		 selectHotelPage = new SelectHotelPage (driver);
		 selectHotelPage.clickOnCheckBox();
		 soft = new SoftAssert ();
	}
	
	@Test (priority = -1)
	public void verifyContinewButton ()
	{
		testID = "0005";
		selectHotelPage.clickOnContinueButton();
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		System.out.println(url);
		System.out.println(title);
		soft.assertEquals(url, "https://adactinhotelapp.com/BookHotel.php",
				"URL of Book hotel page is wrong");
		soft.assertEquals(title, "Adactin.com - Book A Hotel", "Title of Book hotel page is wrong");
		soft.assertAll();
		System.out.println("Continue button verified");	
	}
	
	@Test (priority = 0)
	public void verifyCancelButton ()
	{
		testID = "0006";
	    selectHotelPage.clickOnCancelButton();
	    String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		System.out.println(url);
		System.out.println(title);
		soft.assertEquals(url, "https://adactinhotelapp.com/SearchHotel.php",
				"URL of Search hotel page is wrong");
		soft.assertEquals(title, "Adactin.com - Search Hotel", "Title of Search Hotel page is wrong");
		soft.assertAll();
		System.out.println("Cancel button verified");
	}
	@AfterMethod
	public void logoutApplication (ITestResult result) throws InterruptedException, IOException
	{
		if(ITestResult.FAILURE == result.getStatus())
		{
			Utility.captureScreen(driver, testID);
		}
		searchHotelPage.clickOnLogout();
	}
	@AfterClass
	public void clearObjects ()
	{
		loginPage = null;
		searchHotelPage = null;	
		selectHotelPage = null;	
	}
	@AfterTest
	public void closeBrowser()
	{
//		searchHotelPage.closetheApplication();
		driver.close();
		driver = null;
		System.gc();
	}
}
