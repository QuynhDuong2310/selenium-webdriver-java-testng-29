package webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.BreakIterator;
import java.time.Duration;
import java.util.List;


public class Topic_07_WebElement_Commands_2_Exercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();


    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement mailTextBox = driver.findElement(By.cssSelector("input#mail"));
        if (mailTextBox.isDisplayed()) {
            mailTextBox.sendKeys("Automation Testing");
            System.out.println("Mail textbox is displayed on GUI");
        } else {
            System.out.println("Mail textbox is NOT displayed on GUI");
        }

        WebElement ageRadioButon = driver.findElement(By.cssSelector("input#under_18"));
        if (ageRadioButon.isDisplayed()) {
            ageRadioButon.click();
            System.out.println("Age Radio Buton is displayed on GUI");
        } else {
            System.out.println("Age Radio Buton is NOT displayed on GUI");
        }


        WebElement eduTextArea = driver.findElement(By.cssSelector("textarea#edu"));
        if (eduTextArea.isDisplayed()) {
            eduTextArea.sendKeys("Automation Testing");
            System.out.println("eduTextArea is displayed on GUI");
        } else {
            System.out.println("eduTextArea is NOT displayed on GUI");
        }

        WebElement nameImage = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if (nameImage.isDisplayed()) {
            nameImage.sendKeys("Automation Testing");
            System.out.println("nameImage is displayed on GUI");
        } else {
            System.out.println("nameImage is NOT displayed on GUI");
        }

    }

    @Test
    public void TC_02_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement mailTextBox = driver.findElement(By.cssSelector("input#mail"));
        if (mailTextBox.isEnabled()) {
            System.out.println("Mail textbox is enabled on GUI");
        } else {
            System.out.println("Mail textbox is disabled on GUI");
        }

        WebElement passwordTextBox = driver.findElement(By.cssSelector("input#disable_password"));
        if (passwordTextBox.isEnabled()) {
            System.out.println("Password textbox is enabled on GUI");
        } else {
            System.out.println("Password textbox is disabled on GUI");
        }


        WebElement interestCheckBox = driver.findElement(By.cssSelector("input#check-disbaled"));
        if (interestCheckBox.isEnabled()) {
            System.out.println("interestCheckBox textbox is enabled on GUI");
        } else {
            System.out.println("interestCheckBox textbox is disabled on GUI");
        }
    }

    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.xpath("//label[@for='java']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//label[@for='java']")).isSelected());

        driver.findElement(By.xpath("//label[@for='java']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//label[@for='java']")).isSelected());
        sleepInSecond(2);
    }

    @Test
    public void TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");


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