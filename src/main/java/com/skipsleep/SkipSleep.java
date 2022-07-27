package com.skipsleep;

import com.skipsleep.Listener.SleepEvent;
import com.skipsleep.command.SsCmd;
import com.skipsleep.command.SsCmdTab;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkipSleep extends JavaPlugin {
    private static Plugin plugin;
    public static Plugin getPlugin() {
        return plugin;
    }
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        say("§e[§aSkipSleep§e]§a=====================================");
        say("§e[§aSkipSleep§e]§a     §a作者:§b" + getDescription().getAuthors());
        say("§e[§aSkipSleep§e]§a     §aQQ交流群:§b759010920");
        say("§e[§aSkipSleep§e]§a     §a版本: §b" + getDescription().getVersion());
        say("§e[§aSkipSleep§e]§a=====================================");

        getServer().getPluginManager().registerEvents(new SleepEvent(), this);
        getServer().getPluginCommand("sks").setExecutor(new SsCmd());
        getServer().getPluginCommand("sks").setTabCompleter(new SsCmdTab());
    }
    private void say(String say) {
        getServer().getConsoleSender().sendMessage(say);
    }
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§b" + getName() + "已卸载");
    }
}
