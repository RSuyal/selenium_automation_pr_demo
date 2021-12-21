package customerapp.caobject;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class HPObject{
	

	WebDriver driver;
	@FindBy(xpath = "(//span[@class=\"d-none d-md-inline-block\"])[2]")
	WebElement SupportEmailLinktext;
	
	
	public HPObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
		
		
	public String SEmailXpath = "(//span[@class=\"d-none d-md-inline-block\"])[2]";
	
	//public WebElement SupportEmailLinktext = driver.findElement(By.xpath(SEmailXpath));


	public String GetSupportEmail() {
//		WebElement SupportEmailLinktext = driver.findElement(By.xpath(SEmailXpath));
		System.out.println("GetSupportEmail method started");
		return SupportEmailLinktext.getText();
		
	}

	
	
}
