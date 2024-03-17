package Extents;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SystemEnvironment {
	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");
		extentReports.attachReporter(sparkReporter);

		ExtentSparkReporterConfig config = sparkReporter.config();
		config.setTheme(Theme.DARK);
		config.setReportName("Extent Report");

		System.setProperty("webdriver.chrome.driver",
				"E:\\Drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver (options);
		Capabilities capabilities = ((RemoteWebDriver)driver).getCapabilities();
		driver.get("https://google.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys("rohit sharma");
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		extentReports.createTest("Environment test 1", "Google Homepage").info("This is info message");
		
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("Browser", capabilities.getBrowserName());
		extentReports.setSystemInfo("Browser Version", capabilities.getVersion());
		
		extentReports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());

	}

}
