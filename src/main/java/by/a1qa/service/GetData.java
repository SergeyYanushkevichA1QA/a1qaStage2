package by.a1qa.service;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.utils.AuthUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class GetData {
    private static CloseableHttpClient httpclient = HttpClients.createDefault();
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");
    private static String username;
    private static Boolean isAuth;

    public static void getUserAndAuth(String url) throws IOException {
        HttpUriRequest httpGetPosts = new HttpGet(AuthUtils.getBasicAuthURL(url, environment.getValue("/testdata/username").toString(),
                environment.getValue("/testdata/password").toString()));
        HttpResponse response1 = httpclient.execute(httpGetPosts);
        HttpEntity data = response1.getEntity();
        JSONObject result = new JSONObject(EntityUtils.toString(data));
        setUsername((String) result.get("user"));
        setIsAuth((Boolean) result.get("authenticated"));
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        GetData.username = username;
    }

    public static Boolean getIsAuth() {
        return isAuth;
    }

    public static void setIsAuth(Boolean isAuth) {
        GetData.isAuth = isAuth;
    }
}
