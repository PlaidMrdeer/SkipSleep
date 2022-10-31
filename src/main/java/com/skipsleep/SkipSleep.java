package com.skipsleep;

import com.skipsleep.Listener.SleepEvent;
import com.skipsleep.bstats.Metrics;
import com.skipsleep.command.SsCmd;
import com.skipsleep.command.SsCmdTab;
import com.skipsleep.thead.InspectNum;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SkipSleep extends JavaPlugin {
    private static SkipSleep plugin;
    public static SkipSleep getPlugin() {
        return plugin;
    }
    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        InspectNum.num();

        say(ChatColor.AQUA + "  ____  _    _      ____  _                 ");
        say(ChatColor.AQUA + " / ___|| | _(_)_ __/ ___|| | ___  ___ _ __  ");
        say(ChatColor.AQUA + " \\___ \\| |/ / | '_ \\___ \\| |/ _ \\/ _ \\ '_ \\ ");
        say(ChatColor.AQUA + "  ___) |   <| | |_) |__) | |  __/  __/ |_) |");
        say(ChatColor.AQUA + " |____/|_|\\_\\_| .__/____/|_|\\___|\\___| .__/ ");
        say(ChatColor.AQUA + "              |_|                    |_|    ");

        getServer().getPluginManager().registerEvents(new SleepEvent(), this);

        Objects.requireNonNull(getServer().getPluginCommand("sks")).setExecutor(new SsCmd());
        Objects.requireNonNull(getServer().getPluginCommand("sks")).setTabCompleter(new SsCmdTab());

        int pluginId = 16211;
        Metrics metrics = new Metrics(this, pluginId);
    }
    private void say(String say) {
        getServer().getConsoleSender().sendMessage(say);
    }
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§b" + getName() + "已卸载");
    }
}
