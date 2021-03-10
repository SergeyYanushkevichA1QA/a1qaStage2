package by.a1qa.service;


import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.models.Response;

import org.apache.http.entity.mime.MultipartEntityBuilder;;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VkApiUtils {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static ISettingsFile testdata = new JsonSettingsFile("testdata.json");
    private static final String urlMethod = environment.getValue("/urlMethod").toString();
    private static final String version = "5.126";
    private static final String access_token = "0f523d9cbcbf64e369cbb526b0fad532c8b4b94658384fff9e6119760ac978a6d00a9a2a56940f3f4619c";
    private static final String wall_post = "wall.post?";
    private static final String wall_edit = "wall.edit?";
    private static final String wall_addComment = "wall.createComment?";
    private static final String wall_delete = "wall.delete?";
    private static final String photo_getWallUploadUrl = "photos.getWallUploadServer?";
    private static final String photo_saveWallPhoto = "photos.saveWallPhoto?";
    private static final String likes_isLiked = "likes.isLiked?";


    private static final Map<String, String> commonParams = new HashMap<String, String>() {
        {
            put("v", version);
            put("access_token", access_token);
        }
    };

    private static Map<String, String> getCommonParams() {
        return new HashMap<>(commonParams);
    }

    public int wallPost(String message) throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("message", message);
        String body = execute(wall_post, params);
        return Utils.getObjectNode(body).get("response").get("post_id").asInt();
    }

    public void wallEdit(int postId, String message, File image) throws IOException {
        String photoName = saveWallPhoto(image);
        Map<String, String> params = getCommonParams();
        params.put("post_id", String.valueOf(postId));
        params.put("message", message);
        params.put("attachments", photoName);
        execute(wall_edit, params);
    }

    private static String execute(HttpPost httpPost) throws IOException {
        String body;
        CloseableHttpClient client = HttpClients.createDefault();
        body = EntityUtils.toString(client.execute(httpPost).getEntity());
        return body;
    }

    protected static String execute(String method, Map<String, String> params) throws IOException {
        String parametersString = StringUtils.getParametersString(params);
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
        params.put("user_id", testdata.getValue("/id").toString());
        String responseBody = execute(photo_getWallUploadUrl, params);
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
        params.put("user_id", testdata.getValue("/id").toString());
        params.put("photo", photo);
        params.put("server", server);
        params.put("hash", hash);
        responseBody = execute(photo_saveWallPhoto, params);
        String photoId = Utils.getObjectNode(responseBody).get("response").get(0).get("id").asText();
        String ownerId = Utils.getObjectNode(responseBody).get("response").get(0).get("owner_id").asText();
        return "photo" + ownerId + "_" + photoId;
    }

    public static void createReply(int postId, String message) throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("post_id", String.valueOf(postId));
        params.put("message", message);
        execute(wall_addComment, params);
    }

    private static Response getIsLiked(int postId) throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("type", "post");
        params.put("item_id", String.valueOf(postId));
        return getResponse(likes_isLiked, params);
    }

    public boolean isLiked(int postId) throws IOException {
        Response response = getIsLiked(postId);
        return response.getIntValue("liked") == 1;
    }

    public void deletePost(int postId) throws IOException {
        Map<String, String> params = getCommonParams();
        params.put("post_id", String.valueOf(postId));
        execute(wall_delete, params);
    }
}
