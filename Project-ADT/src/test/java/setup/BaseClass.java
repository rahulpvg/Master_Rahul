package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
	
	public static WebDriver openChromeBrowser ()
	{
		System.setProperty("webdriver.chrome.driver",
				"E:\\Drivers\\chrome\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	public static WebDriver openFireFoxBrowser ()
	{	
		System.setProperty("webdriver.gecko.driver",
				"E:\\Drivers\\firefox\\geckodriver_64bit.exe");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	public static WebDriver openEdgeBrowser ()
	{	
		System.setProperty("webdriver.edge.driver",
				"E:\\Drivers\\edge\\msedgedriver_64bit.exe");
		WebDriver driver = new EdgeDriver();
		return driver;
	}	
}
