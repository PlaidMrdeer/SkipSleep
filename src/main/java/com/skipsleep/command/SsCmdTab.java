package com.skipsleep.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SsCmdTab implements TabCompleter {
    List<String> tab = new ArrayList<>();
    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command cmd, @NonNull String label, String[] args) {
        if (args.length == 1) {
            tab.clear();
            tab.add("on");
            tab.add("off");
            tab.add("set");
            tab.add("model");
            tab.add("reload");
            tab.add("help");
            tab.add("update");
            if (args[0].length() != 0) {
                tab.clear();
                if (args[0].charAt(0) == 'o') {
                    tab.add("on");
                    tab.add("off");
                    if (args[0].length() == 2) {
                        tab.clear();
                        if (args[0].charAt(1) == 'f') {
                            tab.add("off");
                        }
                    }
                } else if (args[0].charAt(0) == 's') {
                    tab.add("set");
                } else if (args[0].charAt(0) == 'm') {
                    tab.add("model");
                } else if (args[0].charAt(0) == 'r') {
                    tab.add("reload");
                } else if (args[0].charAt(0) == 'h') {
                    tab.add("help");
                } else if (args[0].charAt(0) == 'u') {
                    tab.add("update");
                }
                return tab;
            }
            return tab;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
            return Collections.singletonList("<Number of people>");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("model")) {
            tab.clear();
            tab.add("num");
            tab.add("pet");
            tab.add("set");
            if (args[1].length() != 0) {
                tab.clear();
                if (args[1].charAt(0) == 'n') {
                    tab.add("num");
                } else if (args[1].charAt(0) == 'p') {
                    tab.add("pet");
                }  else if (args[1].charAt(0) == 's') {
                    tab.add("set");
                }
            }
            return tab;
        } else if (args.length == 3 && args[1].equalsIgnoreCase("set")) {
            return Collections.singletonList("<percentage>");
        } else if (args.length == 2 && args[0].equalsIgnoreCase("update")) {
            tab.clear();
            tab.add("on");
            tab.add("off");
            if (args[1].length() != 0) {
                tab.clear();
                if (args[1].charAt(0) == 'o') {
                    tab.add("on");
                    tab.add("off");
                    if (args[1].length() == 2) {
                        tab.clear();
                        if (args[0].charAt(1) == 'f') {
                            tab.add("off");
                        }
                    }
                }
            }
            return tab;
        }
        return null;
    }
}
