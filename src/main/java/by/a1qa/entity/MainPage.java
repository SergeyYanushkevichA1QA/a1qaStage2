package by.a1qa.entity;

import by.a1qa.service.StringGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public WebDriver driver;
    private static String randomString = StringGenerator.getAlphaNumericString();

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "#tox-edit-area>iframe")
    private WebElement iframe;

    @FindBy(xpath = "//body[@id='tinymce']/p")
    private WebElement inputField;

    @FindBy(xpath = "//button[@title='Bold']")
    private WebElement boldButton;


    @FindBy(xpath = "//div[contains(@role, 'button') and contains(text(), 'p')]")
    private WebElement highlightTxtButton;

    @FindBy(xpath = "//div[contains(@role, 'button') and contains(text(), 'strong')]")
    private WebElement boldButtonTxt;

    private void switchToFrame() {
        driver.switchTo().frame("mce_0_ifr");
    }

    private void leaveFrame() {
        driver.switchTo().defaultContent();
    }
    public void clearInput() {
        switchToFrame();
        inputField.clear();
    }

    public void clickBoldButton() {
        boldButton.click();
    }
    public void highlightText() {
        leaveFrame();
        highlightTxtButton.click();
    }
    public void inputRandomText() {
        inputField.sendKeys(randomString);
    }

    public String getInputTxt() {
        return inputField.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static String getRandomString() {
        return randomString;
    }

    public boolean isBoldTxt() {
        boolean flag = false;
        if(boldButtonTxt.isDisplayed())
        {
            flag = true;
        }
        return flag;
    }

    public void driverDown() {
        driver.quit();
    }
}
