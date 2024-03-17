package testScripts;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClass {
 	@BeforeSuite
 	public void beforeSuite()
 	{
 		System.out.println("beforesuite ABCD");
 	}
 	@BeforeTest
 	public void beforeTest()
 	{
 		System.out.println("beforetest ABCD");
 	}
 	@BeforeClass
 	public void beforeClass ()
 	{
 		System.out.println("beforeclass");
 	}
	
	@BeforeMethod 
	public void beforeMethod()
	{
		System.out.println("beforemethod");
	}
	@Test (priority = 1, dependsOnMethods = {"testD"})  //AND logic
	public void testA()
	{
		System.out.println("TEST A");
	}
	@Test (priority = 2, timeOut = 3000)
	public void testB() throws InterruptedException
	{
		System.out.println("TEST B");
	//	Thread.sleep(5000);
	//	Assert.fail();  	//Test B will be fail
	}
	@Test (invocationCount = 1, priority = 3)
	public void testC()
	{
		System.out.println("TEST C");
	}
	@Test (priority = -1 )
	public void testD()
	{
		System.out.println("TEST D");
	//	Assert.fail();  	//Test D will be fail
	}
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("aftermethod");
	}
	@AfterClass
	public void afterClass()
	{
		System.out.println("afetrClass");
	}
	@AfterTest
	public void afterTest()
	{
		System.out.println("aftertest ABCD");
	}
	@AfterSuite
	public void afterSuite ()
	{
		System.out.println("aftersuite ABCD");
	}
}
