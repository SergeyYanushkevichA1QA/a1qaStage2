package by.a1qa.service;


import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.models.Response;

import org.apache.http.entity.mime.MultipartEntityBuilder;;
import org.apache.http.HttpEntity;
import org.apache.http.*;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VkApiUtils {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static final String urlMethod = "https://api.vk.com/method/";

    private static final Map<String, String> commonParams = new HashMap<String, String>() {
        {
            put("v", (String) environment.getValue("/testdata/ver"));
            put("access_token", (String) environment.getValue("/testdata/access_token"));
        }
    };

    private static Map<String, String> getCommonParams() {
        return new HashMap<>(commonParams);
    }

    public String wallPost(String message) throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("message", message);
        String body = execute((String) environment.getValue("/testdata/apiMethods/wall/post"), params);
        return Utils.getObjectNode(body).get("response").get("post_id").asText();
    }

    public void wallEdit(String postId, String message, File image) throws IOException {
        String photoName = saveWallPhoto(image);
        Map<String, String> params = getCommonParams();
        params.put("post_id", postId);
        params.put("message", message);
        params.put("attachments", photoName);
        execute((String) environment.getValue("/testdata/apiMethods/wall/edit"), params);
    }

    private static String execute(HttpPost httpPost) throws IOException {
        String body;
        CloseableHttpClient client = HttpClients.createDefault();
        body = EntityUtils.toString(client.execute(httpPost).getEntity());
        return body;
    }

    protected static String execute(String method, Map<String, String> params) throws IOException {
        String parametersString = Utils.getParametersString(params);
        HttpEntity entity = EntityBuilder.create()
                .setContentType(ContentType.APPLICATION_FORM_URLENCODED)
                .setText(parametersString)
                .build();
        HttpPost httpPost = new HttpPost(urlMethod + method);
        httpPost.setEntity(entity);
        return execute(httpPost);
    }

    private static Response getResponse(String method, Map<String, String> params) throws IOException {
        return new Response(execute(method, params));
    }

    private static String getPhotoWallUploadUrl() throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("user_id", (String) environment.getValue("/testdata/userId"));
        String responseBody = execute((String) environment.getValue("/testdata/apiMethods/photo/getWallUploadUrl"), params);
        return Utils.getObjectNode(responseBody).get("response").get("upload_url").asText();
    }

    private static String saveWallPhoto(File file) throws IOException {
        HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("photo", file).build();
        HttpPost post = new HttpPost(getPhotoWallUploadUrl());
        post.setEntity(entity);
        String responseBody = execute(post);
        String server = Utils.getObjectNode(responseBody).get("server").asText();
        String photo = Utils.getObjectNode(responseBody).get("photo").asText();
        String hash = Utils.getObjectNode(responseBody).get("hash").asText();
        Map<String, String> params = getCommonParams();
        params.put("user_id", (String) environment.getValue("/testdata/userId"));
        params.put("photo", photo);
        params.put("server", server);
        params.put("hash", hash);
        responseBody = execute((String) environment.getValue("/testdata/apiMethods/photo/saveWallPhoto"), params);
        String photoId = Utils.getObjectNode(responseBody).get("response").get(0).get("id").asText();
        String ownerId = Utils.getObjectNode(responseBody).get("response").get(0).get("owner_id").asText();
        return "photo" + ownerId + "_" + photoId;
    }

    public static void createReply(String postId, String message) throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("post_id", postId);
        params.put("message", message);
        execute((String) environment.getValue("/testdata/apiMethods/wall/addcomment"), params);
    }

    private static Response getIsLiked(String postId) throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("type", "post");
        params.put("item_id", postId);
        return getResponse((String) environment.getValue("/testdata/apiMethods/likes/isLiked"), params);
    }

    public boolean isLiked(String postId) throws IOException {
        Response response = getIsLiked(postId);
        return response.getIntValue("liked") == 1;
    }

    public void deletePost(String postId) throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("post_id", postId);
        execute((String) environment.getValue("/testdata/apiMethods/wall/delete"), params);
    }
}
