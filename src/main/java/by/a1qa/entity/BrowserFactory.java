package by.a1qa.entity;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    public static final WebDriver driver = null;

    public static WebDriver getDriver(String name) {
        if ("FIREFOX".equalsIgnoreCase(name)) {
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.download.dir", "C:\\");
            options.addPreference("browser.download.useDownloadDir", true);
            options.addPreference("browser.download.viewableInternally.enabledTypes", "");
            options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
            options.addPreference("pdfjs.disabled", true);
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(options);
        } else if ("CHROME".equalsIgnoreCase(name)) {
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.prompt_for_download", false);
            prefs.put("download.directory_upgrade", true);
            prefs.put("download.extensions_to_open", "application/octet-stream");
            prefs.put("safebrowsing.enabled", true);
            prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
            prefs.put("profile.default_content_settings.popups", 0);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("prefs", prefs);
            chromeOptions.addArguments("--safebrowsing-disable-download-protection");
            chromeOptions.addArguments("--safebrowsing-disable-extension-blacklist");
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(chromeOptions);
        } else {
            return driver;
        }
    }
}