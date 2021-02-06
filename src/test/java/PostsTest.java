import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.service.ConfProperties;
import by.a1qa.service.JSONReader;
import org.apache.hc.core5.http.ContentType;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import org.testng.annotations.Test;
import aquality.selenium.browser.AqualityServices;

import org.apache.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class PostsTest {
    private final CloseableHttpClient httpclient = HttpClients.createDefault();

    @Test
    public void getAllPosts() throws IOException {
        HttpUriRequest httpGetPosts = new HttpGet("http://jsonplaceholder.typicode.com/posts");
        CloseableHttpResponse response1 = httpclient.execute(httpGetPosts);
        HttpEntity posts = response1.getEntity();
        AqualityServices.getLogger().info(EntityUtils.toString(posts));
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(response1.getStatusLine().getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + response1.getStatusLine().getStatusCode());
    }

    @Test
    public void getPostN99() throws IOException {
        HttpUriRequest httpGetPostN99 = new HttpGet("http://jsonplaceholder.typicode.com/posts/99");
        HttpResponse response2 = httpclient.execute(httpGetPostN99);
        HttpEntity postN99 = response2.getEntity();
        JSONObject result = new JSONObject(EntityUtils.toString(postN99));
        AqualityServices.getLogger().info(String.valueOf(result));
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(response2.getStatusLine().getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + response2.getStatusLine().getStatusCode());
        AqualityServices.getLogger().info("Checking userId");
        Assert.assertEquals(String.valueOf(result.get("userId")), JSONReader.readByKey("userId"));
        AqualityServices.getLogger().info("Checking id");
        Assert.assertEquals(String.valueOf(result.get("id")), JSONReader.readByKey("id"));
    }

    @Test
    public void getPostN150() throws IOException {
        HttpUriRequest httpGetPostN150 = new HttpGet("http://jsonplaceholder.typicode.com/posts/150");
        HttpResponse response3 = httpclient.execute(httpGetPostN150);
        HttpEntity postN150 = response3.getEntity();
        JSONObject result = new JSONObject(EntityUtils.toString(postN150));
        AqualityServices.getLogger().info(String.valueOf(result));
        Assert.assertEquals(response3.getStatusLine().getStatusCode(), 404);
        AqualityServices.getLogger().info("Status code is " + response3.getStatusLine().getStatusCode());
        AqualityServices.getLogger().info("Checking response for null");
        Assert.assertEquals(String.valueOf(result), "{}");
    }

    @Test
    public void postUser() throws IOException {
        HttpPost httpPost = new HttpPost("http://jsonplaceholder.typicode.com/posts");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("title", ConfProperties.getProperty("title")));
        params.add(new BasicNameValuePair("body", ConfProperties.getProperty("body")));
        params.add(new BasicNameValuePair("userId", ConfProperties.getProperty("userId")));
        params.add(new BasicNameValuePair("id", ConfProperties.getProperty("id")));
        AqualityServices.getLogger().info("POST with title,body,useId, id");
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse post1 = httpclient.execute(httpPost);
        Assert.assertEquals(post1.getStatusLine().getStatusCode(), 201);
        AqualityServices.getLogger().info("Status code is " + post1.getStatusLine().getStatusCode());
        HttpEntity postUser = post1.getEntity();
        JSONObject result = new JSONObject(EntityUtils.toString(postUser));
        AqualityServices.getLogger().info("Checking title");
        Assert.assertEquals(String.valueOf(result.get("title")), ConfProperties.getProperty("title"));
        AqualityServices.getLogger().info("Checking body");
        Assert.assertEquals(String.valueOf(result.get("body")), ConfProperties.getProperty("body"));
        AqualityServices.getLogger().info("Checking useId");
        Assert.assertEquals(String.valueOf(result.get("userId")), ConfProperties.getProperty("userId"));
        AqualityServices.getLogger().info("Checking id");
        Assert.assertEquals(String.valueOf(result.get("id")), ConfProperties.getProperty("id"));
    }

    @Test
    public void getAllUsers() throws IOException {
        HttpUriRequest httpGetUsers = new HttpGet("http://jsonplaceholder.typicode.com/users");
        HttpResponse response4 = httpclient.execute(httpGetUsers);
        HttpEntity users = response4.getEntity();
        JSONArray result = new JSONArray(EntityUtils.toString(users));
        AqualityServices.getLogger().info(String.valueOf(result));
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(response4.getStatusLine().getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + response4.getStatusLine().getStatusCode());
        System.out.print(result.getJSONObject(4).get("name"));
        AqualityServices.getLogger().info("Status code is " + response4.getStatusLine().getStatusCode());
        AqualityServices.getLogger().info("Checking name");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).get("name")), JSONReader.readByKey("name"));
        AqualityServices.getLogger().info("Checking username");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).get("username")), JSONReader.readByKey("username"));
        AqualityServices.getLogger().info("Checking email");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).get("email")), JSONReader.readByKey("email"));
        AqualityServices.getLogger().info("Checking street");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).getJSONObject("address").get("street")), JSONReader.readByKey("street"));
        AqualityServices.getLogger().info("Checking suite");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).getJSONObject("address").get("suite")), JSONReader.readByKey("suite"));
        AqualityServices.getLogger().info("Checking city");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).getJSONObject("address").get("city")), JSONReader.readByKey("city"));
        AqualityServices.getLogger().info("Checking lat");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).getJSONObject("address").getJSONObject("geo").get("lat")), JSONReader.readByKey("lat"));
        AqualityServices.getLogger().info("Checking lng");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).getJSONObject("address").getJSONObject("geo").get("lng")), JSONReader.readByKey("lng"));
        AqualityServices.getLogger().info("Checking phone");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).get("phone")), JSONReader.readByKey("phone"));
        AqualityServices.getLogger().info("Checking website");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).get("website")), JSONReader.readByKey("website"));
        AqualityServices.getLogger().info("Checking company name");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).getJSONObject("company").get("name")), JSONReader.readByKey("companyname"));
        AqualityServices.getLogger().info("Checking catchPhrase");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).getJSONObject("company").get("catchPhrase")), JSONReader.readByKey("catchPhrase"));
        AqualityServices.getLogger().info("Checking bs");
        Assert.assertEquals(String.valueOf(result.getJSONObject(4).getJSONObject("company").get("bs")), JSONReader.readByKey("bs"));
    }

    @Test
    public void getUserN5() throws IOException {
        HttpUriRequest httpgetUserN5 = new HttpGet("http://jsonplaceholder.typicode.com/users/5");
        HttpResponse response5 = httpclient.execute(httpgetUserN5);
        HttpEntity userN5 = response5.getEntity();
        JSONObject result = new JSONObject(EntityUtils.toString(userN5));
        AqualityServices.getLogger().info(String.valueOf(result));
        AqualityServices.getLogger().info("Checking status code");
        Assert.assertEquals(response5.getStatusLine().getStatusCode(), 200);
        AqualityServices.getLogger().info("Status code is " + response5.getStatusLine().getStatusCode());
        AqualityServices.getLogger().info("Checking name");
        Assert.assertEquals(String.valueOf(result.get("name")), JSONReader.readByKey("name"));
        AqualityServices.getLogger().info("Checking username");
        Assert.assertEquals(String.valueOf(result.get("username")), JSONReader.readByKey("username"));
        AqualityServices.getLogger().info("Checking email");
        Assert.assertEquals(String.valueOf(result.get("email")), JSONReader.readByKey("email"));
        AqualityServices.getLogger().info("Checking street");
        Assert.assertEquals(String.valueOf(result.getJSONObject("address").get("street")), JSONReader.readByKey("street"));
        AqualityServices.getLogger().info("Checking suite");
        Assert.assertEquals(String.valueOf(result.getJSONObject("address").get("suite")), JSONReader.readByKey("suite"));
        AqualityServices.getLogger().info("Checking city");
        Assert.assertEquals(String.valueOf(result.getJSONObject("address").get("city")), JSONReader.readByKey("city"));
        AqualityServices.getLogger().info("Checking lat");
        Assert.assertEquals(String.valueOf(result.getJSONObject("address").getJSONObject("geo").get("lat")), JSONReader.readByKey("lat"));
        AqualityServices.getLogger().info("Checking lng");
        Assert.assertEquals(String.valueOf(String.valueOf(result.getJSONObject("address").getJSONObject("geo").get("lng"))), JSONReader.readByKey("lng"));
        AqualityServices.getLogger().info("Checking phone");
        Assert.assertEquals(String.valueOf(result.get("phone")), JSONReader.readByKey("phone"));
        AqualityServices.getLogger().info("Checking website");
        Assert.assertEquals(String.valueOf(result.get("website")), JSONReader.readByKey("website"));
        AqualityServices.getLogger().info("Checking company name");
        Assert.assertEquals(String.valueOf(result.getJSONObject("company").get("name")), JSONReader.readByKey("companyname"));
        AqualityServices.getLogger().info("Checking catchPhrase");
        Assert.assertEquals(String.valueOf(result.getJSONObject("company").get("catchPhrase")), JSONReader.readByKey("catchPhrase"));
        AqualityServices.getLogger().info("Checking bs");
        Assert.assertEquals(String.valueOf(result.getJSONObject("company").get("bs")), JSONReader.readByKey("bs"));
    }




}
