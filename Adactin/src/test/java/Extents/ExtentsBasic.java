package Extents;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentsBasic {
	public static void main(String[] args) throws Exception {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");
		extent.attachReporter(sparkReporter);
		ExtentTest test1 = extent.createTest("Test1");
		test1.pass("this is passed");
		
		ExtentTest test2 = extent.createTest("Test2");
		test2.log(Status.FAIL, "This is failed");
		
		ExtentTest test3 = extent.createTest("Test1");
		test3.skip("This is skippped");
		
		extent.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());
		
		
	}

}
