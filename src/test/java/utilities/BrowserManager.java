package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class BrowserManager {
	
	
	public  WebDriver driver;
	
	
	@BeforeTest
	public void lunchBrowser() {
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev.automationtesting.in/");
		
	}

}
