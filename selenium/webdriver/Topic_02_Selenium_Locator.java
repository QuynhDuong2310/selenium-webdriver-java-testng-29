package webdriver;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");


    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }


        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");


    }
// TestNG : Order the test case to run (0-9, a-z)
// Driver: Variable
// Selenium locator by:
// Class By, 8 loai locator >> nằm trong class By
// C: Class
// f: Biến final
// m: Method
// Trong folder
    @Test
    public void TC_01_ID_Locator() {
        driver.findElement(By.id("FirstName"));
        System.out.println(driver.findElement(By.id("FirstName")));
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
        System.out.println(driver.findElement(By.className("header-logo")));
    }
    @Test
    public void TC_03_Name() {
       driver.findElement(By.name("LastName"));
        System.out.println(driver.findElement(By.name("LastName")));
    }
    @Test
    public void TC_04_Css() {
       //Css vs ID
        driver.findElement((By.cssSelector("input#FirstName")));
        driver.findElement((By.cssSelector("#FirstName")));
        driver.findElement((By.cssSelector("input[id='FirstName']")));

        //Css vs class

        driver.findElement((By.cssSelector("div.page-title")));
        driver.findElement((By.cssSelector(".page-title")));
        driver.findElement((By.cssSelector("div[class='page-title']")));

        //Css vs Name
        driver.findElement((By.cssSelector("input[name='FirstName']")));

        //Css vs link - It only works will href tag
        driver.findElement((By.cssSelector("a[href='/customer/addresses']")));
        //Css vs  partial link - It only works will href tag
        driver.findElement((By.cssSelector("a[href*='/customer/addresses']")));
        driver.findElement((By.cssSelector("a[href^='/customer/addresses']")));
        driver.findElement((By.cssSelector("a[href$='/customer/addresses']")));


    }
    @Test
    public void TC_05_Xpath() {
        //Xpath vs ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        //Xpath vs class
        driver.findElement(By.xpath("//input[@class='search-box-text ui-autocomplete-input']"));

        //Xpath vs Name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        //Xpath vs tagname
        driver.findElement(By.xpath("//input"));

        //Xpath vs link
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));

        //Xpath vs partial link
        driver.findElement(By.xpath("//a[contains(@href,'/customer/addresses')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}