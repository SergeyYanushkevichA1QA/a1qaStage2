import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.*;
import by.a1qa.models.Post;
import by.a1qa.models.User;
import by.a1qa.service.APIUtils;
import by.a1qa.service.Utils;
import org.apache.commons.httpclient.HttpStatus;
import org.testng.Assert;

import org.testng.annotations.Test;

import java.util.List;


public class APITest {
    private static ISettingsFile testdata = new JsonSettingsFile("testdata.json");

    @Test
    public void apiTest(){
        PostsResponse postsResponse = APIUtils.getPosts();
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postsResponse.getStatusCode(), HttpStatus.SC_OK);
        AqualityServices.getLogger().info("Status code is " + postsResponse.getStatusCode());
        AqualityServices.getLogger().info("Checking json");
        Assert.assertTrue(postsResponse.isJSON());
        AqualityServices.getLogger().info("Checking is sorted by id");
        Assert.assertTrue(Utils.isSortedById(postsResponse.getObject()));
        int userId = 10;
        int id = 99;
        PostResponse postResponse = APIUtils.getPost(id);
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.SC_OK);
        AqualityServices.getLogger().info("Status code is " + postResponse.getStatusCode());
        Post post = postResponse.getObject();
        AqualityServices.getLogger().info("Checking userId");
        Assert.assertEquals(post.getUserId(), userId, "Wrong userId");
        AqualityServices.getLogger().info("Checking id");
        Assert.assertEquals(post.getId(), id, "Wrong id");
        AqualityServices.getLogger().info("Checking if is title and body empty");
        Assert.assertFalse(post.getTitle().isEmpty(), "Title is empty");
        Assert.assertFalse(post.getBody().isEmpty(), "Body is empty");
        id = 150;
        postResponse = APIUtils.getPost(id);
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND);
        AqualityServices.getLogger().info("Status code is " + postResponse.getStatusCode());
        AqualityServices.getLogger().info("Checking body of response");
        Assert.assertEquals(postResponse.getBody(), "{}");
        Post newPost = new Post();
        newPost.setUserId((Integer) testdata.getValue("/testdata/testPost/userId"));
        newPost.setTitle(testdata.getValue("/testdata/testPost/title").toString());
        newPost.setBody(testdata.getValue("/testdata/testPost/body").toString());
        postResponse = APIUtils.setPost(newPost);
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.SC_CREATED);
        AqualityServices.getLogger().info("Status code is " + postResponse.getStatusCode());
        post = postResponse.getObject();
        AqualityServices.getLogger().info("Check userId, title, body of post");
        Assert.assertEquals(post.getUserId(), testdata.getValue("/testdata/testPost/userId"));
        Assert.assertEquals(post.getTitle(), testdata.getValue("/testdata/testPost/title").toString());
        Assert.assertEquals(post.getBody(), testdata.getValue("/testdata/testPost/body").toString());
        int userN5forArr = 4;
        UsersResponse usersResponse = APIUtils.getUsers();
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(usersResponse.getStatusCode(), HttpStatus.SC_OK);
        AqualityServices.getLogger().info("Status code is " + usersResponse.getStatusCode());
        AqualityServices.getLogger().info("Checking json");
        Assert.assertTrue(usersResponse.isJSON());
        List<User> users = usersResponse.getObject();
        User user = users.get(userN5forArr);
        AqualityServices.getLogger().info("Checking user id");
        Assert.assertEquals(user.getId(), 5);
        AqualityServices.getLogger().info("User id is " + user.getId());
        User testUser = Utils.getTestUser();
        AqualityServices.getLogger().info("Checking user data with test data");
        Assert.assertEquals(user, testUser);
        UserResponse userResponse = APIUtils.getUser(user.getId());
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(usersResponse.getStatusCode(), HttpStatus.SC_OK);
        AqualityServices.getLogger().info("Status code is " + usersResponse.getStatusCode());
        User newUser = userResponse.getObject();
        AqualityServices.getLogger().info("Checking user data with user in response");
        Assert.assertEquals(user, newUser, "Wrong user");
    }





}