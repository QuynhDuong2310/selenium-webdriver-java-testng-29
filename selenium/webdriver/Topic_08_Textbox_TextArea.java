package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_08_Textbox_TextArea {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();


    }

    @Test
    public void TC_01_Login_With_Empty_Email_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//button[@title='Login']")).click();

        WebElement emailRequiredMessageValidation = driver.findElement(By.id("advice-required-entry-email"));
        Assert.assertEquals(emailRequiredMessageValidation.getText(), "This is a required field.");

        WebElement passwordRequiredMessageValidation = driver.findElement(By.id("advice-required-entry-pass"));
        Assert.assertEquals(passwordRequiredMessageValidation.getText(), "This is a required field.");


    }


    @Test
    public void TC_02_Invalid_Email_Valid_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//button[@title='Login']")).click();
        driver.findElement(By.id("email")).sendKeys("1234@1231245");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        WebElement emailMessageValidation = driver.findElement(By.id("advice-validate-email-email"));
        Assert.assertEquals(emailMessageValidation.getText(), "Please enter a valid email address. For example johndoe@domain.com.");
        Assert.assertEquals(emailMessageValidation.getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//button[@title='Login']")).click();
        driver.findElement(By.id("email")).sendKeys("Quynhxoansh96@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        WebElement passwordMessageValidation = driver.findElement(By.id("advice-validate-password-pass"));
        Assert.assertEquals(passwordMessageValidation.getText(), "Please enter 6 or more characters without leading or trailing spaces.");

    }


    @Test
    public void TC_04_Incorrect_Email_Or_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(2);
        driver.findElement(By.id("email")).sendKeys("quynhxoansh95@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("1234567");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        WebElement InvalidPasswordMessage = driver.findElement(By.xpath("//span[text()='Invalid login or password.']"));
        System.out.println(InvalidPasswordMessage);
        Assert.assertEquals(InvalidPasswordMessage.getText(), "Invalid login or password.");


    }

    @Test
    public void TC_05_Login_Success() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSecond(2);

        String firstName = "Quynh", lastName = "Duong", emailAddress = getEmailAddressRandom(), password = "123456";
        String fullName = firstName + " " + lastName;


        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(emailAddress);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@title='Confirm Password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Log out
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        sleepInSecond(2);

        //Log in
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(2);
        /*driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        sleepInSecond(2);
        WebElement element = driver.findElement(By.xpath("//a[@title='Log In']"));
        Actions act=new Actions(driver);
        act.moveToElement(element).click().perform();
        //driver.findElement(By.xpath("//a[@title='Log In']/parent::li")).click();
        sleepInSecond(2);*/

        driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys(emailAddress);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");

        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSecond(10);
        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);


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