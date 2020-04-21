package PageObjects;

import Utility.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class SignPage extends Base {
    public WebDriver driver;

    public SignPage(WebDriver driver) {
        this.driver = driver;
    }

    public void requestSiteLogin() throws IOException {
        driver.findElement(By.id("ap_email")).sendKeys(getPropertyValue("logindetails", "email"));
        driver.findElement(By.id(("continue"))).click();
        driver.findElement(By.id("ap_password")).sendKeys(getPropertyValue("logindetails", "password"));
        driver.findElement(By.id("signInSubmit")).click();
    }
    public String getPageTitle() {
        return driver.getTitle();
    }
}
