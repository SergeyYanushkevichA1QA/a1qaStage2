package by.a1qa.entity;

import by.a1qa.models.User;
import by.a1qa.service.Utils;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class UsersResponse extends Response<List<User>> {

    public UsersResponse(HttpResponse<String> response) {
        super(response);
    }

    @Override
    protected List<User> getInstance() {
        return Arrays.asList(Utils.readObjectFromJSON(getBody(), User[].class));
    }
}
