package by.a1qa.entity;

import by.a1qa.models.User;
import by.a1qa.service.Utils;

import java.net.http.HttpResponse;

public class UserResponse extends Response<User> {

    public UserResponse(HttpResponse<String> response) {
        super(response);
    }

    @Override
    protected User getInstance() {
        return Utils.readObjectFromJSON(getBody(), User.class);
    }
}
