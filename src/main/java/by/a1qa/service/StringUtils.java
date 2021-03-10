package by.a1qa.service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StringUtils {

    public static String getParametersString(Map<String, String> params) {
        Set<String> values = params.entrySet().stream().map(o -> o.getKey() + "=" + o.getValue()).collect(Collectors.toSet());
        return String.join("&", values);
    }
}
