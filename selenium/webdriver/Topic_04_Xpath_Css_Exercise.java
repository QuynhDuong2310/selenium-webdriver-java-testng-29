package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Topic_04_Xpath_Css_Exercise {
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


    }

    @Test
    public void Register_01_Empty_Data(){

        //Action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert

        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");



    }



    @Test
    public void Register_02_Invalid_Email_Address() {
        //Action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Duong Quynh");
        driver.findElement(By.id("txtEmail")).sendKeys("123@123@");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@123@");
        driver.findElement(By.id("txtPassword")).sendKeys("Quynhduong123@");
        driver.findElement(By.id("txtCPassword")).sendKeys("Quynhduong123@");
        driver.findElement(By.id("txtPhone")).sendKeys("0983567678");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");

    }
    @Test
    public void Register_03_Incorrect_Email_Address() {
        //Action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Duong Quynh");
        driver.findElement(By.id("txtEmail")).sendKeys("123@123@");
        driver.findElement(By.id("txtCEmail")).sendKeys("Quynhmot@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0983567");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
    }

    @Test
    public void Register_04_Invalid_Phonenumber() {
        //Action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Duong Quynh");
        driver.findElement(By.id("txtEmail")).sendKeys("Quynhmot@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("Quynhmot@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0983567");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
    }

    @Test
    public void Register_05_Incorrect_ConfirmPassword() {
        //Action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Duong Quynh");
        driver.findElement(By.id("txtEmail")).sendKeys("Quynhmot@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("Quynhmot@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123457");
        driver.findElement(By.id("txtPhone")).sendKeys("0983567678");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");

    }

    @Test
    public void Register_06_Invalid_Password() {
        //Action
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Duong Quynh");
        driver.findElement(By.id("txtEmail")).sendKeys("Quynhmot@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("Quynhmot@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123");
        driver.findElement(By.id("txtCPassword")).sendKeys("123");
        driver.findElement(By.id("txtPhone")).sendKeys("0983567678");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");

    }




    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}