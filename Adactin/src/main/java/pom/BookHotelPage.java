package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookHotelPage {
		private WebDriver driver;
		private WebDriverWait wait;
		@FindBy (xpath = "//input[@id='first_name']")
		private WebElement firstName;
		@FindBy (xpath = "//input[@id='last_name']")
		private WebElement lastName;
		@FindBy (xpath = "//textarea[@id='address']")
		private WebElement billAddress;
		@FindBy (xpath = "//input[@id='cc_num']")
		private WebElement creditCardNo;
		@FindBy (xpath = "//select")
		private List<WebElement> dropdown;
		@FindBy (xpath = "//input[@id='cc_cvv']")
		private WebElement ccvNo;
		@FindBy (xpath = "//input[@id='book_now']")
		private WebElement bookNowButton;
		@FindBy (xpath = "//input[@id='cancel']")
		private WebElement cancelButton;

		
		public BookHotelPage (WebDriver driver)
		{
			PageFactory.initElements(driver, this);
			this.driver = driver;
		}
		
		public void bookHotelFields (String fname, String lname, 
				String add, String cCardNo, String ccvN) throws Throwable		{
			firstName.sendKeys(fname);
			lastName.sendKeys(lname);
			billAddress.sendKeys(add);
			creditCardNo.sendKeys(cCardNo);
			Select creditCardType = new Select (dropdown.get(0));
			creditCardType.selectByVisibleText("Master Card");
			Select expMonth = new Select (dropdown.get(1));
			expMonth.selectByVisibleText("December");
			Select expYear= new Select (dropdown.get(2));
			expYear.selectByVisibleText("2025");
			
			ccvNo.sendKeys(ccvN);
	//		Thread.sleep(4000);
		}
		public void clickOnBookNowButton()
		{
			bookNowButton.click();
			System.out.println("Hotel booked successfully");
		}
		public void clickOnCancelButton()
		{
			cancelButton.click();
		}
}
