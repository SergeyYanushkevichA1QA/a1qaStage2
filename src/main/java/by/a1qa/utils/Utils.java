package by.a1qa.utils;


import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.io.IOException;

public class Utils {
    private static final ISettingsFile environment = new JsonSettingsFile("settings.json");

    public static void autoUploadImage() {
        try {
            Process process = Runtime.getRuntime().exec(environment.getValue("/pathForScript").toString()
                    + " "  + environment.getValue("/pathForPic").toString());
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            AqualityServices.getLogger().info("Couldn't click to open button to attach file using AutoIt script: " + e.toString());
        }
    }

}
