package adactin.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchHotelPage {
	
		private WebDriverWait wait;
		private WebDriver driver;
		private Select location;
		private Select hotels;
		@FindBy (tagName = "select")
		private List<WebElement> dropDown;
		@FindBy (xpath = "//input[@id='datepick_in']")
		private WebElement checkInDate;
		@FindBy (xpath = "//input[@id='datepick_out']")
		private WebElement checkOutDate;
		@FindBy (xpath = "//input[@id='Submit']")
		private WebElement searchButton;
		@FindBy (xpath = "//input[@id='Reset']")
		private WebElement resetButton;
		@FindBy (xpath = "//a[text()='Booked Itinerary']")
		private WebElement bookedItinrary;
		@FindBy (xpath = "//a[text() = 'Logout']")
		private WebElement logout;
		
		// Initialization
		public SearchHotelPage (WebDriver driver)
		{
			PageFactory.initElements(driver, this);
			this.driver = driver;
		}
		// Use
		public void searchHotelFields (String dateIN, String dateOut)
		{
			location = new Select (dropDown.get(0));
			location.selectByIndex(5);
			
			hotels = new Select (dropDown.get(1));
			hotels.selectByIndex(1);
			
			Select roomType = new Select (dropDown.get(2));
			roomType.selectByIndex(3);
			Select noOfRooms = new Select (dropDown.get(3));
			noOfRooms.selectByIndex(2);   
			
//			dropDownList = new Select (dropDown.get(0));
//			dropDownList.selectByIndex(5);
//		
//			
//			dropDownList = new Select (dropDown.get(1));
//			dropDownList.selectByIndex(1);
//			
//			dropDownList = new Select (dropDown.get(2));
//			dropDownList.selectByIndex(3);
//			
//			dropDownList = new Select (dropDown.get(3));
//			dropDownList.selectByIndex(2);
			
			checkInDate.clear();
			checkInDate.sendKeys(dateIN);
			checkOutDate.clear();
			checkOutDate.sendKeys(dateOut);
			
			Select adultsPerRoom = new Select (dropDown.get(4));
			adultsPerRoom.selectByIndex(2);
			Select childPerRoom = new Select (dropDown.get(5));
			Random rand = new Random();
			int rand_int = rand.nextInt(4); 
			childPerRoom.selectByIndex(rand_int);   
			
//			dropDownList = new Select (dropDown.get(4));
//			dropDownList.selectByIndex(2);
//			
//			Random rand = new Random();
//			int rand_int = rand.nextInt(4); 
//			dropDownList = new Select (dropDown.get(5));
//			dropDownList.selectByIndex(rand_int);
		}
		public void clickOnSearchButton ()
		{
			wait = new WebDriverWait (driver, 30);
			wait.until(ExpectedConditions.visibilityOf(searchButton));
			searchButton.click();
			System.out.println("Hotel searched successfully");
		}
		
		public boolean resetVerification ()
		{
			WebElement firstOpt = location.getFirstSelectedOption();
			String byDefaultOpt = firstOpt.getText();
			System.out.println("Expected "+byDefaultOpt);
			location.selectByIndex(5);
			WebElement option = location.getFirstSelectedOption();
			String selectedOpt = option.getText();
			System.out.println(selectedOpt);
			
			WebElement firstOpt1 = hotels.getFirstSelectedOption();
			String byDefaultOpt1 = firstOpt1.getText();
			System.out.println("Expected "+byDefaultOpt1);
			hotels.selectByIndex(1);
			WebElement option1 = hotels.getFirstSelectedOption();
			String selectedOpt1 = option1.getText();
			System.out.println(selectedOpt1);
			
			ArrayList<String> userData = new ArrayList<String> ();
			userData.add(selectedOpt);
			userData.add(selectedOpt1);
			ArrayList <String> defaultData = new ArrayList<String> ();
			defaultData.add(byDefaultOpt);
			defaultData.add(byDefaultOpt1);
			
			boolean reset = userData.equals(defaultData);
			return reset;
		}
		
		public void clickOnResetButton ()
		{
			resetButton.click();
		}
		
		public void clickOnBookeditinrary ()
		{
			bookedItinrary.click();
			System.out.println("Booked Itinrary opened successfully");
		}
		
		public void clickOnLogout () throws InterruptedException
		{
//			wait = new WebDriverWait (driver, 20);
//			wait.until(ExpectedConditions.visibilityOf(logout));
//			Thread.sleep(4000);
			logout.click();
			System.out.println("Logout");
		}
		
//		public void closetheApplication()
//		{
//			driver.close();
//		}	
}
