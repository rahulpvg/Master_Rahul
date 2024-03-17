package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utilities.ReadConfig;


public class BaseClass {
	private ReadConfig readconfig;
	
	public WebDriver openChromeBrowser ()
	{
		readconfig = new ReadConfig();
		System.setProperty("webdriver.chrome.driver",readconfig.getChromepath());
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	public WebDriver openFireFoxBrowser ()
	{	
		readconfig = new ReadConfig();
		System.setProperty("webdriver.gecko.driver", readconfig.getgeckopath());
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	public WebDriver openEdgeBrowser ()
	{
		readconfig = new ReadConfig();
		System.setProperty("webdriver.edge.driver",readconfig.getedgepath());
		WebDriver driver = new EdgeDriver();
		return driver;
	}	
}