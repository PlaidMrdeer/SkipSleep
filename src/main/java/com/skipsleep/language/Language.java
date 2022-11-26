package com.skipsleep.language;

import com.skipsleep.Listener.SleepEvent;
import com.skipsleep.SkipSleep;
import com.skipsleep.update.UpdateCheck;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Objects;

public class Language {
    private static YamlConfiguration language;


    public static String CURRENT_VERSION = null;
    public static String INSPECT_NEW_VERSION = null;
    public static String DETECTED_NEW_VERSION = null;
    public static String NO_NEW_VERSION = null;
    public static String NEW_VERSION_WEBSITE = null;


    public static String COMMAND_SET_NO_NUMBER = null;
    public static String COMMAND_SET_NUMBER_SUCCESS = null;
    public static String COMMAND_ERROR = null;
    public static String COMMAND_SWITCH_MODEL_SUCCESS = null;
    public static String COMMAND_PET_EXCEED_100 = null;
    public static String COMMAND_SET_PET_SUCCESS = null;
    public static String COMMAND_NO_PERMISSION = null;
    public static String COMMAND_SKIP_ON = null;
    public static String COMMAND_SKIP_OFF = null;
    public static String COMMAND_RELOAD = null;
    public static String COMMAND_UPDATE = null;


    public static String HELP_HEAD = null;
    public static String HELP_COMMAND_ON = null;
    public static String HELP_COMMAND_OFF = null;
    public static String HELP_COMMAND_SET_NUMBER = null;
    public static String HELP_COMMAND_MODEL_NUM = null;
    public static String HELP_COMMAND_MODEL_PET = null;
    public static String HELP_COMMAND_MODEL_SET_PET = null;
    public static String HELP_COMMAND_RELOAD = null;
    public static String HELP_COMMAND_HELP = null;


    public static String SKIP_NIGHT_NUMBER_FULL = null;
    public static String SKIP_PET_TOTAL_PET = null;
    public static String SKIP_NIGHT = null;
    public static String SKIP_FAILED = null;
    public static String SKIP_NUMBER_TOTAL_NUMBER = null;



    public static void loadLanguage(SkipSleep instance) {
        File file = new File(SkipSleep.getPlugin().getDataFolder(), "language.yml");
        if (!file.exists()) {
            instance.saveResource("language.yml", false);
        }
        language = YamlConfiguration.loadConfiguration(file);

        getLanguage();
    }

    public static void getLanguage() {
        CURRENT_VERSION = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("CURRENT_VERSION"))));
        INSPECT_NEW_VERSION = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("INSPECT_NEW_VERSION"))));
        DETECTED_NEW_VERSION = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("DETECTED_NEW_VERSION"))));
        NO_NEW_VERSION = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("NO_NEW_VERSION"))));
        NEW_VERSION_WEBSITE = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("NEW_VERSION_WEBSITE"))));

        COMMAND_SET_NO_NUMBER = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_SET_NO_NUMBER"))));
        COMMAND_SET_NUMBER_SUCCESS = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_SET_NUMBER_SUCCESS"))));
        COMMAND_ERROR = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_ERROR"))));
        COMMAND_SWITCH_MODEL_SUCCESS = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_SWITCH_MODEL_SUCCESS"))));
        COMMAND_PET_EXCEED_100 = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_PET_EXCEED_100"))));
        COMMAND_SET_PET_SUCCESS = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_SET_PET_SUCCESS"))));
        COMMAND_NO_PERMISSION = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_NO_PERMISSION"))));
        COMMAND_SKIP_ON = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_SKIP_ON"))));
        COMMAND_SKIP_OFF = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_SKIP_OFF"))));
        COMMAND_RELOAD = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_RELOAD"))));
        COMMAND_UPDATE = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("COMMAND_UPDATE"))));

        HELP_HEAD = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("HELP_HEAD"))));
        HELP_COMMAND_ON = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("HELP_COMMAND_ON"))));
        HELP_COMMAND_OFF = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("HELP_COMMAND_OFF"))));
        HELP_COMMAND_SET_NUMBER = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("HELP_COMMAND_SET_NUMBER"))));
        HELP_COMMAND_MODEL_NUM = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("HELP_COMMAND_MODEL_NUM"))));
        HELP_COMMAND_MODEL_PET = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("HELP_COMMAND_MODEL_PET"))));
        HELP_COMMAND_MODEL_SET_PET = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("HELP_COMMAND_MODEL_SET_PET"))));
        HELP_COMMAND_RELOAD = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("HELP_COMMAND_RELOAD"))));
        HELP_COMMAND_HELP = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("HELP_COMMAND_HELP"))));

        SKIP_NIGHT_NUMBER_FULL = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("SKIP_NIGHT_NUMBER_FULL"))));
        SKIP_PET_TOTAL_PET = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("SKIP_PET_TOTAL_PET"))));
        SKIP_NIGHT = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("SKIP_NIGHT"))));
        SKIP_FAILED = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("SKIP_FAILED"))));
        SKIP_NUMBER_TOTAL_NUMBER = ColorHandle(VarHandle(Objects.requireNonNull(language.getString("SKIP_NUMBER_TOTAL_NUMBER"))));
    }

    private static String ColorHandle(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    private static String VarHandle(String msg) {
        String result = msg;
        if (SkipSleep.getPlugin().getConfig().getBoolean("update")) {
            result = result.replace("{version}", SkipSleep.getPlugin().getDescription().getVersion());
            result = result.replace("{new_version}", UpdateCheck.ver);
        }
        result = result.replace("{totalNum}", "" + SkipSleep.getPlugin().getConfig().getInt("skipNum"));
        result = result.replace("{totalPet}", "" + SkipSleep.getPlugin().getConfig().getInt("skipPet"));
        result = result.replace("{pet}", "" + SleepEvent.resultInt);
        result = result.replace("{num}", "" + SleepEvent.num1);
        return result;
    }
}
