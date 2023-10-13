package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_08_Textbox_Textarea_Part_2 {
    WebDriver driver;
    String firstName = "Quynh", lastName = "Duong", userName = getUserNameRandom(), passWord = "Quynh123@";
    String numberPassport = "2310", commentImmigration = "Ok";


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");


    }

    @Test
    public void TC_01() {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        sleepInSecond(3);
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        sleepInSecond(3);
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        String employeeId = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
        System.out.println(employeeId);
        driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//div[@class='oxd-switch-wrapper']/label")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Username')]/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div//input[@type='password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div//input[@type='password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div//button[text()=' Add ']/i")).click();
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(numberPassport);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(commentImmigration);
        driver.findElement(By.xpath("//div[@class='oxd-form-actions']//button[@type='submit']")).click();
        sleepInSecond(2);
        driver.findElement(By.cssSelector("i.oxd-icon.bi-pencil-fill")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), numberPassport);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), commentImmigration);
        driver.findElement(By.cssSelector("li.oxd-userdropdown i")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleepInSecond(2);
        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        driver.findElement(By.cssSelector("i.oxd-icon.bi-pencil-fill")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), numberPassport);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), commentImmigration);
















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
    public String getUserNameRandom() {
        Random random = new Random();
        // String emaillAddressRandom = "Quynhxoan" + random.nextInt(99999) + "@gmail.com"; (Có thể khai báo biến cho email address)
        return "Quynhxoan" + random.nextInt(99999);
        // return "Quynhxoan" + new Random()nextInt(99999) + "@gmail.com"; (Có thể viết ntn)
    }
}