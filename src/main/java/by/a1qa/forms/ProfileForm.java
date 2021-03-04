package by.a1qa.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import by.a1qa.utils.Utils;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;

public class ProfileForm extends Form {

    private final IElementFactory elementFactory = AqualityServices.getElementFactory();

    private final IButton nextButton = elementFactory.getButton(By.xpath("//button[.='Next']"), "Next");
    private final ICheckBox unselectAllCheckBox = elementFactory.getCheckBox(By.xpath("//label[@for='interest_unselectall']"), "unselect all checkbox");
    private final ILink uploadImageLink = elementFactory.getLink(By.xpath("//a[@class='avatar-and-interests__upload-button']"), "Upload image");;
    private static By interestsLocator = By.xpath("//div[@class='avatar-and-interests__interests-list__item']//label");

    public ProfileForm() {
        super(By.className("avatar-and-interests__form"), "Profile");
    }

    public List<ICheckBox> getInterests() {
        List<ICheckBox> interests = elementFactory.findElements(interestsLocator, ElementType.CHECKBOX);
        return interests;
    }


    public void checkInterests(int number) {
        unselectAllCheckBox.click();
        List<ICheckBox> interests = getInterests();
        for (int i = 0; i < number; i++) {
            int rand = new Random().nextInt(interests.size());
            interests.get(rand).click();
            interests.remove(rand);
        }
    }


    public void uploadImage() {
        uploadImageLink.click();
        Utils.autoUploadImage();
    }

    public void clickNextButton() {
            nextButton.click();
    }
}
