package StepDefinitions;

import PageObjects.*;
import Utility.Base;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class AmazonProductCheckOutSteps extends Base {

    public WebDriver driver;
    AmazonPage amazonPage;
    HomePage homePage;
    ProductPage productPage;
    SignPage signPage;
    YourListPage yourListPage;

    public String randomItemSelectedInHomePage;

    public AmazonProductCheckOutSteps() {
        driver = Hooks.driver;
    }

    @Given("^I am on amazon$")
    public void i_am_on_amazon() {
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.loadPageOnBrowser().contains("Amazon.com: Online Shopping"));
    }

    @When("^I search for \"([^\"]*)\"$")
    public void i_search_for(String item) throws Throwable {
        homePage.searchitem(item);
        Assert.assertTrue(homePage.getUpperresultInfodetails().contains(item));
    }

    @And("^I select a random item from results$")
    public void i_select_a_random_item_from_results() throws InterruptedException {
        productPage= new ProductPage(driver);
        randomItemSelectedInHomePage = homePage.selectRandomItemFromResult();
        Assert.assertTrue(productPage.getTitleFromProductSelectionPage().contains(randomItemSelectedInHomePage));
    }

    @When("^I request item to be added to cart$")
    public void i_request_item_to_be_added_to_cart() {
        productPage.addProductToList();
    }

    @Then("^I should be prompted to login to Amazon$")
    public void i_should_be_prompted_to_login_to_amazon() throws Throwable {
        signPage= new SignPage(driver);
        signPage.getPageTitle();
        signPage.requestSiteLogin();
    }

    @When("^I successfully login to site item should be added to cart$")
    public void i_successfully_login_to_site_item_should_be_added_to_cart() {
        yourListPage = new YourListPage(driver);
        productPage.clickLateLoginAddToList();
        String itemInCart = yourListPage.getAddedProductFromWishListToCart();
    }

    @When("^I successfully do early login to site$")
    public void i_successfully_do_early_login_to_site() throws IOException {
        signPage = new SignPage(driver);
        homePage.clickEarlyLogin();
        signPage.requestSiteLogin();
    }

    @Then("^I add random item to cart$")
    public void I_add_random_item_to_cart() {
        productPage.clickLateLoginAddToList();
        //amazonPage.checkItemIsPresentInCart();
    }

    @And("I should be able to check out the product$")
    public void i_should_be_able_to_check_out_the_product() {
        yourListPage = new YourListPage(driver);
        String itemInCart = yourListPage.getAddedProductFromWishListToCart();
    }
}
