package PageObjects;

import Utility.Base;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class HomePage extends Base {

    public WebDriver driver;
    private String searchTextBox = "twotabsearchtextbox";
    private String upperWidgetResult = "//span[contains(@class,'RESULT_INFO_BAR')]//div[contains(@class,'a-section a-spacing-small a-spacing-top-small')]";
    private String searchResults = "//div[contains(@class,'s-main-slot s-result-list s-search-results sg-row')]//div[contains(@data-component-type,'s-search-result')]";
    private String earlySignOnButton = "//div[@id='nav-signin-tooltip']//span";

    public int randomNumber;
    public String itemSelected;
    public String selectedItemDescription;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String loadPageOnBrowser() {
        try {
            driver.get(getPropertyValue("config", "amazonUrl"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver.getTitle();
    }

    public void searchitem(String item) {
        this.waitForAWhile(1000);
        driver.findElement(By.id(searchTextBox)).sendKeys(item);
        driver.findElement(By.id(searchTextBox)).sendKeys(Keys.ENTER);
    }

    public String getUpperresultInfodetails() {
        return driver.findElement(By.xpath(upperWidgetResult)).getText();
    }

    // method to select a random product from the result list
    public String selectRandomItemFromResult() {
        this.waitForAWhile(1500);
        List<WebElement> elements = driver.findElements(By.xpath(searchResults));
        randomNumber = getRandomNumberInRange(4, 15);
        System.out.println("generated random number is " + randomNumber);
        String randomSelectionItem = "//div[contains(@class,'s-result-list s-search-results sg-row')]//div[contains(@data-component-type,'s-search-result')][" + randomNumber + "]//span[contains(@class,'a-size-medium a-color-base a-text-normal')]";
        String randomProductSelectedName  = driver.findElement(By.xpath(randomSelectionItem)).getText();
        driver.findElement(By.xpath(randomSelectionItem)).click();
        return randomProductSelectedName;
    }

    public void clickEarlyLogin() {
        driver.findElement(By.xpath(earlySignOnButton)).click();
    }

    public void waitForAWhile(int millisec)  {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //method which generates random number between a range
    public int getRandomNumberInRange(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
