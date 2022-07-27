package com.skipsleep.command;

import com.skipsleep.SkipSleep;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SsCmd implements CommandExecutor {
    Player p;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender != Bukkit.getConsoleSender()) {
            p = (Player) sender;
        }
        if (cmd.getName().equalsIgnoreCase("sks")) {
            if (p.isOp() || sender == Bukkit.getConsoleSender()) {
                if (args.length != 0) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args.length == 2) {
                            int num = Integer.parseInt(args[1]);
                            SkipSleep.getPlugin().getConfig().set("skipNum", num);
                            SkipSleep.getPlugin().saveConfig();
                            p.sendMessage(ChatColor.GREEN + "已设置达到 " + ChatColor.YELLOW + num + "人" + ChatColor.GREEN + " 睡觉跳过夜晚");
                        } else {
                            p.sendMessage(ChatColor.RED + "命令错误!");
                            p.sendMessage(ChatColor.AQUA + "/sks set <数字>--设置达到几人睡觉跳过夜晚");
                        }
                    }
                    if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("set")) {
                        subCmd(args[0], sender);
                    } else {
                        p.sendMessage(ChatColor.RED + "命令错误!");
                        p.sendMessage(ChatColor.AQUA + "/sks on--设置开启跳过功能");
                        p.sendMessage(ChatColor.AQUA + "/sks off--设置关闭跳过功能");
                        p.sendMessage(ChatColor.AQUA + "/sks set <数字>--设置达到几人睡觉跳过夜晚");
                        p.sendMessage(ChatColor.AQUA + "/sks reload--重载配置文件");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "命令错误!");
                    p.sendMessage(ChatColor.AQUA + "/sks on--设置开启跳过功能");
                    p.sendMessage(ChatColor.AQUA + "/sks off--设置关闭跳过功能");
                    p.sendMessage(ChatColor.AQUA + "/sks set <数字>--设置达到几人睡觉跳过夜晚");
                    p.sendMessage(ChatColor.AQUA + "/sks reload--重载配置文件");
                }
            } else {
                p.sendMessage(ChatColor.RED + "你不是管理员!");
            }
            return true;
        }
        return false;
    }
    private void subCmd(String sub, CommandSender sender) {
        switch (sub) {
            case "on":
                SkipSleep.getPlugin().getConfig().set("skip", true);
                SkipSleep.getPlugin().saveConfig();
                if (sender == Bukkit.getConsoleSender()) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "跳过睡觉开启!");
                } else {
                    p.sendMessage(ChatColor.GREEN + "跳过睡觉开启!");
                }
                break;
            case "off":
                SkipSleep.getPlugin().getConfig().set("skip", false);
                SkipSleep.getPlugin().saveConfig();
                if (sender == Bukkit.getConsoleSender()) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "跳过睡觉关闭!");
                } else {
                    p.sendMessage(ChatColor.GREEN + "跳过睡觉关闭!");
                }
                break;
            case "reload":
                SkipSleep.getPlugin().reloadConfig();
                if (sender == Bukkit.getConsoleSender()) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + SkipSleep.getPlugin().getDescription().getName() + ChatColor.GREEN + " 重载成功!");
                } else {
                    p.sendMessage(ChatColor.GREEN + "重载成功!");
                }
                break;
            case "set":
                break;
        }
    }
}
