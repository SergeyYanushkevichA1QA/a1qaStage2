package by.a1qa.entity;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NavBarForm extends Form {

    private final IButton myPageButton = getElementFactory().getButton(By.xpath("//span[@class='left_label inl_bl']"),
            "My page button");

    public NavBarForm() {
        super(By.xpath("//div[@id='side_bar_inner']"), "Navigate bar");
    }

    public void clickMyProfileButton() {
        myPageButton.click();
    }
}
