package by.a1qa.entity;

import by.a1qa.service.ConfProperties;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static Driver instance = null;
    private static WebDriver driver;


    private Driver(){

    }

    public WebDriver openBrowser(String browserName){
        driver = BrowserFactory.getDriver(ConfProperties.getProperty(browserName));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));
        return driver;
    }

    public static Driver getInstance(){
        if(instance == null){
            instance = new Driver();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

}