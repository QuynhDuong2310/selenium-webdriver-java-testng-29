package webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Topic_07_WebElement_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");


    }

    @Test
    public void TC_01_Element() {
        // Tim và trả về 1 Element (Textbox, dropdown/checkbox...)
        driver.findElement(By.id(""));

        // Tìm và tương tác với Element
        driver.findElement(By.id("")).click();

        // Tìm và lưu vào 1 biến WebElement
        WebElement fullName = driver.findElement(By.id(""));

        // Tái sử dụng biến
        fullName.sendKeys();


        // clear()
        // to delete/replace/clear the data of a specified field
        // Work with : Textbox/TextArea/Droplist (Editable)
        // Use along with sendKeys() in order to guarantee the data integrity (Tính tò vẹn của dữ liệu)
        driver.findElement(By.id("")).clear();

        // sendKeys() : To enter value for a specified field
        driver.findElement(By.id("")).sendKeys("");

        // click() : To click on a specified element
        driver.findElement(By.id("")).click();

        // Return one matching element that matches with the conditions
        WebElement fullNameTextbox =  driver.findElement(By.id(""));

        // Return all matching elements that matches with the conditions
        List<WebElement> fullNameTextboxList =  driver.findElements(By.id(""));

        // Java non Generic - java generic

        // To verify wherether a checkbox/radio/droplist(Default) is selected or not
        Assert.assertTrue(driver.findElement(By.id("")).isSelected());
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        // Dropdown : Default/Custom
        Select select  = new Select(driver.findElement(By.id("")));

        // To verify a element is presented or not.
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());

        // To verify a element is ready-only or not
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled());

        // HTML element
        driver.findElement(By.id("")).getAttribute("title");

        // Tab Styles - GUI
        // Font / Size / Color / Background
        driver.findElement(By.id("")).getCssValue("");

        // Vị trí của element so ới độ phân giải màn hình
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();

        // Location + Size
        Rectangle nameTextbox = driver.findElement(By.id("")).getRect();

        // Location
        Point namePoint = nameTextbox.getPoint();
        // Size (Height + Width)
        Dimension nameSize = nameTextbox.getDimension();

        // Shawdow element
        driver.findElement(By.id("")).getShadowRoot();

        // Từ id, class, name, css, xpath >> Truy ra được tagname của element đó ở trên HTML
        driver.findElement(By.id("")).getTagName();

        // Get  a visible text
        driver.findElement(By.id("")).getText();

        // chụp hình bị lỗi và lưu lại dưới dạng nào
        // file : Lưu thành hình anh và có kích thước trong ổ cứng
        // base64 : Lưu ảnh thành dạng text >> KHi nhúng vào thẻ hmtl >> Lại convert sang ảnh
        // Bytes :
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BASE64);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BYTES);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.FILE);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}