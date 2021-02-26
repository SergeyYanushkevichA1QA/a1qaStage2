package by.a1qa.utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class StringUtils {
    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    public static String getAuthURL(String url, String username, String password) {
        return environment.getValue("/testdata/https").toString() + environment.getValue("/testdata/username").toString() + ":"
                + environment.getValue("/testdata/password").toString() + "@"
                + environment.getValue("/testdata/url").toString().replace(environment.getValue("/testdata/https").toString(), "");
    }
}
