package webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Topic_06_Webbrowser_Commands {
    // Các câu lệnh để thao tác với trình duyệt thì nó đứng sau driver
    // driver.
    WebDriver driver;

    FirefoxDriver ffdriver;
    ChromeDriver chDriver;

    EdgeDriver edgeDriver;


    // Các câu lệnh thao tác với Element
    // element.

    WebElement element;


    // Class inherit 1 class >> extends
    // Class inherit 1 interface >> implements
    // Interface inherit 1 interface >> extends
    // Interface does not inherit a class
    // One class can not inherit > one class, but a class can implements more than one interface.





    @BeforeClass
    public void beforeClass() {
        // Muốn dùng được thì phải khởi tạo
        // Nếu không khoeir tạo sẽ gặp lỗi: NullPointerException (Của java)
        // NullPointerException: 1 biến chưa được khởi tạo đã được gọi ra để dùng


        driver = new FirefoxDriver();
        // Version 3/2/1
        // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // Version 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");



    }

    @Test
    public void TC_01() {
    driver.get("");

        // Trong trường hợp có 1 tab/1 window >> close() vs quit() có value ngang nhau
        // Trong trường hợp có  nhiêều hơn 1 tab/1 window >> close() đóng active tab
    driver.close();

        // Đóng tất cả các tab.window đang mở
    driver.quit();

        // 2 hàm findElement & findElements : Sẽ bị ảnh hưởng bởi timeout của hàm implicitWait();
        // implicitWait() - Wait for element
    WebElement name = driver.findElement(By.name(""));

    List<WebElement> nameList = driver.findElements(By.name(""));
    nameList.get(1).click();


    }



    @Test
    public void TC_02() throws MalformedURLException {
        // Get the current titile of the current page
        driver.getTitle();

        // Get the current ID of the current active window
        String getWindowID = driver.getWindowHandle();

        // Cookies / Framework
        driver.manage().getCookies();

        // Get ra những log ở Dev Tool - Framework
        driver.manage().logs().get(LogType.DRIVER);

        // Apply cho việc tìm element (findElement / findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Cho cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Set trước khi dùng vs thư viện JavascriptExecutor
        // Inject 1 đoạn code JS vào trong Browser / Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // Chạy full màn hình
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        // Test Responsive
        driver.manage().window().setSize(new Dimension(1366, 768));

        driver.manage().window().getSize();

        // Set cho breowser ở vị trí nào so với độ phân giải của màn hình
        driver.manage().window().setPosition(new Point(2,4));


        // Điều hướng trang web
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        // Thao tác với history của web ppage
        driver.navigate().to("https://www.facebook.com/?locale=vi_VN");
        driver.navigate().to(new URL("https://www.facebook.com/?locale=vi_VN"));

        // Alert / Window (Tab) / Frame (iFrame)
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("");

        driver.switchTo().window(getWindowID);

        // Switch / handle frame - iFrame
        // Index / ID / Name (element)
        driver.switchTo().frame(1);
        driver.switchTo().frame("abc");
        driver.switchTo().frame(driver.findElement(By.id("abc")));

        // Switch về HTML chưa frame trước đó
        driver.switchTo().defaultContent();

        // Từ Frame trong đi ra frame ngoài chứa nó
        driver.switchTo().parentFrame();

    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}