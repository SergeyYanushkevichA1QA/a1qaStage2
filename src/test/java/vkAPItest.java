import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.MainPage;
import by.a1qa.models.Comment;
import by.a1qa.models.Post;
import by.a1qa.service.StringGenerator;
import by.a1qa.service.VkApiUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class vkAPItest {
    private Browser browser = AqualityServices.getBrowser();
    public static MainPage mainPage;
    private static VkApiUtils vkApiUtils = new VkApiUtils();
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static File file = new File(environment.getValue("/testdata/pathForPic").toString());


    @BeforeClass
    public void setup() {
        browser.maximize();
        browser.goTo(environment.getValue("/testdata/url").toString());
        browser.waitForPageToLoad();
        mainPage = new MainPage(browser);
    }


    @Test
    public void APItest() throws IOException {
        Assert.assertEquals(mainPage.getURL(), environment.getValue("/testdata/url").toString());
        mainPage.fillLogin();
        mainPage.fillPassword();
        mainPage.clickLoginButton();
        mainPage.clickMyPageButton();
        AqualityServices.getLogger().info("Creating post with API and random message");
        Post.setId(vkApiUtils.wallPost(Post.setMessage(StringGenerator.getAlphaNumericString())));
        AqualityServices.getLogger().info("Checking post");
        Assert.assertTrue(mainPage.isPostCorrect());
        AqualityServices.getLogger().info("Editing post and attach image");
        vkApiUtils.wallEdit(Post.getId(), Post.setMessage(StringGenerator.getAlphaNumericString()), file);
        AqualityServices.getLogger().info("Checking post and image");
        Assert.assertTrue(mainPage.isPostWithPhotoCorrect());
        AqualityServices.getLogger().info("Creating reply");
        vkApiUtils.createReply(Post.getId(), Comment.setMessage(StringGenerator.getAlphaNumericString()));
        Assert.assertTrue(mainPage.isCommentCorrect());
        AqualityServices.getLogger().info("Push like button");
        mainPage.clickLikeButton();
        AqualityServices.getLogger().info("Checking your like");
        Assert.assertTrue(vkApiUtils.isLiked(Post.getId()));
        AqualityServices.getLogger().info("Deleting post");
        vkApiUtils.deletePost(Post.getId());
        AqualityServices.getLogger().info("Checking if post where deleted");
        Assert.assertTrue(mainPage.isPostDeleted(Post.getId()));
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }

}
