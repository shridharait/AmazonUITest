package StepDefinitions;

import Utility.Base;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Hooks extends Base {
    public static WebDriver driver;

    @Before
    public void openBrowser() throws IOException {
        createWebDriver();
        //Delete all cookies at the start of each scenario to avoid shared state between tests
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    public void createWebDriver() throws IOException {

        String chromeDriver = "/drivers/chromedriver";
        String firefoxDriver = "/drivers/geckodriver";
        String windowsChromeDriver = "/drivers/chromedriver.exe";
        String windowsFirefoxDriver = "/drivers/geckodriver.exe";
        String driverPath;

        String webdriver = System.getProperty("Browser", "chrome");
        String os = System.getProperty("os", "mac");
        System.out.println("Running tests on " + os + " operating system by launching browser " + webdriver);
        DesiredCapabilities capabilities;

        if (System.getProperty("Run", "local").equalsIgnoreCase("Remote")) {
            if (webdriver.equalsIgnoreCase(("chrome"))) {
                System.out.println("Running test on remote server");
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + chromeDriver);
                capabilities = new DesiredCapabilities();
                capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
                capabilities.setCapability(CapabilityType.PLATFORM_NAME, "MAC");
                capabilities.setCapability(CapabilityType.BROWSER_VERSION, "81");
                driver = new RemoteWebDriver(new URL(getPropertyValue("config", "nodeURL")), capabilities);
            } else if (webdriver.equalsIgnoreCase(("firefox"))) {
                System.out.println("Running test on remote server");
                System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + firefoxDriver);
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
                capabilities.setCapability(CapabilityType.PLATFORM_NAME, "MAC");
                capabilities.setCapability(CapabilityType.BROWSER_VERSION, "81");
                driver = new RemoteWebDriver(new URL(getPropertyValue("config", "nodeURL")), capabilities);
            }

        } else {
            //To run tests on chrome browser
            if (webdriver.equalsIgnoreCase(("chrome"))) {
                if (os.equalsIgnoreCase("windows")) {
                    driverPath = System.getProperty("user.dir") + windowsChromeDriver;
                } else {
                    driverPath = System.getProperty("user.dir") + chromeDriver;
                }
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();
            }

            //To run tests on firefox Browser
            else if (webdriver.equalsIgnoreCase(("firefox"))) {
                if (os.equalsIgnoreCase("windows")) {
                    driverPath = System.getProperty("user.dir") + windowsFirefoxDriver;
                } else {
                    driverPath = System.getProperty("user.dir") + firefoxDriver;
                }
                System.setProperty("webdriver.gecko.driver", driverPath);
                driver = new FirefoxDriver();
            }

            //To run tests on Headless Browser
            else if (webdriver.equalsIgnoreCase(("headless"))) {
                driverPath = System.getProperty("user.dir") + chromeDriver;
                System.setProperty("webdriver.chrome.driver", driverPath);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--start-maximized");
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            } else
                throw new RuntimeException("Unsupported webdriver: " + webdriver);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
