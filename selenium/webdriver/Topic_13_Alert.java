package webdriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        // Chờ cho alert present
        // Nếu trong thời gian chờ mà alert xuất hiện >> Tự switch vào alert
        // Nếu reach timeout >> fail
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        // Khi mình accept/dismiss >> Alert shall be disappeared from the UI.
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Ok");
        System.out.println(driver.findElement(By.xpath("//p[@id='result']")).getText());

    }
    @Test
    public void TC_02_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String valueToSend = "Quynh Duong";
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        // Chờ cho alert present
        // Nếu trong thời gian chờ mà alert xuất hiện >> Tự switch vào alert
        // Nếu reach timeout >> fail
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        alert.sendKeys(valueToSend);

        // Khi mình accept/dismiss >> Alert shall be disappeared from the UI.
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + valueToSend);
        System.out.println(driver.findElement(By.xpath("//p[@id='result']")).getText());

    }
    @Test
    public void TC_03_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        // Chờ cho alert present
        // Nếu trong thời gian chờ mà alert xuất hiện >> Tự switch vào alert
        // Nếu reach timeout >> fail
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        // Khi mình accept/dismiss >> Alert shall be disappeared from the UI.
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).getText(), "You clicked an alert successfully");
    }
    @Test
    public void TC_04_Authentication_Alert() {
        String userName = "admin";
        String password = "admin";
        driver.get("https://" + userName +":"+ password + "@" + "the-internet.herokuapp.com/basic_auth");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInSecond(long sleepInSecond){
        try {
            Thread.sleep(sleepInSecond * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}