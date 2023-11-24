package Exercise;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic_02_Custom_RadioButton {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();



    }

    @Test
    public void TC_01_Custom_Radiobutton() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By haNoiRadiobutton = By.xpath("//div[@data-value='Hà Nội']");
        By quanNamCheckbox= By.xpath("//div[@aria-label='Quảng Nam']");
        By quangBinhCheckbo = By.xpath("//div[@aria-label='Quảng Bình']") ;

        Assert.assertEquals(driver.findElement(haNoiRadiobutton).getAttribute("aria-checked"), "false");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Hà Nội' and @aria-checked='false']")).isDisplayed());
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(haNoiRadiobutton));
        sleepInSecond(1);
        Assert.assertEquals(driver.findElement(haNoiRadiobutton).getAttribute("aria-checked"), "true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Hà Nội' and @aria-checked='true']")).isDisplayed());


        Assert.assertEquals(driver.findElement(quanNamCheckbox).getAttribute("aria-checked"), "false");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='false']")).isDisplayed());
        driver.findElement(quanNamCheckbox).click();
        sleepInSecond(1);
        Assert.assertEquals(driver.findElement(quanNamCheckbox).getAttribute("aria-checked"), "true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='true']")).isDisplayed());


        Assert.assertEquals(driver.findElement(quangBinhCheckbo).getAttribute("aria-checked"), "false");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Bình' and @aria-checked='false']")).isDisplayed());
        driver.findElement(quangBinhCheckbo).click();
        sleepInSecond(1);
        Assert.assertEquals(driver.findElement(quangBinhCheckbo).getAttribute("aria-checked"), "true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Bình' and @aria-checked='true']")).isDisplayed());



    }

    @AfterClass
    public void afterClass() {
        driver.quit();
        driver.close();
    }

    public void sleepInSecond(long sleepInSecond) {
        try {
            Thread.sleep(sleepInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}