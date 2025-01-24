package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TestBase {
	
	private WebDriver driver;
	private static boolean startBrowser = false;
	
	@Before
	public void beforeMethod() {
		if(!startBrowser) {
			System.getProperty("wedriver.chrome.driver", "C:\\Users\\rangm\\eclipse-workspace\\FISSolutions\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	
	 @After
	    public void tearDown(){
	        if(driver!=null){
	            driver.quit();
	        }
	    }
	 
	 public WebDriver getDriver() {
		 return driver;
	 }

}
