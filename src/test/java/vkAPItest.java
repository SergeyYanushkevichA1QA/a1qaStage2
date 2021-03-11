import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.MainPage;
import by.a1qa.entity.PostForm;
import by.a1qa.entity.WallForm;
import by.a1qa.models.Post;
import by.a1qa.service.ImageUtils;
import by.a1qa.service.StringGenerator;
import by.a1qa.service.VkApiUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class vkAPItest {
    private Browser browser = AqualityServices.getBrowser();
    public static MainPage mainPage;
    private static VkApiUtils vkApiUtils = new VkApiUtils();
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static ISettingsFile testdata = new JsonSettingsFile("testdata.json");
    private static File file = new File(environment.getValue("/path/pic").toString());
    private static String url = environment.getValue("/url").toString();


    @BeforeClass
    public void setup() {
        browser.maximize();
        browser.goTo(url);
        browser.waitForPageToLoad();
        mainPage = new MainPage();
    }


    @Test
    public void APItest() throws IOException {
        Assert.assertEquals(browser.getCurrentUrl(), url);
        mainPage.getLoginForm().login();
        mainPage.getNavBarForm().clickMyProfileButton();
        String message = StringGenerator.getAlphaNumericString();
        AqualityServices.getLogger().info("Creating post with API and random message");
        int postId = vkApiUtils.wallPost(message);
        WallForm wallForm = new WallForm();
        List<Post> posts = wallForm.getPosts();
        AqualityServices.getLogger().info("Checking post");
        Post post = posts.stream().filter(o -> o.getMessage().equals(message)).findFirst().orElse(null);
        AqualityServices.getLogger().info("Editing post and attach image");
        String newMessage = StringGenerator.getAlphaNumericString();
        vkApiUtils.wallEdit(postId, newMessage, file);
        AqualityServices.getLogger().info("Checking post and image");
        Post editedPost = wallForm.getPostWithPhoto(post.getId());
        BufferedImage fileImage = ImageUtils.getImage(file);
        BufferedImage photo = editedPost.getPhoto();
        Assert.assertEquals(editedPost.getMessage(), newMessage, "Wrong message");
        Assert.assertTrue(ImageUtils.isSimilarImages(fileImage, photo), "Wrong photo");
        String comment = StringGenerator.getAlphaNumericString();
        vkApiUtils.createReply(postId, comment);
        PostForm postForm = new PostForm(editedPost.getId());
        postForm.showReplies();
        List<Post> replies = postForm.getReplies();
        Post reply = replies.stream().filter(o -> o.getMessage().equals(comment)).findFirst().orElse(null);
        Assert.assertNotNull(reply, "No such post with message: " + comment);
        Assert.assertEquals(reply.getAuthorHrefPath(), testdata.getValue("/userId").toString(), "Wrong reply author");
        postForm.clickLikeButton();
        Assert.assertTrue(vkApiUtils.isLiked(postId), "Post isn't liked");
        vkApiUtils.deletePost(postId);
        PostForm.waitForDeletePost(editedPost.getId());
        Assert.assertFalse(wallForm.hasPost(editedPost.getId()), "Post didn't delete");

    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }

}
