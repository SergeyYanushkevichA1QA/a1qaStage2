package by.a1qa.entity;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class StartPage extends Form {

    private IElementFactory elementFactory = AqualityServices.getElementFactory();
    private IButton startButton = elementFactory.getButton(By.xpath("//a[@class='start__link']"), "start button");

    public StartPage() {
        super(By.xpath("/html"), "Start page");
    }

    public void clickStartButton() {
        startButton.click();
    }

    public boolean isPage() {
        return startButton.state().isExist();
    }

}
