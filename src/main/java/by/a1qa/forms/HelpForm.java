package by.a1qa.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final IButton btnSendToBottom = elementFactory.getButton(By.xpath("//button[contains(@class,'help-form__send-to-bottom-button')]"), "Send to bottom");
    private final ILabel lblTitle = elementFactory.getLabel(By.xpath("//*[@class='help-form__title']"), "Title");

    public HelpForm() {
        super(By.className("help-form__container"), "Help");
    }

    public void hide() {
        btnSendToBottom.click();
    }

    public boolean isHidden() {
        return lblTitle.getElement().isDisplayed();
    }
}