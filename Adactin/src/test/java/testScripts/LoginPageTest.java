package testScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pom.LoginPage;
import pom.SearchHotelPage;
import setup.BaseClass;
import utilities.ReadConfig;
import utilities.Utility;

public class LoginPageTest extends BaseClass {
	private WebDriver driver;
	private LoginPage loginPage;
	private SearchHotelPage searchHotelPage;
	private SoftAssert soft;
	private String testID;
	private ReadConfig readconfig = new ReadConfig();
	private ExtentSparkReporter reporter;

	@Parameters ("browser")
	@BeforeTest
	public void openBrowser (@Optional("chrome")String browserName)
	{
		reporter = new ExtentSparkReporter("test-output/ExtendReport/Extent.html");
		ExtentReports extend = new ExtentReports();
		extend.attachReporter(reporter);
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			driver = openChromeBrowser();
		}
		if(browserName.equalsIgnoreCase("Firefox"))
		{
			driver = openFireFoxBrowser();
		}
		if(browserName.equalsIgnoreCase("Edge"))
		{
			driver = openEdgeBrowser();
		}	
		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@BeforeClass
	public void cretePOMObjects()
	{
		 loginPage = new LoginPage(driver);
		 searchHotelPage = new SearchHotelPage(driver);
	}
	
	@BeforeMethod 
	public void openApplication() throws IOException, InterruptedException
	{
		readconfig = new ReadConfig();
		driver.navigate().to(readconfig.getApplicationURL());		
		String user = Utility.getDataFromExcel(1, 1);
		String pass = Utility.getDataFromExcel(2, 1);
		loginPage.loginToApplication(user, pass);
		 soft = new SoftAssert ();
	}
	@Test (priority = 2)
	public void verifyLogo ()
	{
		testID = "0001";
		Assert.assertTrue(loginPage.logo(), "Logo is wrong");
//		if (loginPage.logo() == true)
//		{
//			System.out.println("PASS");
//		}
//		else
//		{
//			System.out.println("FAIL");
//		}
		soft.assertAll();
	}	
	@Test (priority = 1)
	public void verifyLoginbutton() throws IOException
	{
		testID = "0002";
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		System.out.println(url);
		System.out.println(title);			
		
		Assert.assertEquals(url, "https://adactinhotelapp.com/SearchHotel.php",
				"URL of Search Hotel Page is wrong");
		Assert.assertEquals(title, "Adactin.com - Search Hotel",
				"Title of Search Hotel Page is wrong");
		soft.assertAll();
//		if(url.equals("https://adactinhotelapp.com/SearchHotel.php") 
//				&& title.equals("Adactin.com - Search Hotel"))
//		{
//			System.out.println("PASS");
//		}
//		else
//		{
//			System.out.println("FAIL");
//		}
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
		driver = null;		
		System.gc();   		//Garbage Collector = deletes all the objects which are not in used(null)
	}
}
