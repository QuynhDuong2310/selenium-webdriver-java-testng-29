

package webdriver;

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

public class Topic_15_Popup {
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
    public void TC_01_Fixed_Popup_In_DOM_01() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
        WebElement loginPopup = driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div"));
        Assert.assertTrue(loginPopup.isDisplayed());
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input[id='account-input']")).sendKeys("fcautomation");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input[id='password-input']")).sendKeys("fcautomation");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
        String errorMessage = explicitWait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")))).getText();
        System.out.println(errorMessage);
        Assert.assertEquals(errorMessage, "Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        Assert.assertFalse(loginPopup.isDisplayed());

    }

    @Test
    public void TC_02_Fixed_Popup_In_DOM_02() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.cssSelector("div.nav-item a.login-btn")).click();
        WebElement kynaLoginPopup = driver.findElement(By.cssSelector("div.k-popup-account-mb-content"));
        Assert.assertTrue(kynaLoginPopup.isDisplayed());
        driver.findElement(By.cssSelector("div.k-popup-account-mb-content input#user-login")).sendKeys("fcautomation");
        driver.findElement(By.cssSelector("div.k-popup-account-mb-content input#user-password")).sendKeys("fcautomation");

        driver.findElement(By.cssSelector("div.k-popup-account-mb-content button#btn-submit-login")).click();
        String kynaErrorMessage = explicitWait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div.k-popup-account-mb-content div#password-form-login-message")))).getText();
        System.out.println(kynaErrorMessage);
        Assert.assertEquals(kynaErrorMessage, "Sai tên đăng nhập hoặc mật khẩu");
        driver.findElement(By.cssSelector("div.k-popup-account-mb-content button.k-popup-account-close")).click();
        Assert.assertFalse(kynaLoginPopup.isDisplayed());
        // Làm sao để verify đc 1 element khi nó k còn trong DOM?
        //boolean elementStatus = explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.k-popup-account-mb-content div#password-form-login-message"))));
        boolean elementStatus = explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.k-popup-account-mb-content div#password-form-login-message")));
        Assert.assertTrue(elementStatus);
        List<WebElement> popup = driver.findElements(By.xpath(""));
        Assert.assertEquals(popup.size(), 0);

    }

    @Test
    public void TC_03_Fixed_Popup_Not_In_DOM() {
        driver.get("");
    }

    @Test
    public void TC_04_Random_Popup_In_DOM() {
        driver.get("https://www.javacodegeeks.com/");
        // Nếu popup is not displayed >> is the testcase will be marked as fail?
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        List<WebElement> popupA = driver.findElements(By.xpath("//div[@class='lepopup-popup-container']//div[@class='lepopup-form-inner']/parent::div[not(contains(@style,'display:none;'))]"));
        System.out.println(popupA.size());
        if (!popupA.isEmpty()) {
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lepopup-popup-container']//div[@class='lepopup-form-inner']/parent::div[not(contains(@style,'display:none;'))]")).isDisplayed());
            driver.findElement(By.xpath("//div[@class='lepopup-popup-container']//div[@class='lepopup-form-inner']/parent::div[not(contains(@style,'display:none;'))]//div[@class='lepopup-element-html-content']//a[text()='×']")).click();
            sleepInSecond(3);
            Assert.assertEquals(popupA.size(), 1);
            System.out.println("Tới đây rồi nè Q!");
        } else {
            driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
            //ul[@id='posts-container']//li
            driver.findElement(By.xpath("//button[@id='search-submit']")).click();
            String resultSearchList = driver.findElement(By.xpath("//ul[@id='posts-container']/li[1]//h2[@class='post-title']//a")).getText();
            Assert.assertEquals(resultSearchList, "Agile Testing Explained");
            System.out.println("Tới đây rồi nè Q ơi!");
        }
    }

    @Test
    public void TC_05_Random_Popup_In_DOM() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.cssSelector("div.nav-item a.login-btn")).click();
        List<WebElement> kynaLoginPopup = driver.findElements(By.cssSelector("div.k-popup-account-mb-content"));
        System.out.println(kynaLoginPopup.size());
        if (!kynaLoginPopup.isEmpty()) {
            driver.findElement(By.cssSelector("div.k-popup-account-mb-content button.k-popup-account-close")).click();
            sleepInSecond(3);
            Assert.assertEquals(kynaLoginPopup.size(), 1);
            System.out.println("Tới đây rồi nè Q!");
        } else {
            System.out.println("Tới đây rồi nè Q ơi!");
        }
    }

    @Test
    public void TC_06_Random_Popup_In_DOM() {
        driver.get("https://vnk.edu.vn/");
        // div.thrv_wrapper.thrv-columns
        List<WebElement> eduRandomPopup = driver.findElements(By.cssSelector("div.thrv_wrapper.thrv-columns"));
        System.out.println(eduRandomPopup.size());
        if (!eduRandomPopup.isEmpty()) {
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.tve_p_lb_inner"))));
            Assert.assertTrue(driver.findElement(By.cssSelector("div.tve_p_lb_inner")).isDisplayed());
            sleepInSecond(3);
            driver.findElement(By.xpath("//*[local-name()='svg' and @data-name='closeclose']")).click();
            sleepInSecond(3);
            Assert.assertEquals(eduRandomPopup.size(), 1);
            System.out.println("Tới đây rồi nè Q!");
        } else {
            System.out.println("Tới đây rồi nè Q ơi!");
        }
    }

    @Test
    public void TC_07_Random_Popup_Not_In_DOM() {
        driver.get("https://www.javacodegeeks.com/");
        By randomJavaBy = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
        if (driver.findElements(randomJavaBy).size() > 0 && driver.findElements(randomJavaBy).get(0).isDisplayed()) {
            // Vẫn có trong DOM nhưng không hiển thị lên UI >> Dùng hàm isDisplayed thì vẫn có thể kiểm tra đc
            // Nhunge nếu mà page chưa load xong >> popup chưa đc render ra >> Thì cái popup sẽ k có trong DOM >> Dẫn đến hàm findElement() sẽ đánh fail test case.
            System.out.println("Popup hiển thị");
            driver.findElement(By.xpath("//a[contains(text(),'×')]")).click();
            sleepInSecond(3);
        }
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.xpath("//button[@id='search-submit']")).click();
        String resultSearchList = driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).getText();
        Assert.assertEquals(resultSearchList, "Agile Testing Explained");

    }

    @Test
    public void TC_08_Random_Popup_In_DOM() {
        driver.get("https://vnk.edu.vn/");
        sleepInSecond(30);
        By marketingPopup = By.cssSelector("div.tve-leads-conversion-object");
        if (driver.findElement(marketingPopup).isDisplayed()) {
            driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
            sleepInSecond(3);
            System.out.println("Popup hiển thị");
        } else {
            System.out.println("Popup không hiển thị");
        }
        driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Lịch Khai Giảng");
    }

    @Test
    public void TC_09_Random_Popup_Not_In_DOM() {
        driver.get("https://dehieu.vn/");
        By marketingPopup = By.cssSelector("div.popup-content");
        if (driver.findElements(marketingPopup).size() > 0 && driver.findElements(marketingPopup).get(0).isDisplayed()) {
            System.out.println("Popup hiển thị");
            int heightBrowser = driver.manage().window().getSize().getHeight();
            if (heightBrowser < 1920) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click()", driver.findElement(By.cssSelector("button#close-popup")));
            } else {
                driver.findElement(By.cssSelector("button#close-popup")).click();
            }
            sleepInSecond(3);
        }
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