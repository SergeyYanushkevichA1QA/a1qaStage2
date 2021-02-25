package by.a1qa.service;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.entity.PostResponse;
import by.a1qa.entity.PostsResponse;
import by.a1qa.entity.UserResponse;
import by.a1qa.entity.UsersResponse;
import by.a1qa.models.Post;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIUtils {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static String url = environment.getValue("/testdata/url").toString();
    private static final String headerName = "content-type";
    private static final String headerValue = "application/json";


    public static HttpRequest getPostRequest(String path, String json) {
        AqualityServices.getLogger().info("Send request: POST " + path);
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header(headerName, headerValue)
                .uri(URI.create(url + path))
                .build();
    }

    public static HttpRequest getGetRequest(String path) {
        return HttpRequest.newBuilder()
                .GET()
                .header(headerName, headerValue)
                .uri(URI.create(url + path))
                .build();
    }

    public static HttpResponse<String> getResponse(HttpRequest request) {
        AqualityServices.getLogger().info("Send request: " + request.toString());
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return response;
    }

    public static PostsResponse getPosts() {
        return new PostsResponse(getResponse(getGetRequest(environment.getValue("/api/methods/get/posts").toString())));
    }

    public static PostResponse getPost(int number) {

        return new PostResponse(getResponse(getGetRequest(environment.getValue("/api/methods/get/posts").toString() + "/" + number)));
    }

    public static PostResponse setPost(Post post) {
        return new PostResponse(getResponse(getPostRequest(environment.getValue("/api/methods/get/posts").toString(), Utils.toJSONString(post))));
    }

    public static UsersResponse getUsers() {
        return new UsersResponse(getResponse(getGetRequest(environment.getValue("/api/methods/get/users").toString())));
    }

    public static UserResponse getUser(int number) {
        return new UserResponse(getResponse(getGetRequest(environment.getValue("/api/methods/get/users").toString() + "/" + number)));
    }


}
