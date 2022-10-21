package testScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
import setup.BaseClass;
import utills.Utility;

public class SearchHotelPageTest extends BaseClass {

	private WebDriver driver;
	private LoginPage loginPage;
	private SearchHotelPage searchHotelPage;
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
	}
	@BeforeMethod 
	public void openApplication() throws Exception
	{
		driver.navigate().to("https://adactinhotelapp.com/");
		String user = Utility.getDataFromExcel(1, 1);
		String pass = Utility.getDataFromExcel(2, 1);
		loginPage.loginToApplication(user, pass);
		String dateIN = Utility.getDataFromExcel(3, 1);
		String dateOut = Utility.getDataFromExcel(4, 1);
		searchHotelPage.searchHotelFields(dateIN, dateOut);
		soft = new SoftAssert ();
	}
	
	@Test (priority = 0)
	public void verifySearchButton ()
	{
		testID = "0003";
		searchHotelPage.clickOnSearchButton();
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		System.out.println(url);
		System.out.println(title);
		
		soft.assertEquals(url, "https://adactinhotelapp.com/SelectHotel.php",
				"URL of Select hotel page is wrong");
		soft.assertEquals(title, "Adactin.com - Select Hotel", "Title of Select hotel page is wrong");
		soft.assertAll();
		System.out.println("Search button verified");
	}
	@Test (priority = -1)
	public void verifyResetButton ()
	{
		testID = "0004";
		searchHotelPage.clickOnResetButton();
		Assert.assertFalse(false, "Fuctionality of Reset button is not working");
		System.out.println("Reset button verified");
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
	}
	@AfterTest
	public void closeBrowser()
	{
//		searchHotelPage.closetheApplication();
		driver.close();
		driver =null;
		System.gc();
	}
}
