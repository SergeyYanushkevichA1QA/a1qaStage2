package by.a1qa.entity;

import by.a1qa.models.Post;
import by.a1qa.service.Utils;

import java.net.http.HttpResponse;

public class PostResponse extends Response<Post> {
    public PostResponse(HttpResponse<String> response) {
        super(response);
    }

    @Override
    protected Post getInstance() {
        return Utils.readObjectFromJSON(getBody(), Post.class);
    }
}