package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Shadow_Popup {
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
    public void TC_01_Shadow_DOM() {
        // driver = đại diện cho cái real DOM (DOM bên ngoài)
        driver.get("https://automationfc.github.io/shadow-dom");
        // Đi theo đúng cấu trúc của html (DOM)
        // Cái mà chứa shadow root chính là shadow host (Là 1 cái element)
        // shadowRootContext đại diện cho một cái shadow root bên trong
        WebElement shawdowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootContext = shawdowHostElement.getShadowRoot();
        String someText = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        Assert.assertEquals(someText, "some text");
        List<WebElement> inputTagTotal = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(inputTagTotal.size());
        //nestedShadowHostElement = đại diện cho cái nested shadow DOM 2 (Nằm trong shadow DOM 1)
        WebElement nestedShadowHostElement = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowHostElement.getShadowRoot();
        String nestedText = nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        Assert.assertEquals(nestedText, "nested text");

    }

    @Test
    public void TC_02_Shadow_DOM_Shopee() {
        driver.get("https://shopee.vn/");
        WebElement hostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shawdowRootShoppeContext = hostElement.getShadowRoot();
        /*
        Verify popup hiển thị
        Nếu dùng findElement() >> Tại sao lại bị fail test case? nhưng dùng findElements() thì lại pass?
        Tại vì : Căn cứ vào dữ liệu trả về của từng hàm.
        findElement() >> Nếu k tìm thấy element >> Hàm findElement() sẽ fail test case.
        findElements() >> Nếu k tìm thấy element >> Hàm findElements() sẽ không đánh fail test case && trả về list null có size = 0.
        */
        if (!shawdowRootShoppeContext.findElements(By.cssSelector("div.home-popup__content")).isEmpty()
                && shawdowRootShoppeContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()) {
            // Click để close popup đi
            shawdowRootShoppeContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            sleepInSecond(2);
        }
        // Không hiển thị or đã close popup
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iphone 15 pro max");
        sleepInSecond(2);
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
        sleepInSecond(2);
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