package by.a1qa.entity;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import by.a1qa.forms.CookiesForm;
import by.a1qa.forms.HelpForm;
import by.a1qa.utils.Utils;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;

public class GamePage {
    private final CookiesForm cookiesForm = new CookiesForm();
    private final HelpForm helpForm = new HelpForm();

    private static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ITextBox passwordTxtBox = elementFactory.getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "password");
    private final ITextBox emailTxtBox = elementFactory.getTextBox(By.cssSelector("input[placeholder='Your email']"), "email");
    private final ITextBox domainTxtBox = elementFactory.getTextBox(By.cssSelector("input[placeholder='Domain']"), "domain");
    private final IComboBox dropDownDomain = elementFactory.getComboBox(By.xpath("//div[contains(@class,'dropdown--')]"), "dropDownDomain");
    private final ICheckBox notAcceptCheckBox = elementFactory.getCheckBox(By.className("checkbox"), "Not accept");
    private final IButton nextButton = elementFactory.getButton(By.xpath("//a[.='Next']"), "Next Button");
    private final ILabel timer = elementFactory.getLabel(By.xpath("//div[contains(@class, 'timer--center')]"), "Timer");
    private final ILabel pageNumber = elementFactory.getLabel(By.xpath("//div[@class='page-indicator']"), "Page indicator");
    private final IButton nextButtonAtThirdCard = elementFactory.getButton(By.xpath("//a[.='Next']"), "Next");
    private final By dropDownDomainList = By.xpath("//div[@class='dropdown__list']//div[contains(text(), '.')]");
    private final ICheckBox unselectAllCheckBox = elementFactory.getCheckBox(By.xpath("//label[@for='interest_unselectall']"), "unselect all checkbox");
    private final ILink uploadImageLink = elementFactory.getLink(By.xpath("//a[@class='avatar-and-interests__upload-button']"), "Upload image");;
    private static By interestsLocator = By.xpath("//div[@class='avatar-and-interests__interests-list__item']//label");


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

    public void clickNextButton() {
        nextButton.click();
    }

    private List<IButton> getDropDownList() {
        return elementFactory.findElements(dropDownDomainList, ElementType.BUTTON);
    }

    public void selectRandomDropDownDomain() {
        dropDownDomain.click();
        List<IButton> DropDownList = getDropDownList();
        DropDownList.get(new Random().nextInt(DropDownList.size())).click();
    }

    private static List<ICheckBox> getInterests() {
        List<ICheckBox> interests = elementFactory.findElements(interestsLocator, ElementType.CHECKBOX);
        return interests;
    }

    public void checkThreeInterests() {
        unselectAllCheckBox.click();
        List<ICheckBox> interests = getInterests();
        for (int i = 0; i < 3; i++) {
            int rand = new Random().nextInt(interests.size());
            interests.get(rand).click();
        }
    }

    public String getURL() {
        return AqualityServices.getBrowser().getCurrentUrl();
    }

    public void uploadImage() {
       uploadImageLink.click();
       Utils.autoUploadImage();
   }

    public CookiesForm getCookiesForm() {
        return cookiesForm;
    }

    public HelpForm getHelpForm() {
        return helpForm;
    }

    public String getTimerValue() {
        return timer.getText();
    }

    public boolean checkFirstCard() {
        return pageNumber.getText().charAt(0) == '1';
    }

    public boolean checkSecondCard() {
        return pageNumber.getText().charAt(0) == '2';
    }

    public boolean checkThirdCard() {
        return pageNumber.getText().charAt(0) == '3';
    }

    public void clickNextButtonAt3rd() {
        nextButtonAtThirdCard.click();
    }

}