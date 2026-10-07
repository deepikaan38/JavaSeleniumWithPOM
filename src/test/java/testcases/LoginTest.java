package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.BrowserManager;

public class LoginTest extends BrowserManager  {
	
	LoginPage lp;
	@BeforeClass
	public void LoginTestObjcet(){
		lp=new LoginPage(driver);
	}
	
	
	@Test
	public void LogintToApplication() {
		lp.loginToQATools();
	}

}
