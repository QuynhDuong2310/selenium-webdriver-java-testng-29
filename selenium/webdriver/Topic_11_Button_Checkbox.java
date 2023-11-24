package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_11_Button_Checkbox {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");
        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
        Assert.assertFalse(registerButton.isEnabled());
        driver.findElement(By.xpath("//input[@type='checkbox' and @id ='chinhSach']")).click();
        sleepInSecond(2);
        Assert.assertTrue(registerButton.isEnabled());
        // HEX : #ef5a00
        //RGBA : rgb(239, 90, 0)
        // Lấy ra màu nền của button
        String registerButtonBackgroundRGB = registerButton.getCssValue("background-color");
        System.out.println("registerButtonBackgroundRGB = " + registerButtonBackgroundRGB);

        // Convert từ kiểu string sang kiểu color >> Bởi vì color này mới có cái hàm để convert sang Hexa.
        Color registerButtonBackgroundColour = Color.fromString(registerButtonBackgroundRGB);
        System.out.println("registerButtonBackgroundColour = " + registerButtonBackgroundColour.asHex());

        Assert.assertEquals(registerButtonBackgroundColour.asHex(), "#ef5a00");





    }


    @AfterClass
    public void afterClass() {

        driver.quit();
    }
    public void sleepInSecond(long sleepInSecond){
        try{
            Thread.sleep(sleepInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}