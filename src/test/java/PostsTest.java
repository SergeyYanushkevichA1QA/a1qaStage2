import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.*;
import by.a1qa.models.Post;
import by.a1qa.models.User;
import by.a1qa.service.APIUtils;
import by.a1qa.service.Utils;
import org.testng.Assert;

import org.testng.annotations.Test;

import java.util.List;


public class PostsTest {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    @Test
    public void getAllPosts(){
        PostsResponse postsResponse = APIUtils.getPosts();
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postsResponse.getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + postsResponse.getStatusCode());
        AqualityServices.getLogger().info("Checking json");
        Assert.assertTrue(postsResponse.isJSON());
        AqualityServices.getLogger().info("Checking is sorted by id");
        Assert.assertTrue(Utils.isSortedById(postsResponse.getObject()));
    }

    @Test
    public void getPostN99() {
        int userId = 10;
        int id = 99;
        PostResponse postResponse = APIUtils.getPost(id);
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postResponse.getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + postResponse.getStatusCode());
        Post post = postResponse.getObject();
        AqualityServices.getLogger().info("Checking userId");
        Assert.assertEquals(post.getUserId(), userId);
        AqualityServices.getLogger().info("Checking id");
        Assert.assertEquals(post.getId(), id);
        AqualityServices.getLogger().info("Checking if is title and body empty");
        Assert.assertFalse(post.getTitle().isEmpty(), "Title is empty");
        Assert.assertFalse(post.getBody().isEmpty(), "Body is empty");
    }

    @Test
    public void getPostN150() {
        int id = 150;
        PostResponse postResponse = APIUtils.getPost(id);
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postResponse.getStatusCode(), 404);
        AqualityServices.getLogger().info("Status code is " + postResponse.getStatusCode());
        AqualityServices.getLogger().info("Checking body of response");
        Assert.assertEquals(postResponse.getBody(), "{}");
    }

    @Test
    public void postUser() {
        Post newPost = new Post();
        newPost.setUserId((Integer) environment.getValue("/testdata/testPost/userId"));
        newPost.setTitle(environment.getValue("/testdata/testPost/title").toString());
        newPost.setBody(environment.getValue("/testdata/testPost/body").toString());
        PostResponse postResponse = APIUtils.setPost(newPost);
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postResponse.getStatusCode(), 201);
        AqualityServices.getLogger().info("Status code is " + postResponse.getStatusCode());
        Post post = postResponse.getObject();
        AqualityServices.getLogger().info("Check userId, title, body of post");
        Assert.assertEquals(post.getUserId(), environment.getValue("/testdata/testPost/userId").toString());
        Assert.assertEquals(post.getTitle(), environment.getValue("/testdata/testPost/title").toString());
        Assert.assertEquals(post.getBody(), environment.getValue("/testdata/testPost/body").toString());
    }

    @Test
    public void getAllUsersAndUserN5() {
        int userN5forArr = 4;
        UsersResponse usersResponse = APIUtils.getUsers();
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(usersResponse.getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + usersResponse.getStatusCode());
        AqualityServices.getLogger().info("Checking json");
        Assert.assertTrue(usersResponse.isJSON());
        List<User> users = usersResponse.getObject();
        User user = users.get(userN5forArr);
        AqualityServices.getLogger().info("Checking user id");
        Assert.assertEquals(user.getId(), 5);
        AqualityServices.getLogger().info("User id is "+user.getId());
        User testUser = Utils.getUserN5();
        AqualityServices.getLogger().info("Checking user data with test data");
        Assert.assertEquals(user, testUser);
        UserResponse userResponse = APIUtils.getUser(user.getId());
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(usersResponse.getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + usersResponse.getStatusCode());
        User newUser = userResponse.getObject();
        AqualityServices.getLogger().info("Checking user data with user in response");
        Assert.assertEquals(user, newUser);
    }

}