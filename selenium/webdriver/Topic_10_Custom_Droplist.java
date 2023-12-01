package Exercise;


import org.openqa.selenium.By;
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


public class Topic_10_Custom_Droplist {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();


    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDroplist("//span[@id='number-button']", "ul#number-menu li div", "8");

        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button .ui-selectmenu-text")).getText(), "8");

    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDroplist("//div[text()='Select Friend']", "div.visible.menu.transition span", "Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown>div.divider.text")).getText(), "Justen Kitsune");


    }

    @Test
    public void TC_03_VuejJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDroplist("//li[@class='dropdown-toggle']", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
    }

    @Test
    public void TC_04_Editabledroplist() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditableDroplist("//input[@class='search']", "div.item span", "Algeria");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
        //Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailAddress);
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

    public void selectItemInDroplist(String parentXpathLocator, String childCssLocator, String expectedItem) {
        driver.findElement(By.xpath(parentXpathLocator)).click();
        sleepInSecond(2);
        List<WebElement> allITemsInDroplist = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCssLocator)));
        for (WebElement item : allITemsInDroplist) {
            if (item.getText().equals(expectedItem)) {
                item.click();
                sleepInSecond(2);
                break;


            }

        }

    }

    public void selectItemInEditableDroplist(String parentXpathLocator, String childCssLocator, String expectedText) {
        driver.findElement(By.xpath(parentXpathLocator)).clear();
        driver.findElement(By.xpath(parentXpathLocator)).sendKeys(expectedText);
        sleepInSecond(2);
        List<WebElement> allITemsInDroplist = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCssLocator)));
        for (WebElement item : allITemsInDroplist) {
            if (item.getText().equals(expectedText)) {
                item.click();
                sleepInSecond(2);
                break;


            }

        }

    }
}