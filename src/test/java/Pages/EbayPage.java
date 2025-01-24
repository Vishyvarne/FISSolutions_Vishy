package Pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;

public class EbayPage extends PageBase {
	
	public EbayPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@type='text']")
	private WebElement searchBox;
	
	@FindBy(xpath="//button[contains(@class, 'button') and @value='Search']")
	private WebElement searchButton;
	
	@FindBy(xpath="//ul[contains(@class,'results')]")
	private WebElement results;

	public void launchEbay() {
		try {
			driver.get("https://www.ebay.com/");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchBook() {
		try {
			searchBox.sendKeys("Book");
			searchButton.click();
			waitForElementtoAppear(results, 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickFirstBook() {
		try {
			String defaultWindow = driver.getWindowHandle();
			WebElement firstResult = driver.findElement(By.xpath("((//ul[contains(@class, 'results')]//li)[1]//a)[3]"));
			firstResult.click();
			Set<String> windows = driver.getWindowHandles();
			outer : for(String a : windows) {
				if(!a.equalsIgnoreCase(defaultWindow)) {
					driver.switchTo().window(a);
					break outer;
				}
			}
			WebElement addCart = driver.findElement(By.xpath("//a[contains(@id,'atcBtn_btn_1')]"));
			waitForElementtoAppear(addCart, 30);
			addCart.click();
			WebElement basket = driver.findElement(By.xpath("//span[@class='gh-cart__icon']"));
			waitForElementtoAppear(basket, 30);
			System.out.println(basket.getAttribute("aria-label"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getResponse() {
		try {
			RestAssured.baseURI = "api.coindesk.com/v1/bpi/currentprice.json";
			
			JsonPath response = get("http://api.coindesk.com/v1/bpi/currentprice.json").then().extract().response().jsonPath();
			//System.out.println(response.getString("bpi"));
			
			int bpi = response.getMap("bpi").size();
			System.out.println(bpi);
	        assert bpi == 3 : "Expected 3 currencies in 'bpi', but found " + bpi;
	        
	        String gbpDescription = response.getString("bpi.GBP.description");
	        assertTrue(gbpDescription.equalsIgnoreCase("British Pound Sterling"), "The value from json response is expected");
	        
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
