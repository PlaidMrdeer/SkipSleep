package com.skipsleep.command;

import com.skipsleep.SkipSleep;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class SsCmd implements CommandExecutor {
    Player p;
    int num;
    int petNum;
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command cmd, @NonNull String label, String[] args) {
        if (sender instanceof Player) {
            p = (Player) sender;
        }
        if (cmd.getName().equalsIgnoreCase("sks")) {
            if (sender == Bukkit.getConsoleSender() || p.isOp()) {
                if (args.length != 0) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args.length == 2) {
                            try {
                                num = Integer.parseInt(args[1]);
                            } catch (NumberFormatException e) {
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "你应该输入数字!");

                                } else {
                                    p.sendMessage(ChatColor.RED + "你应该输入数字!");
                                }
                                return true;
                            }
                            SkipSleep.getPlugin().getConfig().set("skipNum", num);
                            SkipSleep.getPlugin().saveConfig();
                            if (sender == Bukkit.getConsoleSender()) {
                                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "已设置达到 §l§e" + num + "人" + ChatColor.GREEN + " 睡觉跳过夜晚");
                            } else {
                                p.sendMessage(ChatColor.GREEN + "已设置达到 §l§e" + num + "人" + ChatColor.GREEN + " 睡觉跳过夜晚");
                            }
                        } else {
                            if (sender == Bukkit.getConsoleSender()) {
                                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "命令错误!");
                            } else {
                                p.sendMessage(ChatColor.RED + "命令错误!");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("model")) {
                        if (args.length == 2) {
                            if (args[1].equalsIgnoreCase("num")) {
                                SkipSleep.getPlugin().getConfig().set("model", "num");
                                SkipSleep.getPlugin().saveConfig();
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "成功切换到人数跳过");
                                } else {
                                    p.sendMessage(ChatColor.AQUA + "成功切换到人数跳过");
                                }
                            } else if (args[1].equalsIgnoreCase("pet")) {
                                SkipSleep.getPlugin().getConfig().set("model", "pet");
                                SkipSleep.getPlugin().saveConfig();
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "成功切换到百分比跳过");
                                } else {
                                    p.sendMessage(ChatColor.AQUA + "成功切换到百分比跳过");
                                }
                            } else {
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "命令错误!");
                                } else {
                                    p.sendMessage(ChatColor.RED + "命令错误!");
                                }
                            }
                        } else if (args.length == 3) {
                            if (args[1].equalsIgnoreCase("set")) {
                                try {
                                    petNum = Integer.parseInt(args[2]);
                                } catch (NumberFormatException e) {
                                    if (sender == Bukkit.getConsoleSender()) {
                                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "你应该输入数字!");
                                    } else {
                                        p.sendMessage(ChatColor.RED + "你应该输入数字!");
                                    }
                                    return true;
                                }
                                if (petNum > 100) {
                                    p.sendMessage(ChatColor.RED + "请勿设置超过100%!");
                                    return true;
                                }
                                SkipSleep.getPlugin().getConfig().set("skipPet", petNum);
                                SkipSleep.getPlugin().saveConfig();
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "已设置达到 §l§e百分比" + petNum + ChatColor.GREEN + " 跳过夜晚");
                                } else {
                                    p.sendMessage(ChatColor.GREEN + "已设置达到 §l§e百分比" + petNum + ChatColor.GREEN + " 跳过夜晚");
                                }
                            } else {
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "命令错误!");
                                } else {
                                    p.sendMessage(ChatColor.RED + "命令错误!");
                                }
                            }
                        } else {
                            if (sender == Bukkit.getConsoleSender()) {
                                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "命令错误!");
                            } else {
                                p.sendMessage(ChatColor.RED + "命令错误!");
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("model") || args[0].equalsIgnoreCase("help")) {
                        subCmd(args[0], sender);
                    } else {
                        if (sender == Bukkit.getConsoleSender()) {
                            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "命令错误!");
                        } else {
                            p.sendMessage(ChatColor.RED + "命令错误!");
                        }
                    }
                } else {
                    if (sender == Bukkit.getConsoleSender()) {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "命令错误!");
                    } else {
                        p.sendMessage(ChatColor.RED + "命令错误!");
                    }
                }
            } else {
                p.sendMessage(ChatColor.RED + "你不是管理员!");
            }
            return true;
        }
        return false;
    }
    private void subCmd(@NonNull String sub, CommandSender sender) {
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
            case "help":
                help(sender);
                break;
        }
    }

    private void help(CommandSender sender) {
        if (sender == Bukkit.getConsoleSender()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "==============" + ChatColor.GREEN + "SkipSleep" + ChatColor.YELLOW + "==============");
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "/sks on--设置开启跳过功能");
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "/sks off--设置关闭跳过功能");
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "/sks set <数字>--设置达到几人睡觉跳过夜晚");
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "/sks model num--切换人数跳过");
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "/sks model pet--切换百分比跳过");
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "/sks model set <百分比>--设置达到多少百分比跳过夜晚");
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "/sks reload--重载配置文件");
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "/sks help--查看指令帮助");
        } else {
            p.sendMessage(ChatColor.YELLOW + "==============" + ChatColor.GREEN + "SkipSleep" + ChatColor.YELLOW + "==============");
            p.sendMessage(ChatColor.AQUA + "/sks on--设置开启跳过功能");
            p.sendMessage(ChatColor.AQUA + "/sks off--设置关闭跳过功能");
            p.sendMessage(ChatColor.AQUA + "/sks set <数字>--设置达到几人睡觉跳过夜晚");
            p.sendMessage(ChatColor.AQUA + "/sks model num--切换人数跳过");
            p.sendMessage(ChatColor.AQUA + "/sks model pet--切换百分比跳过");
            p.sendMessage(ChatColor.AQUA + "/sks model set <百分比>--设置达到多少百分比跳过夜晚");
            p.sendMessage(ChatColor.AQUA + "/sks reload--重载配置文件");
            p.sendMessage(ChatColor.AQUA + "/sks help--查看指令帮助");
        }
    }
}
