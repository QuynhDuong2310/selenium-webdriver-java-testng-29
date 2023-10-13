package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Topic_10_TC_Login_Viettel {
    WebDriver driver;
    WebDriverWait expliciWait;




    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://viettel.vn/");


    }

    @Test
    public void TC_01() {
        expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='login-register login-register-v5']//li[@class='active']")));
        driver.findElement(By.xpath("//div[@class='login-register login-register-v5']//li[@class='active']")).click();
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='popupSignIn'and @style='display: inline-block;']")).isDisplayed());
        sleepInSecond(5);
        driver.findElement(By.xpath("//label[text()='Số điện thoại']/following-sibling::input[@type='number']")).sendKeys("0338305325");

        sleepInSecond(5);
        driver.findElement(By.xpath("//input[@id='typepass']")).sendKeys("Linhnhiminhanh222@");
        expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='popupSignIn']//a[@class='button button--primary']")));
        sleepInSecond(5);
        driver.findElement(By.xpath("//div[@id='popupSignIn']//a[@class='button button--primary']")).click();
        /*Actions actions = new Actions(driver);
        WebElement elementLocator =  driver.findElement(By.xpath("//div[@id='popupSignIn']//a[@class='button button--primary']"));
        actions.doubleClick(elementLocator).perform();*/
        sleepInSecond(10);

        Assert.assertTrue( driver.findElement(By.cssSelector("div#otp-verification1")).isDisplayed());

        //expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#otp-verification1")));


    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInSecond(long sleepInSecond) {
        try {
            Thread.sleep(sleepInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}