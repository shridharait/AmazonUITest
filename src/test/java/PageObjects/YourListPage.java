package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourListPage {

    public WebDriver driver;

    public YourListPage(WebDriver driver) {
        this.driver = driver;
    }

    private String itemNameInYourLists = "//a[contains(@id,'itemName_')]";
    private String deleteAddedItemFromCart = "//span[contains(@data-action,'reg-item-delete')]";

    public String getAddedProductFromWishListToCart() {
        String itemNameInYourListDescription = driver.findElement(By.xpath(itemNameInYourLists)).getText();
        driver.findElement(By.xpath(deleteAddedItemFromCart)).click();
        return itemNameInYourListDescription;
    }
}
