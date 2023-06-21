package com.skipsleep.language;

import com.skipsleep.Listener.SleepEvent;
import com.skipsleep.SkipSleep;
import com.skipsleep.update.UpdateCheck;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Language {
    public static FileConfiguration lang;
    public static boolean loadLang() {
        List<String> L = Arrays.asList("english.yml", "chinese.yml", "german.yml");
        for (String l : L) {
            if (!(new File("plugins" + File.separator + "SKipSleep" + File.separator + "lang" + File.separator + l)).exists()) {
                SkipSleep.instance().saveResource("lang/" + l, false);
            }
        }

        if (!(new File("plugins" + File.separator + "SKipSleep" + File.separator + "lang",
                SkipSleep.config.getString("Language.lang") + ".yml")).exists()) {

            SkipSleep.instance().getLogger().warning("Unknown language file!");
            Bukkit.getPluginManager().disablePlugin(SkipSleep.instance());
            return false;
        }

        File file = new File("plugins" + File.separator + "SKipSleep" + File.separator + "lang",
                SkipSleep.config.getString("Language.lang") + ".yml");
        lang = YamlConfiguration.loadConfiguration(file);
        return true;
    }

    public static String setStyle(String msg) {
        String finalMsg = msg;
        finalMsg = finalMsg.replace(
                "{prefix}", Objects.requireNonNull(lang.getString("prefix"))
        );
        finalMsg = ChatColor.translateAlternateColorCodes('&', finalMsg);
        if (SkipSleep.instance().getConfig().getBoolean("update")) {
            finalMsg = finalMsg.replace("{version}", SkipSleep.instance().getDescription().getVersion());
            finalMsg = finalMsg.replace("{new_version}", UpdateCheck.ver);
        }
        finalMsg = finalMsg.replace("{totalNum}", "" + SkipSleep.instance().getConfig().getInt("skipNum"));
        finalMsg = finalMsg.replace("{totalPet}", "" + SkipSleep.instance().getConfig().getInt("skipPet"));
        finalMsg = finalMsg.replace("{pet}", "" + SleepEvent.resultInt);
        finalMsg = finalMsg.replace("{num}", "" + SleepEvent.num1);
        return finalMsg;
    }
}
