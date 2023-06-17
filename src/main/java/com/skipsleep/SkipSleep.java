package com.skipsleep;

import com.skipsleep.Listener.PlayerJoinInspect;
import com.skipsleep.Listener.SleepEvent;
import com.skipsleep.bstats.Metrics;
import com.skipsleep.command.*;
import com.skipsleep.command.tab.EnableCommandTab;
import com.skipsleep.command.tab.ModelCommandTab;
import com.skipsleep.command.tab.UpdateCommandTab;
import com.skipsleep.language.Language;
import com.skipsleep.update.UpdateCheck;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author PlaidMrdeer
 */
public final class SkipSleep extends JavaPlugin {
    private static SkipSleep instance;

    public static FileConfiguration config;

    public static SkipSleep instance() {
        return instance;
    }

    private void pluginLogo() {
        String logo1 = "  ____  _    _      ____  _                 ";
        String logo2 = " / ___|| | _(_)_ __/ ___|| | ___  ___ _ __  ";
        String logo3 = " \\___ \\| |/ / | '_ \\___ \\| |/ _ \\/ _ \\ '_ \\ ";
        String logo4 = "  ___) |   <| | |_) |__) | |  __/  __/ |_) | ";
        String logo5 = " |____/|_|\\_\\_| .__/____/|_|\\___|\\___| .__/ ";
        String logo6 = "              |_|                    |_|    ";

        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + logo1);
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + logo2);
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + logo3);
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + logo4);
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + logo5);
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + logo6);
    }

    private void version() {
        if (!UpdateCheck.isUpDate()) {
            return;
        }
        boolean isNewVersion = UpdateCheck.isLatestVersion();

        SkipSleep.sendMessage(Bukkit.getConsoleSender(), "CURRENT_VERSION");
        SkipSleep.sendMessage(Bukkit.getConsoleSender(), "INSPECT_NEW_VERSION");

        if (isNewVersion) {
            SkipSleep.sendMessage(Bukkit.getConsoleSender(), "NO_NEW_VERSION");
            return;
        }
        SkipSleep.sendMessage(Bukkit.getConsoleSender(), "DETECTED_NEW_VERSION");
        SkipSleep.sendMessage(Bukkit.getConsoleSender(), "NEW_VERSION_WEBSITE");
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("skipsleepenable"))
                .setExecutor(new SkipSleepEnableCommand());

        Objects.requireNonNull(getCommand("skipsleepnumber"))
                .setExecutor(new SkipSleepNumberCommand());

        Objects.requireNonNull(getCommand("skipsleepmodel"))
                .setExecutor(new SkipSleepModelCommand());

        Objects.requireNonNull(getCommand("skipsleeppet"))
                .setExecutor(new SkipSleepPetCommand());

        Objects.requireNonNull(getCommand("skipsleephelp"))
                .setExecutor(new SkipSleepHelpCommand());

        Objects.requireNonNull(getCommand("skipsleepreload"))
                .setExecutor(new SkipSleepReloadCommand());

        Objects.requireNonNull(getCommand("skipsleepupdate"))
                .setExecutor(new SkipSleepUpdateCommand());
    }

    private void registerCommandTbas() {
        Objects.requireNonNull(getCommand("skipsleepenable"))
                .setTabCompleter(new EnableCommandTab());

        Objects.requireNonNull(getCommand("skipsleepModel"))
                .setTabCompleter(new ModelCommandTab());

        Objects.requireNonNull(getCommand("skipsleepupdate"))
                .setTabCompleter(new UpdateCommandTab());
    }

    private void registerEvents() {
        getServer().getPluginManager()
                .registerEvents(new SleepEvent(), this);

        getServer().getPluginManager()
                .registerEvents(new PlayerJoinInspect(), this);
    }

    public static void sendMessage(@NotNull CommandSender sender, String langPath) {
        String msg = Language.setStyle(Language.lang.getString(langPath));

        sender.sendMessage(msg);
    }

    public static void broadcastMessage(String langPath) {
        String msg = Language.setStyle(Language.lang.getString(langPath));

        Bukkit.broadcastMessage(msg);
    }

    public static void reloadPlugin() {
        instance().reloadConfig();
        config = instance().getConfig();
        Language.loadLang();
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        config = getConfig();

        if (!Language.loadLang()) {
            return;
        }

        pluginLogo();

        new BukkitRunnable() {
            @Override
            public void run() {
                version();
                cancel();
            }
        }.runTaskAsynchronously(this);

        registerCommands();
        registerCommandTbas();

        registerEvents();

        int pluginId = 16211;
        Metrics metrics = new Metrics(this, pluginId);
    }
    @Override
    public void onDisable() {}
}
