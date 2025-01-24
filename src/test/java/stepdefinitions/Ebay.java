package stepdefinitions;

import org.openqa.selenium.WebDriver;

import Pages.EbayPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Ebay {

	WebDriver driver = null;
	EbayPage ebay = null;
	
	private TestBase base;
	
	public Ebay(TestBase base) {
		this.base = base;
	}
	
	@Given("Launch Ebay")
	public void launchEbay() {
		driver = base.getDriver();
		ebay = new EbayPage(driver);
		ebay.launchEbay();
	}
	
	@Given("Search for Book")
	public void search_for_book() {
	    ebay.searchBook();
	}
	@Then("Click on first book in the list and add to cart")
	public void click_on_first_book_in_the_list() {
	    ebay.clickFirstBook();
	}
	
	@Given("Validate the response from Get call from API")
	public void validate() {
		driver = base.getDriver();
		ebay = new EbayPage(driver);
		ebay.getResponse();
	}
	
	
}
