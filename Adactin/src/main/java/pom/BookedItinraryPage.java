package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookedItinraryPage {
	private WebDriver driver;
	@FindBy (xpath ="(//input[@type='checkbox'])[2]")
	private WebElement checkBox;
	@FindBy (xpath = "(//input[@type='submit'])[2]")
	private WebElement cancelButton;
	@FindBy (xpath= "(//form[@id='booking_form']//td)[1]")
	private static  WebElement bookingConfTitle;
	
	public BookedItinraryPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public static  String bookConfirmText ()
	{
		return bookingConfTitle.getText();
	}
	public void cancelOrder ()
	{
		checkBox.click();
		cancelButton.click();
		driver.switchTo().alert().accept();
		System.out.println("Booked order cancelled successfully");
	}
}
