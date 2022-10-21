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

import adactin.pages.BookHotelPage;
import adactin.pages.BookedItinraryPage;
import adactin.pages.LoginPage;
import adactin.pages.SearchHotelPage;
import adactin.pages.SelectHotelPage;
import setup.BaseClass;
import utills.Utility;

public class BookHotelPageTest extends BaseClass {
	private WebDriver driver;
	private LoginPage loginPage;
	private SearchHotelPage searchHotelPage;
	private SelectHotelPage selectHotelPage;
	private BookHotelPage bookHotelPage;
	private BookedItinraryPage bookedItinraryPage;
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
		searchHotelPage = new SearchHotelPage (driver);
		selectHotelPage = new SelectHotelPage (driver);
		bookHotelPage = new BookHotelPage (driver);
		bookedItinraryPage = new BookedItinraryPage (driver);
	}
	@BeforeMethod 
	public void openApplication() throws Throwable
	{
		driver.navigate().to("https://adactinhotelapp.com/");
		System.out.println("Application opened successfully");
		 loginPage = new LoginPage(driver);
			String user = Utility.getDataFromExcel(1, 1);
			String pass = Utility.getDataFromExcel(2, 1);
			loginPage.loginToApplication(user, pass);
		 
			String dateIN = Utility.getDataFromExcel(3, 1);
			String dateOut = Utility.getDataFromExcel(4, 1);
			searchHotelPage.searchHotelFields(dateIN, dateOut);
		 searchHotelPage.clickOnSearchButton();
		 
		 selectHotelPage.clickOnCheckBox();
		 selectHotelPage.clickOnContinueButton();
		 
		String fname = Utility.getDataFromExcel(5, 1);
		String lname = Utility.getDataFromExcel(6, 1);
		String add = Utility.getDataFromExcel(7, 1);
		String cCardNo = Utility.getDataFromExcel(8, 1);
		String ccvN = Utility.getDataFromExcel(9, 1);
		bookHotelPage.bookHotelFields(fname, lname, add, cCardNo, ccvN);
		 
		 soft = new SoftAssert ();
	}
	
	@Test (priority = 0)
	public void verifyBookNowButton () throws InterruptedException
	{
		testID = "0007";
		bookHotelPage.clickOnBookNowButton();
		Thread.sleep(6000);
		soft.assertEquals(BookedItinraryPage.bookConfirmText(), "Booking Confirmation");
		
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		System.out.println(url);
		System.out.println(title);
		soft.assertEquals(url, "https://adactinhotelapp.com/BookingConfirm.php",
				"URL of Hotel Booking Confirmation page is wrong");
		soft.assertEquals(title, "Adactin.com - Hotel Booking Confirmation",
				"Title of Hotel Booking Confirmation page is wrong");
		soft.assertAll();
		System.out.println("Book Now button verified");	
	}
	
	@Test (priority = 1)
	public void verifyCancelButton () throws InterruptedException
	{
		testID = "0008";
	    selectHotelPage.clickOnCancelButton();
	    Thread.sleep(3000);
	    String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		System.out.println(url);
		System.out.println(title);
		soft.assertEquals(url, "https://adactinhotelapp.com/SelectHotel.php",
				"URL of Select hotel page is wrong");
		soft.assertEquals(title, "Adactin.com - Select Hotel", "Title of Select hotel page is wrong");
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
		Thread.sleep(5000);
	//	searchHotelPage.clickOnLogout();
	}
	@AfterClass
	public void clearObjects ()
	{
		loginPage = null;
		searchHotelPage = null;	
		selectHotelPage = null;	
		bookHotelPage = null;
	}
	@AfterTest
	public void closeBrowser()
	{
//		searchHotelPage.closetheApplication();
	//	driver.close();
		driver = null;
		System.gc();
	}
}
