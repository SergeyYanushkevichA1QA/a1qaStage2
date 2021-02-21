import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.*;
import by.a1qa.models.Post;
import by.a1qa.models.User;
import by.a1qa.service.APIUtils;
import by.a1qa.service.Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class PostsTest {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    @Test
    public void getAllPosts(){
        PostsResponse postsResponse = APIUtils.getPosts(environment.getValue("/testdata/methods/get/posts").toString());
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postsResponse.getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + postsResponse.getStatusCode());
        AqualityServices.getLogger().info("Checking json");
        Assert.assertTrue(postsResponse.isJSON());
    }



    @Test
    public void getPostN99() {
        PostResponse postResponse = APIUtils.getPost(environment.getValue("/testdata/methods/get/postN99").toString());
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postResponse.getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + postResponse.getStatusCode());
        Post post = postResponse.getObject();
        AqualityServices.getLogger().info("Checking userId");
        Assert.assertEquals(post.getUserId(), environment.getValue("/testdata/postN99data/userId").toString());
        AqualityServices.getLogger().info("Checking id");
        Assert.assertEquals(post.getId(), environment.getValue("/testdata/postN99data/id").toString());
        AqualityServices.getLogger().info("Checking checking title and body");
        Assert.assertFalse(post.getTitle().isEmpty(), "Title is empty");
        Assert.assertFalse(post.getBody().isEmpty(), "Body is empty");
    }

    @Test
    public void getPostN150() {
        PostResponse postResponse = APIUtils.getPost(environment.getValue("/testdata/methods/get/postN150").toString());
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(postResponse.getStatusCode(), 404);
        AqualityServices.getLogger().info("Status code is " + postResponse.getStatusCode());
        AqualityServices.getLogger().info("Checking body of response");
        Assert.assertEquals(postResponse.getBody(), "{}");
    }

    @Test
    public void postUser() {
        Post newPost = new Post();
        newPost.setUserId(environment.getValue("/testdata/testPost/userId").toString());
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
        UsersResponse usersResponse = APIUtils.getUsers(environment.getValue("/testdata/methods/get/users").toString());
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(usersResponse.getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + usersResponse.getStatusCode());
        AqualityServices.getLogger().info("Checking json");
        Assert.assertTrue(usersResponse.isJSON());
        List<User> users = usersResponse.getObject();
        User user = users.get(4);
        AqualityServices.getLogger().info("Checking user id");
        Assert.assertEquals(user.getId(), "5");
        AqualityServices.getLogger().info("User id is "+user.getId());
        AqualityServices.getLogger().info("Checking name");
        Assert.assertEquals(user.getName(), environment.getValue("/testdata/testUser/name").toString());
        AqualityServices.getLogger().info("Checking username");
        Assert.assertEquals(user.getUsername(), environment.getValue("/testdata/testUser/username").toString());
        AqualityServices.getLogger().info("Checking email");
        Assert.assertEquals(user.getEmail(), environment.getValue("/testdata/testUser/email").toString());
        AqualityServices.getLogger().info("Checking street");
        Assert.assertEquals(user.getAddress().getStreet(), environment.getValue("/testdata/testUser/street").toString());
        AqualityServices.getLogger().info("Checking suite");
        Assert.assertEquals(user.getAddress().getSuite(), environment.getValue("/testdata/testUser/suite").toString());
        AqualityServices.getLogger().info("Checking city");
        Assert.assertEquals(user.getAddress().getCity(), environment.getValue("/testdata/testUser/city").toString());
        AqualityServices.getLogger().info("Checking zipcode");
        Assert.assertEquals(user.getAddress().getZipcode(), environment.getValue("/testdata/testUser/zipcode").toString());
        AqualityServices.getLogger().info("Checking lat");
        Assert.assertEquals(user.getAddress().getGeo().getLat(), environment.getValue("/testdata/testUser/lat").toString());
        AqualityServices.getLogger().info("Checking lng");
        Assert.assertEquals(user.getAddress().getGeo().getLng(), environment.getValue("/testdata/testUser/lng").toString());
        AqualityServices.getLogger().info("Checking phone");
        Assert.assertEquals(user.getPhone(), environment.getValue("/testdata/testUser/phone").toString());
        AqualityServices.getLogger().info("Checking website");
        Assert.assertEquals(user.getWebsite(), environment.getValue("/testdata/testUser/website").toString());
        AqualityServices.getLogger().info("Checking company name");
        Assert.assertEquals(user.getCompany().getName(), environment.getValue("/testdata/testUser/companyname").toString());
        AqualityServices.getLogger().info("Checking catchPhrase");
        Assert.assertEquals(user.getCompany().getCatchPhrase(), environment.getValue("/testdata/testUser/catchPhrase").toString());
        AqualityServices.getLogger().info("Checking bs");
        Assert.assertEquals(user.getCompany().getBs(), environment.getValue("/testdata/testUser/bs").toString());
        UserResponse userResponse = APIUtils.getUser(environment.getValue("/testdata/methods/get/userN5").toString());
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(usersResponse.getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + usersResponse.getStatusCode());
        User newUser = userResponse.getObject();
        Assert.assertEquals(newUser, user);
    }

}
