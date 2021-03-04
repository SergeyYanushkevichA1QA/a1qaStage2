package by.a1qa.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;

public class LoginForm extends Form {

    private static IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final ITextBox passwordTxtBox = elementFactory.getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "password");
    private final ITextBox emailTxtBox = elementFactory.getTextBox(By.cssSelector("input[placeholder='Your email']"), "email");
    private final ITextBox domainTxtBox = elementFactory.getTextBox(By.cssSelector("input[placeholder='Domain']"), "domain");
    private final IComboBox dropDownDomain = elementFactory.getComboBox(By.xpath("//div[contains(@class,'dropdown--')]"), "dropDownDomain");
    private final ICheckBox notAcceptCheckBox = elementFactory.getCheckBox(By.className("checkbox"), "Not accept");
    private final By dropDownDomainList = By.xpath("//div[@class='dropdown__list']//div[contains(text(), '.')]");
    private final IButton nextButton = elementFactory.getButton(By.xpath("//a[.='Next']"), "Next Button");

    public LoginForm() {
        super(By.className("login-form__container"), "login");
    }

    public void setPassword(String text) {
        passwordTxtBox.clearAndType(text);
    }

    public String getPassword() {
        return passwordTxtBox.getValue();
    }

    public void setEmail(String text) {
        emailTxtBox.clearAndType(text);
    }

    public void setDomain(String text) {
        domainTxtBox.clearAndType(text);
    }

    public void acceptTermsAndConditions() {
        notAcceptCheckBox.click();
    }

    public void selectRandomDropDownDomain() {
        dropDownDomain.click();
        List<IButton> DropDownList = getDropDownList();
        DropDownList.get(new Random().nextInt(DropDownList.size())).click();
    }

    private List<IButton> getDropDownList() {
        return elementFactory.findElements(dropDownDomainList, ElementType.BUTTON);
    }

    public void clickNextButton() {
        nextButton.click();
    }
}
