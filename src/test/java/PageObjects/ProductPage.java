package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    public WebDriver driver;

    private String productTitle = "//span[@id='productTitle']";
    private String addtoWishListButton = "//*[@id='wishListMainButton-announce']";
    private String lateLoginAddToList = "//input[@id='add-to-wishlist-button-submit']";
    private String viewList = "//a[contains(@id,'viewlist')]";
    private String addList = "//div[@id='wishlistButtonStack']//a";

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleFromProductSelectionPage() {
        return driver.findElement(By.xpath(productTitle)).getText();
    }

    public void addProductToList() {
        driver.findElement(By.xpath(addtoWishListButton)).click();
    }

    public void addProductToListForLoggedInUser() {
        driver.findElement(By.xpath(addList)).click();
    }
    public void clickLateLoginAddToList() {
        driver.findElement(By.xpath(lateLoginAddToList)).click();
        driver.findElement(By.xpath(viewList)).click();
    }
}
