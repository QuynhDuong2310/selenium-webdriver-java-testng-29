package webdriver;



import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_00_Template {
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

        driver.manage().window().maximize();

    }

    @Test
    public void TC_01() {
        //Selenium version 1x/2x/3x/4x
        // 8 loại locator
        // Selenium locator = Html attribute
        // Id/Class/Name = trùng với 3 attribute của html
        //Linktext / Partial linktext: html link (Thẻ a)
        //Tagname: Html tagname
        //Css/Xptah


        //Selenium version 4: GUI (Graphic User Interface)
        // Class - Relative Locator
        // Above / Bellow / leftof / Rightof

        // UI testing

    }



    @Test
    public void TC_02() {

    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}