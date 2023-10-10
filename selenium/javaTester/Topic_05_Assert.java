package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;

    @Test
    public void verifyTestNG(){
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/?locale=vi_VN");

        // Trong Java có nhiều thư viện để verify dữ liệu
        // Testing framework(Unit / Intergration / )

        // Kiểu dữ liệu nhận vào là Boolean (True/False)
        // Khi mong muốn điều kiện trả về là đúng thì dùng assertTrue và ngược lại

        Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ với mọi người trong cuộc sống của bạn."));

        // Các kiểu dữ liệu mà trả về là true/fasle
        // Bắt đu bằng : isXXX

        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select(driver.findElement(By.id(""))).isMultiple();

        // Actual = Expected (Tuyệt đối)
        Assert.assertEquals(driver.getCurrentUrl(), "abc");
        Assert.assertEquals(driver.findElement(By.id("")).getText(), "abc");

        // Unit Test
        String name = null;
        Assert.assertNull(name);

        name = "Quynh";
        Assert.assertNotNull(name);




    }

}
