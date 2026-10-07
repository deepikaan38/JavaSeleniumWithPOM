package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy( xpath = "//input[@type='email']")
	WebElement txtmailid;
	
	@FindBy( xpath = "//input[@type='password']")
	WebElement txtpassword;
	
	@FindBy( xpath = "//button[text()='LOGIN']")
	WebElement btnlogin;
	
	@FindBy( xpath = "//button[text()='SKIP LOGIN']")
	WebElement btnskiplogin;
	
	
	public void loginToQATools() {
		txtmailid.sendKeys("admin@automationtesting.in");
		txtpassword.sendKeys("Admin1234");
		 
	}
	

}
