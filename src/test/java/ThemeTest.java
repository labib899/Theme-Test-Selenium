import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WebDriverManager;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;

public class ThemeTest
{
    private WebDriver driver;
    private WebDriverWait wait;

    private void pause()
    {
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void switchToTheme(String themeSelector)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement themeSwitcher = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".theme-switcher-menu")));
        themeSwitcher.click();
        WebElement themeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(themeSelector)));
        themeButton.click();
        pause();
    }

    private void assertThemeApplied(String expectedTheme)
    {
        String currentClass = driver.findElement(By.tagName("html")).getAttribute("class");
        Assertions.assertTrue(currentClass.contains(expectedTheme), expectedTheme + " theme was not applied successfully.");
    }

    @BeforeEach
    public void setUp()
    {
        driver = WebDriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://developer.mozilla.org/en-US/docs/Web/WebDriver");

        driver.manage().deleteAllCookies();
    }

    @AfterEach
    public void tearDown()
    {
        WebDriverManager.quitDriver();
        wait = null;
    }

    @Test
    public void testOsDefaultToDark()
    {
        switchToTheme(".icon-theme-os-default");
        switchToTheme(".icon-theme-dark");
        assertThemeApplied("dark");
    }

    @Test
    public void testOsDefaultToLight()
    {
        switchToTheme(".icon-theme-os-default");
        switchToTheme(".icon-theme-light");
        assertThemeApplied("light");
    }

    @Test
    public void testLightToDark()
    {
        switchToTheme(".icon-theme-light");
        switchToTheme(".icon-theme-dark");
        assertThemeApplied("dark");
    }

    @Test
    public void testDarkToLight()
    {
        switchToTheme(".icon-theme-dark");
        switchToTheme(".icon-theme-light");
        assertThemeApplied("light");
    }

    @Test
    public void testDarkToOsDefault()
    {
        switchToTheme(".icon-theme-dark");
        switchToTheme(".icon-theme-os-default");
        assertThemeApplied("os-default");
    }

    @Test
    public void testLightToOsDefault()
    {
        switchToTheme(".icon-theme-light");
        switchToTheme(".icon-theme-os-default");
        assertThemeApplied("os-default");
    }
}
