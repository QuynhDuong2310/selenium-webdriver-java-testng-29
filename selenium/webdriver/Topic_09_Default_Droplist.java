package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_09_Default_Droplist {
    WebDriver driver;
    String firstName = "Quynh", lastName = "Duong", emailAddress = getEmailAddressRandom(), password ="123456";
    String day = "23", month = "November", year = "2000";


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");

    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.className(".ico-register")).click();
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        Select dayOfBirth = new Select(driver.findElement(By.name("DateOfBirthDay")));
        dayOfBirth.selectByVisibleText(day);
        Assert.assertEquals(dayOfBirth.getAllSelectedOptions().size(),32);


        Select monthOfBirth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        monthOfBirth.selectByVisibleText(month);

        Select yearOfBirth = new Select(driver.findElement(By.name("DateOfBirthYear")));
        yearOfBirth.selectByVisibleText(year);


        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("register-button")).click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-body>.result")).getText(), "Your registration completed");


    }


    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.className("ico-login")).click();
        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector(".button-1.login-button")).click();
        sleepInSecond(2);

        driver.findElement(By.className("ico-account")).click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(),day);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(),month);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(),year);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailAddress);







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

    public String getEmailAddressRandom() {
        Random random = new Random();
        // String emaillAddressRandom = "Quynhxoan" + random.nextInt(99999) + "@gmail.com"; (Có thể khai báo biến cho email address)
        return "Quynhxoan" + random.nextInt(99999) + "@gmail.com";
        // return "Quynhxoan" + new Random()nextInt(99999) + "@gmail.com"; (Có thể viết ntn)
    }
}