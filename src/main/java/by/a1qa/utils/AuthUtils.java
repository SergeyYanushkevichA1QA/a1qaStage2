package by.a1qa.utils;


public class AuthUtils {

    public static String getBasicAuthURL(String url, String username, String password) {
        return StringUtils.getAuthURL(url, username, password);
    }
}
