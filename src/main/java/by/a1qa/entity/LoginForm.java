package by.a1qa.entity;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginForm extends Form {
    private static ISettingsFile testdata = new JsonSettingsFile("testdata.json");

    private final IButton loginButton = getElementFactory().getButton(By.xpath("//button[@id='index_login_button']"), "login button");
    private final ITextBox txbLogin = getElementFactory().getTextBox(By.id("index_email"), "Login field");
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.id("index_pass"), "Password field");


    public LoginForm() {
        super(By.id("index_login"), "Welcome page");
    }

    public void login() {
        fillLogin();
        fillPassword();
        loginButton.click();
    }

    private void fillLogin () {
        txbLogin.type(testdata.getValue("/login").toString());
    }


    private void fillPassword () {
        txbPassword.type(testdata.getValue("/password").toString());
    }
}
