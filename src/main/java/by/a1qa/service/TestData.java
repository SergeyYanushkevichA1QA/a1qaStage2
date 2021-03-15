package by.a1qa.service;

public class TestData {
    private static final String jSAlertText = "I am a JS Alert";
    private static final String jSAlertResultText = "You successfully clicked an alert";
    private static final String confirmAlertText = "I am a JS Confirm";
    private static final String confirmAlertResultText = "You clicked: Ok";
    private static final String promptAlertText = "I am a JS prompt";
    private static final String promptAlertResultText = "You entered: %s";

    public static String getJSAlertText() {
        return jSAlertText;
    }

    public static String getJSAlertResultText() {
        return jSAlertResultText;
    }

    public static String getConfirmAlertText() {
        return confirmAlertText;
    }

    public static String getConfirmAlertResultText() {
        return confirmAlertResultText;
    }

    public static String getPromptAlertText() {
        return promptAlertText;
    }

    public static String getPromptAlertResultText(String text) {
        return String.format(promptAlertResultText, text);
    }
}
