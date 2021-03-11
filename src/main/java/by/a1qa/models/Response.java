package by.a1qa.models;

import by.a1qa.service.Utils;
import com.fasterxml.jackson.databind.JsonNode;

public class Response {
    private static final String jsonName = "response";
    private final String body;

    public Response(String body) {
        this.body = body;
    }

    public JsonNode getValue(String name) {
        return Utils.getObjectNode(body).get(jsonName).get(name);
    }

    public int getIntValue(String name) {
        return getValue(name).asInt();
    }
}
