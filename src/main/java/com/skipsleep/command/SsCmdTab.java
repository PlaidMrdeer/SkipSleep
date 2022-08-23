package com.skipsleep.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SsCmdTab implements TabCompleter {
    List<String> tab = new ArrayList<>();
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length == 1) {
            tab.add("on");
            tab.add("off");
            tab.add("set");
            tab.add("model");
            tab.add("reload");
            return tab;
        } else if (args.length == 2 && args[1].equalsIgnoreCase("model")) {
            tab.add("num");
            tab.add("pet");
            tab.add("set");
        }
        return null;
    }
}
