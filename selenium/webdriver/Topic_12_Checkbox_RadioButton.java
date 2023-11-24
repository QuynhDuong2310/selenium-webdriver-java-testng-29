package webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Checkbox_RadioButton {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_DefaultCheckbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckboxValue = driver.findElements(By.xpath(" //label[text()=' Have you ever had (Please check all that apply) ']/following-sibling::div//span[@class='form-checkbox-item']//input"));

        for(WebElement item : allCheckboxValue){
            if(!item.isSelected()){
                item.click();
                sleepInSecond(2);
            }
            Assert.assertTrue(item.isSelected());

        }

    }
    @Test
    public void TC_02_Default_Checkbox(){
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckboxValue = driver.findElements(By.xpath(" //label[text()=' Have you ever had (Please check all that apply) ']/following-sibling::div//span[@class='form-checkbox-item']//input"));

        for(WebElement item : allCheckboxValue){
            if(!item.isSelected()){
                item.click();
                sleepInSecond(2);
            }
            Assert.assertTrue(item.isSelected());

        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        allCheckboxValue = driver.findElements(By.xpath(" //label[text()=' Have you ever had (Please check all that apply) ']/following-sibling::div//span[@class='form-checkbox-item']//input"));

        for(WebElement item : allCheckboxValue){
            if(item.getAttribute("value").equals("Heart Attack") && !item.isSelected()){
                item.click();
                Assert.assertTrue(item.isSelected());
                sleepInSecond(2);
            }
        }
    }


    @Test
    public void TC_03_Custom_Checkbox(){
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
       /*driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).click();
       Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div//input")).isSelected());
       sleepInSecond(2);*/

        //$x(//div[text()='Đăng ký cho người thân']/preceding-sibling::div//input).[0];
        By radiobutton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div//input");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(radiobutton));
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(radiobutton).isSelected());
        sleepInSecond(2);
    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }

    public void sleepInSecond(long sleepInSecond){
        try{
            Thread.sleep(sleepInSecond * 1000);
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }


}