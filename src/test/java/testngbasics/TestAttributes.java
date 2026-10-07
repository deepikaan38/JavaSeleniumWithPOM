package testngbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestAttributes {
	
	WebDriver driver;
	
	@Test(priority = 1)
	public void openTheBrowser() {
		 driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(description="Login with valid creadentials", dependsOnMethods = "openTheBrowser",priority = 2,enabled = false)
	public void login() {
	WebElement username=driver.findElement(By.name("username"));
	username.click();
	username.sendKeys("Admin");
	WebElement password=driver.findElement(By.name("password"));
	password.click();
	password.sendKeys("admin123");
		
	}
	
	@Test(description="Login with invalid creadentials", dependsOnMethods = "openTheBrowser",priority = 2,enabled = true,invocationCount = 2)
	public void login2() {
	WebElement username=driver.findElement(By.name("username"));
	username.click();
	username.sendKeys("Admin");
	WebElement password=driver.findElement(By.name("password"));
	password.click();
	password.sendKeys("admin");
	
		
	}
	
	@Test(priority = 3)
	public void closeTheBrowser() {
		driver.quit();
		
	}
	
	

}
