package by.a1qa.service;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.models.Post;
import by.a1qa.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Ordering;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Utils {

    private static ISettingsFile environment = new JsonSettingsFile("settings.json");

    public static boolean isJson(String content) {
        AqualityServices.getLogger().info("Check if string is JSON");
        try {
            new ObjectMapper().readTree(content);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String toJSONString(Object object) {
        AqualityServices.getLogger().info("Write " + object.getClass().getSimpleName() + " to JSON string");
        String result = "{}";
        try {
            result = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return result;
    }

    public static <T> T readObjectFromJSON(String json, Class<T> objectClass) {
        AqualityServices.getLogger().info("Read " + objectClass.getSimpleName() + " from JSON string");
        T object = null;
        try {
            object = new ObjectMapper().readValue(json, objectClass);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return object;
    }

    public static User getUserN5() {
        return Utils.readObjectFromJSON(environment.getValue("/testdata/testUser").toString(), User.class);
    }

    public static boolean isSortedById(List<Post> posts) {
        int[] getSortedIdArr = posts.stream().mapToInt(Post::getId).sorted().toArray();
        return IntStream.range(0, posts.size() - 1).noneMatch(i -> posts.get(i).getId() != getSortedIdArr[i]);
    }
}