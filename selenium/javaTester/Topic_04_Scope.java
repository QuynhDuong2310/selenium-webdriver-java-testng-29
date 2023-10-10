package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Scope {
    // Các biến mà được khai báo ở bên ngoài hàm >> Phạm vi class >> Biến global (Toàn cục)
    // Có thẻ dùng cho tất cả các hàm ở trong 1 class đó
    WebDriver driver; // Khai báo (Declare)
    String homeURL;

    String fullname = "Quynh"; // Khai báo + khởi tạo (Innitial)
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

    }

    @Test

    public void TC_01() {
        // Các biến mà được khai báo ở bên trong  hàm >> Phạm vi class >> Biến local (Cục bộ)
        // Chỉ dùng đưuọc trong hàm/block mà biến được khai báo.
        // Trong 1 hàm nếu có 2 biến cùng tên (Global/Local) >> Ưa tiên lấy biến local dùng
        // If the "Local Variable" has not been initialed yet >> (At "Compiler code" level will HIT the error.


        String homeURL = "https://abc.com";
        driver.get(homeURL);

        // If "Globle variable" & "Local Variable" are the same name >> User wants to use the "Global" variable
        // Use this.
        // If the "Global Variable" has not been initialed yet >> (At "Compiler code" level will NOT hit the error
        // At runtime >> It will hit an error.
        driver.get(this.homeURL);

        // Get the curent Url
        driver.getCurrentUrl();

        driver.getPageSource();
        driver.getPageSource().contains("JSONPath Online Evaluator");
        Assert.assertTrue(driver.getPageSource().contains("JSONPath Online Evaluator"));
    }
    public void TC_02() {
        driver.get("https://jsonpath.com/");
        driver.getPageSource();
        driver.getPageSource().contains("JSONPath Online Evaluator");
        Assert.assertTrue(driver.getPageSource().contains("JSONPath Online Evaluator"));

    }
    public void TC_03() {

    }


    @Test
    public void TC_04() {

    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }





















}
