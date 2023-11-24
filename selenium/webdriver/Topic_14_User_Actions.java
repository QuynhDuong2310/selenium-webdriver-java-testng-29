package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_14_User_Actions {
    WebDriver driver;
    Actions actions; // Khai báo

    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        //System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new FirefoxDriver();
        actions = new Actions(driver); // Khởi tạo
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Hover_Tooltip() {
        // Gọi thông qua 1 đối tượng có kiểu dữ liệu là Actions
        // Các thứ 2 là mình new Actions luôn mà không cần thông qua 1 đối tượng :new Actions(driver).
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        actions.moveToElement((ageTextbox)).perform();
        sleepInSecond(2);
        WebElement ageTooltip = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']"));
        Assert.assertTrue(ageTooltip.isDisplayed());
        Assert.assertEquals(ageTooltip.getText(), "We ask for your age only for statistical purposes.");


    }

    @Test
    public void TC_02_Hover_Menu() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        sleepInSecond(2);
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='']"))).perform();
        sleepInSecond(2);

        String subMenuText = "Thiết Bị Số - Phụ Kiện Số";
        actions.click(driver.findElement(By.xpath("//a[@title='']"))).perform();
        sleepInSecond(2);

        // Khi mà gettext() >> Thì hàm gettext() sẽ lấy ra text ở trên UI
        // Nếu dùng text ở xpath - Text này sẽ lấy ở html >> Thì dùng hàm isDisplayed()  >> Vì hàm isDisplayed() sẽ check text ở dưới html.
        /* Trong case này:
        Nếu mình dùng getText() và isDisplayed >> Mình sẽ lấy expectedText = text trên UI để assert.
        // isDisplayed()
            - Return : True >> Element có trên DOM & có hiển thị trê UI
            - Return : False >> Element có trên DOM & Không hiển thị trê UI

       */
        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(), subMenuText);
    }

    @Test
    public void TC_03_Click_And_Hold_By_Block() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> selectableOptions = driver.findElements(By.xpath("//ol//li"));
        int itemZise = selectableOptions.size();
        Assert.assertEquals(itemZise, 20);
        // Chọn theo block: Ngang or dọc
        // Chọn từ 1 -15
        // List, Set, Queue, Array: Lưu value theo dạng index. Bắt đầu từ 0. Element n >> index = n-1
        actions.clickAndHold(selectableOptions.get(0)).pause(2000).moveToElement(selectableOptions.get(14)).pause(2000).release().perform();

        List<String> allNumberExpected = new ArrayList<>();
        allNumberExpected.add("1");
        allNumberExpected.add("2");
        allNumberExpected.add("3");
        allNumberExpected.add("5");
        allNumberExpected.add("6");
        allNumberExpected.add("7");
        allNumberExpected.add("9");
        allNumberExpected.add("10");
        allNumberExpected.add("11");
        allNumberExpected.add("13");
        allNumberExpected.add("14");
        allNumberExpected.add("15");

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),12);

        List<String> allNumberActual = new ArrayList<>();
        for(WebElement element : allNumberSelected) {
            allNumberActual.add(element.getText());
        }
        Assert.assertEquals(allNumberActual, allNumberExpected);

    }

    @Test
    public void TC_04_Click_And_Hold_To_Any_Element() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
    /*    String osName = System.getProperty("os.user");
        Keys keys;
        if (osName.startsWith("Windows")){
            keys= Keys.CONTROL;
        } else {
            keys= Keys.COMMAND;
        }*/
        List<WebElement> selectableOptions = driver.findElements(By.xpath("//ol//li"));
        actions.clickAndHold(selectableOptions.get(0)).pause(2000).moveToElement(selectableOptions.get(11)).pause(2000).release().perform();

        actions.keyDown(cmdCtrl).perform(); // Nhấn phím ctrl down >> Nhưng mà chưa nhả chuột ra.
        actions.click(selectableOptions.get(12)).click(selectableOptions.get(13)).click(selectableOptions.get(14)).keyUp(cmdCtrl).perform();

    }

    @Test
    public void TC_05_Click_And_Hold_To_Any_Element_Using_For_Loop() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
        List<WebElement> selectableOptions = driver.findElements(By.xpath("//ol//li"));
        for (int i= 0; i< selectableOptions.size(); i++){
            if (i < 15){
                actions.keyDown(cmdCtrl).perform();
                actions.click(selectableOptions.get(i)).perform();
            }
        }
    }

    @Test
    public void TC_06_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement myElement  = explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Double click me']"))));
        if(driver.toString().contains("firefox")){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myElement);
            sleepInSecond(2);
        } else {
            actions.scrollToElement(myElement).perform();
            sleepInSecond(2);
        }
        actions.doubleClick(myElement).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).getText(), "Hello Automation Guys!");

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