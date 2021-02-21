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

    public static PostsResponse getPosts(String path) {
        return new PostsResponse(getResponse(getGetRequest(path)));
    }

    public static PostResponse getPost(String path) {
        return new PostResponse(getResponse(getGetRequest(path)));
    }

    public static PostResponse setPost(Post post) {
        return new PostResponse(getResponse(getPostRequest(environment.getValue("/testdata/methods/get/posts").toString(), Utils.toJSONString(post))));
    }

    public static UsersResponse getUsers(String path) {
        return new UsersResponse(getResponse(getGetRequest(path)));
    }

    public static UserResponse getUser(String path) {
        return new UserResponse(getResponse(getGetRequest(path)));
    }


}
