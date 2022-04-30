package com.gem.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private Driver(){}

    private static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver == null){
            String browser = ConfigFileReader.getProperty("browser");

            switch (browser){
                case "chrome":
                    // Using bonigarcia dependency to set up web driver for Chrome browser.
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    // Using bonigarcia dependency to set up web driver for Firefox browser.
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Not supported or incorrect browser name");
            }
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.manage().window().maximize();
        }
        return driver;

    }

    public static void quitDriver(){
        if (driver !=null){
            driver.quit();
            driver = null;
        }
    }
}
