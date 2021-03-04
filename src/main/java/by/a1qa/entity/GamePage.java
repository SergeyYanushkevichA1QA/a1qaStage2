package by.a1qa.entity;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import by.a1qa.forms.CookiesForm;
import by.a1qa.forms.HelpForm;
import by.a1qa.forms.LoginForm;
import by.a1qa.forms.ProfileForm;
import org.openqa.selenium.By;


public class GamePage {
    private final CookiesForm cookiesForm = new CookiesForm();
    private final HelpForm helpForm = new HelpForm();
    private final LoginForm loginForm = new LoginForm();
    private final ProfileForm profileForm = new ProfileForm();


    private static IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ILabel timer = elementFactory.getLabel(By.xpath("//div[contains(@class, 'timer--center')]"), "Timer");
    private final ILabel pageNumber = elementFactory.getLabel(By.xpath("//div[@class='page-indicator']"), "Page indicator");


    public boolean isPage() {
        return timer.state().isExist();
    }

    public CookiesForm getCookiesForm() {
        return cookiesForm;
    }

    public HelpForm getHelpForm() {
        return helpForm;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public ProfileForm getProfileForm() {
        return profileForm;
    }

    public String getTimerValue() {
        return timer.getText();
    }


    public int getCurrentPageNumber() {
        String number = String.valueOf(pageNumber.getText().charAt(0));

        return Integer.parseInt(number);
    }


}