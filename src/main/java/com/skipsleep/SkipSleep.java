package com.skipsleep;

import com.skipsleep.Listener.PlayerJoinInspect;
import com.skipsleep.Listener.SleepEvent;
import com.skipsleep.bstats.Metrics;
import com.skipsleep.command.SsCmd;
import com.skipsleep.command.SsCmdTab;
import com.skipsleep.language.Language;
import com.skipsleep.update.UpdateCheck;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SkipSleep extends JavaPlugin {
    private static SkipSleep plugin;

    private static boolean isNewVersion;
    public static SkipSleep getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        if (UpdateCheck.isUpDate()) {
            isNewVersion = UpdateCheck.isLatestVersion();
        }
        Language.loadLanguage(getPlugin());


        say("");
        say(ChatColor.AQUA + "  ____  _    _      ____  _                 ");
        say(ChatColor.AQUA + " / ___|| | _(_)_ __/ ___|| | ___  ___ _ __  ");
        say(ChatColor.AQUA + " \\___ \\| |/ / | '_ \\___ \\| |/ _ \\/ _ \\ '_ \\ ");
        say(ChatColor.AQUA + "  ___) |   <| | |_) |__) | |  __/  __/ |_) |");
        say(ChatColor.AQUA + " |____/|_|\\_\\_| .__/____/|_|\\___|\\___| .__/ ");
        say(ChatColor.AQUA + "              |_|                    |_|    ");
        if (UpdateCheck.isUpDate()) {
            say(ChatColor.YELLOW + "[" + ChatColor.GREEN + "SkipSleep" + ChatColor.YELLOW + "]" + Language.CURRENT_VERSION);
            say(ChatColor.YELLOW + "[" + ChatColor.GREEN + "SkipSleep" + ChatColor.YELLOW + "]" + Language.INSPECT_NEW_VERSION);
            if (isNewVersion) {
                say(ChatColor.YELLOW + "[" + ChatColor.GREEN + "SkipSleep" + ChatColor.YELLOW + "]" + Language.NO_NEW_VERSION);
            } else {
                say(ChatColor.YELLOW + "[" + ChatColor.GREEN + "SkipSleep" + ChatColor.YELLOW + "]" + Language.DETECTED_NEW_VERSION);
                say(ChatColor.YELLOW + "[" + ChatColor.GREEN + "SkipSleep" + ChatColor.YELLOW + "]" + Language.NEW_VERSION_WEBSITE);
            }
        }
        say("");

        getServer().getPluginManager().registerEvents(new SleepEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinInspect(), this);

        Objects.requireNonNull(getCommand("sks")).setExecutor(new SsCmd());
        Objects.requireNonNull(getCommand("sks")).setTabCompleter(new SsCmdTab());

        int pluginId = 16211;
        Metrics metrics = new Metrics(this, pluginId);
    }
    private void say(String say) {
        getServer().getConsoleSender().sendMessage(say);
    }
    @Override
    public void onDisable() {}
}
