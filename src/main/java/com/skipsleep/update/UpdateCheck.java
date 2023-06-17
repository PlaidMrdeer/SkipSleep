package com.skipsleep.update;

import com.skipsleep.SkipSleep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UpdateCheck {
    public static String ver;

    private static String getLatestVersion() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new URL("https://api.spigotmc.org/legacy/update.php?resource=106146")
                        .openStream(), StandardCharsets.UTF_8))) {
            ver = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ver;
    }

    public static boolean isLatestVersion() {
        String latest = getLatestVersion();
        String current = SkipSleep.instance().getDescription().getVersion();
        return latest.equalsIgnoreCase(current);
    }

    public static boolean isUpDate() {
        return SkipSleep.instance().getConfig().getBoolean("update");
    }
}
