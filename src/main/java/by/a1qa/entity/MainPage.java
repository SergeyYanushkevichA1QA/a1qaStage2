package by.a1qa.entity;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.ElementFactory;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import by.a1qa.models.Comment;
import by.a1qa.models.Post;
import by.a1qa.service.ImageUtils;
import by.a1qa.service.StringGenerator;
import by.a1qa.service.VkApiUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.awt.image.BufferedImage;
import java.io.File;

import static aquality.selenium.browser.AqualityServices.getElementFactory;


public class MainPage {
    public Browser browser;//wall_reply_text
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static File file = new File(environment.getValue("/testdata/pathForPic").toString());
    private static IElementFactory elementFactory = getElementFactory();
    private static IButton loginButton = elementFactory.getButton(By.xpath("//button[@id='index_login_button']"), "login button");
    private static ITextBox txbLogin = elementFactory.getTextBox(By.id("index_email"), "Login field");
    private static ITextBox txbPassword = elementFactory.getTextBox(By.id("index_pass"), "Password field");
    private static IButton myPageButton = elementFactory.getButton(By.xpath("//span[@class='left_label inl_bl']"), "My page button");
    private static ILink photo = getElementFactory().getLink(By.xpath("//*[@id='pv_photo']/img"), "Photo");
    private static ILink lnkPhoto = elementFactory.getLink(By.xpath("//a[contains(@class, 'page_post_thumb_wrap')]"), "Photo URL");
    private static IButton btnClosePhoto = elementFactory.getButton(By.className("pv_close_btn"), "Close");
    private static IButton btnShowReplies = elementFactory.getButton(By.xpath("//a[contains(@class, 'replies_next  replies_next_main')]"), "Show comments");
    private static ITextBox reply = elementFactory.getTextBox(By.id("index_email"), "reply field");


    public MainPage(Browser browser) {
        this.browser = browser;
    }

    public void fillLogin () {
        txbLogin.type(environment.getValue("/testdata/login").toString());
    }


    public void fillPassword () {
        txbPassword.type(environment.getValue("/testdata/password").toString());
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickMyPageButton () {
        myPageButton.click();
    }

    public boolean isPostCorrect() {
        boolean flag = false;
        ITextBox postWindow = elementFactory.getTextBox(By.xpath("//div[@id='post"
                + environment.getValue("/testdata/userId") + "_" + Post.getId() + "']"), "Post");
        flag = postWindow.getElement().isDisplayed();
        if(postWindow.getElement().getText().contains(Post.getMessage())) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public boolean isPostWithPhotoCorrect() {
        boolean flag = false;
        ITextBox postWindow = elementFactory.getTextBox(By.xpath("//div[@id='post"
                + environment.getValue("/testdata/userId") + "_" + Post.getId() + "']"), "Post");
        flag = postWindow.getElement().isDisplayed();
        if(postWindow.getElement().getText().contains(Post.getMessage())) {
            BufferedImage fileImage = ImageUtils.getImage(file);
            BufferedImage image = ImageUtils.getImage(getImageURL());
            Post.setPhoto(image);
            flag = ImageUtils.isSimilarImages(fileImage, image);
        } else {
            flag = false;
        }
        return flag;
    }

    public boolean isCommentCorrect() {
        boolean flag = false;
        System.out.println(Comment.getId());
        ITextBox reply = elementFactory.getTextBox(By.xpath("//div[@class='wall_reply_text']"), "reply field");
        showReplies();
        flag = reply.getElement().isDisplayed();
        System.out.println(Comment.getMessage());
        System.out.println(reply.getElement().getText());
        if(reply.getElement().getText().contains(Comment.getMessage())) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    private static String getPhotoURL() {
        return photo.getAttribute("src");
    }

    private static String getImageURL() {
        String photoURL = "";
        if (lnkPhoto.state().isExist()) {
            lnkPhoto.click();
            photoURL = getPhotoURL();
            btnClosePhoto.click();
        }
        return photoURL;
    }

    public static void showReplies() {
        if (btnShowReplies.state().isExist()) {
            btnShowReplies.click();
        }
    }

   public void clickLikeButton() {
        IButton likeButton = elementFactory.getButton(By.xpath("//div[@id='post"
                + environment.getValue("/testdata/userId") + "_" + Post.getId() + "']//div[@class='like_button_icon']"), "Like button");
        likeButton.click();
    }


    public static void waitForDeletePost(String id) {
        try {
            AqualityServices.getConditionalWait().waitForTrue(() -> AqualityServices.getBrowser().getDriver()
                    .findElementsById(id).isEmpty());
        } catch (TimeoutException | java.util.concurrent.TimeoutException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
    }

    public boolean isPostDeleted(String id) {
        boolean flag = false;
        waitForDeletePost(id);
        if(elementFactory.findElements(By.id(id), ElementType.LABEL).isEmpty()) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public String getURL() {
       return browser.getCurrentUrl();
    }



}
