package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverManager
{
    static WebDriver driver;

    public static WebDriver getDriver()
    {
        if (driver == null)
        {
            System.setProperty("webdriver.chrome.driver",
                    "/Users/Labib/Downloads/webdriver/chromedriver-mac-arm64/chromedriver");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void quitDriver()
    {
         if(driver!=null)
         {
             driver.quit();
             driver=null;
         }
    }
}