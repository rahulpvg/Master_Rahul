package adactin.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingConfirmationPage {
	
	@FindBy (xpath = "//*[@id='booking_form']/table/tbody/tr[1]/td")
	private WebElement pageName;

}
