package org.example.wait;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void visibleAfter() {

        driver.get("https://demoqa.com/dynamic-properties");
        By button = By.cssSelector("#visibleAfter");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        WebElement visibleButton = driver.findElement(button);
        assertTrue(visibleButton.isDisplayed(), " Кнопка не явилась после 5 сек");

    }

    @Test
    public void enable() {


        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement disableButton = driver.findElement(By.cssSelector("#enableAfter"));
        wait.until(ExpectedConditions.elementToBeClickable(disableButton));
        assertTrue(disableButton.isEnabled(), "Кнопка стала активной");

    }

    @Test
    public void hidden() {

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        driver.findElement(By.cssSelector("#start button")).click();
        WebElement helloText = driver.findElement(By.cssSelector("#finish h4"));
        wait.until(ExpectedConditions.visibilityOf(helloText));
        assertTrue(helloText.isDisplayed(), "Нет нужного текста");

    }

    @Test
    public void notLoaded() {

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        driver.findElement(By.cssSelector("#start button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#finish h4")));
        assertTrue(driver.findElement(By.cssSelector("#finish h4")).isDisplayed(), "Нет нужного текста");

    }

        @Test
    public void toolsMenu() {

        WebElement menuPopup = driver.findElement(By.cssSelector("div[data-test='main-submenu']"));
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {


        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}
