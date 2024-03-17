package Extents;

import java.awt.Desktop;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentScreenshot {
	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");
		extentReports.attachReporter(sparkReporter);
		
		ExtentSparkReporterConfig config = sparkReporter.config();
		config.setTheme(Theme.DARK);
		config.setReportName("Extent Report");
	
		WebDriver driver = new ChromeDriver();
		driver.get("https://google.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys("rohit sharma");
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).build().perform();
		driver.manage().window().maximize();
		String path = captureScreenshot("Google.jpg");
		
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();//to get browserName & browserVersion
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("Browser", capabilities.getBrowserName());
		extentReports.setSystemInfo("Browser Version", capabilities.getVersion());
		Thread.sleep(3000);
		

		extentReports.createTest("Screenshot test 1", "Google Homepage")
		.info("This is info message")
		.addScreenCaptureFromPath(path, "Google Homepage");

		Throwable t = new Throwable("This is throwable exception ");
		extentReports.createTest("Screenshot test ", "Google Homepage")
		.info("This is info message");
//		.fail(t, MediaEntityBuilder.createScreenCaptureFromPath(path, "Google Homepage 2").build());
		
		
		
		extentReports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());

	}

	public static String captureScreenshot(String fileName) {
		TakesScreenshot takesScreenShot = (TakesScreenshot) driver;
		File source = takesScreenShot.getScreenshotAs(OutputType.FILE);
		File destination = new File("./Screenshots/" + fileName);
		try {
			FileUtils.copyFile(source, destination);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Screenshot saved successfully");
		System.out.println("Rohit Gojare is good guy");
		return destination.getAbsolutePath();

	}

}