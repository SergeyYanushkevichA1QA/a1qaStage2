package by.a1qa.models;

public class Comment {
    private static String message;
    private static String id;

    public static String getMessage() {
        return message;
    }

    public static String setMessage(String message) {
        Comment.message = message;
        return message;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Comment.id = id;
    }

}
