package Extents;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class FailedExtentReport {
	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");
		
		ExtentSparkReporter sparkReporter_failed = new ExtentSparkReporter("FailedTests.html");
		sparkReporter_failed.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		
		extentReports.attachReporter(sparkReporter,sparkReporter_failed);

		ExtentSparkReporterConfig config = sparkReporter.config();
		config.setTheme(Theme.DARK);
		config.setReportName("Extent Report");

		driver = new ChromeDriver();
		Capabilities capabilities = ((RemoteWebDriver)driver).getCapabilities();
		driver.get("https://google.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys("rohit sharma");
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		extentReports.createTest("Environment test 1", "Google Homepage").info("This is info message");
		
		ExtentTest test2 = extentReports.createTest("Environment test 2", "Google failed" );
		test2.log(Status.FAIL, "This is Failed");
		
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("Browser", capabilities.getBrowserName());
		extentReports.setSystemInfo("Browser Version", capabilities.getVersion());
		
		extentReports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());
		Desktop.getDesktop().browse(new File("FailedTests.html").toURI());

	}

	
}
