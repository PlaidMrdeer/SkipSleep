package com.skipsleep.update;

import com.skipsleep.SkipSleep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UpdateCheck {
    public static String ver;
    public static String getLatestVersion() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://49.234.10.176").openStream(), StandardCharsets.UTF_8))) {
            ver = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ver;
    }

    public static boolean isLatestVersion() {
        String latest = getLatestVersion();
        String current = SkipSleep.getPlugin().getDescription().getVersion();
        return latest.equalsIgnoreCase(current);
    }

    public static boolean isUpDate() {
        return SkipSleep.getPlugin().getConfig().getBoolean("update");
    }
}
