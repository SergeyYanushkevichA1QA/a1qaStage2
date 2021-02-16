package by.a1qa.models;

import java.awt.image.BufferedImage;
import java.net.URI;

public class Post {
    private static String id;
    private static String message;
    private static String authorHref;
    private static BufferedImage photo;
    private static String photo_id;

    public String getAuthorHrefPath() {
        return URI.create(getAuthorHref()).getPath();
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Post.id = id;
    }

    public static String getMessage() {
        return message;
    }

    public static String setMessage(String message) {
        Post.message = message;
        return message;
    }

    public static String getAuthorHref() {
        return authorHref;
    }

    public static BufferedImage getPhoto() {
        return photo;
    }

    public static void setPhoto(BufferedImage photo) {
        Post.photo = photo;
    }

}