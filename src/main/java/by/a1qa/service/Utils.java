package by.a1qa.service;

import aquality.selenium.browser.AqualityServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public static ObjectNode getObjectNode(String json) {
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(json, ObjectNode.class);
        } catch (IOException e) {
            AqualityServices.getLogger().error(e.getMessage());
        }
        return node;
    }

    public static String getParametersString(Map<String, String> params) {
        Set<String> values = params.entrySet().stream().map(o -> o.getKey() + "=" + o.getValue()).collect(Collectors.toSet());
        return String.join("&", values);
    }
}
