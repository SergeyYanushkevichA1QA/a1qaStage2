package by.a1qa.entity;

import by.a1qa.models.Post;
import by.a1qa.service.Utils;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class PostsResponse extends Response<List<Post>> {

    public PostsResponse(HttpResponse<String> response) {
        super(response);
    }

    @Override
    protected List<Post> getInstance() {
        return Arrays.asList(Utils.readObjectFromJSON(getBody(), Post[].class));
    }

}
