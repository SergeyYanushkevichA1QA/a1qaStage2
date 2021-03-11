package by.a1qa.models;

import java.awt.image.BufferedImage;
import java.net.URI;

public class Post {
    private String id;
    private String message;
    private String authorHref;
    private BufferedImage photo;

    public String getAuthorHrefPath() {
        return URI.create(getAuthorHref()).getPath();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public String setMessage(String message) {
        this.message = message;
        return message;
    }

    public String getAuthorHref() {
        return authorHref;
    }

    public BufferedImage getPhoto() {
        return photo;
    }

    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }

    public void setAuthorHref(String authorHref) {
        this.authorHref = authorHref;
    }

}