package adactin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectHotelPage {
//	private WebDriver driver;
	@FindBy (xpath = "//input[@id='radiobutton_0']")
	private WebElement selectRadioButton;
	@FindBy (xpath = "//input[@id='continue']")
	private WebElement continueButton;
	@FindBy (xpath = "//input[@id='cancel']")
	private WebElement cancelButton;		
	public SelectHotelPage (WebDriver driver) {
		PageFactory.initElements(driver, this);
//		this.driver=driver;
	}	
	public void clickOnCheckBox () {
		selectRadioButton.click();	
	}
	public void clickOnContinueButton () {
		continueButton.click();
		System.out.println("Hotel selected successfully");
	}
	public void clickOnCancelButton () {
		cancelButton.click();
	}
}
