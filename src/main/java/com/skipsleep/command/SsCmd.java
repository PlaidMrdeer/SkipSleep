package com.skipsleep.command;

import com.skipsleep.SkipSleep;
import com.skipsleep.language.Language;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class SsCmd implements CommandExecutor {
    Player p;
    int totalNum;
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
                                totalNum = Integer.parseInt(args[1]);
                            } catch (NumberFormatException e) {
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_SET_NO_NUMBER);

                                } else {
                                    p.sendMessage(Language.COMMAND_SET_NO_NUMBER);
                                }
                                return true;
                            }
                            SkipSleep.getPlugin().getConfig().set("skipNum", totalNum);
                            SkipSleep.getPlugin().saveConfig();
                            Language.getLanguage();
                            if (sender == Bukkit.getConsoleSender()) {
                                Bukkit.getConsoleSender().sendMessage(Language.COMMAND_SET_NUMBER_SUCCESS);
                            } else {
                                p.sendMessage(Language.COMMAND_SET_NUMBER_SUCCESS);
                            }
                        } else {
                            if (sender == Bukkit.getConsoleSender()) {
                                Bukkit.getConsoleSender().sendMessage(Language.COMMAND_ERROR);
                            } else {
                                p.sendMessage(Language.COMMAND_ERROR);
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("model")) {
                        if (args.length == 2) {
                            if (args[1].equalsIgnoreCase("num")) {
                                SkipSleep.getPlugin().getConfig().set("model", "num");
                                SkipSleep.getPlugin().saveConfig();
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_SWITCH_MODEL_SUCCESS);
                                } else {
                                    p.sendMessage(Language.COMMAND_SWITCH_MODEL_SUCCESS);
                                }
                            } else if (args[1].equalsIgnoreCase("pet")) {
                                SkipSleep.getPlugin().getConfig().set("model", "pet");
                                SkipSleep.getPlugin().saveConfig();
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_SWITCH_MODEL_SUCCESS);
                                } else {
                                    p.sendMessage(Language.COMMAND_SWITCH_MODEL_SUCCESS);
                                }
                            } else {
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_ERROR);
                                } else {
                                    p.sendMessage(Language.COMMAND_ERROR);
                                }
                            }
                        } else if (args.length == 3) {
                            if (args[1].equalsIgnoreCase("set")) {
                                try {
                                    petNum = Integer.parseInt(args[2]);
                                } catch (NumberFormatException e) {
                                    if (sender == Bukkit.getConsoleSender()) {
                                        Bukkit.getConsoleSender().sendMessage(Language.COMMAND_SET_NO_NUMBER);
                                    } else {
                                        p.sendMessage(Language.COMMAND_SET_NO_NUMBER);
                                    }
                                    return true;
                                }
                                if (petNum > 100) {
                                    p.sendMessage(Language.COMMAND_PET_EXCEED_100);
                                    return true;
                                }
                                SkipSleep.getPlugin().getConfig().set("skipPet", petNum);
                                SkipSleep.getPlugin().saveConfig();
                                Language.getLanguage();
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_SET_PET_SUCCESS);
                                } else {
                                    p.sendMessage(Language.COMMAND_SET_PET_SUCCESS);
                                }
                            } else {
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_ERROR);
                                } else {
                                    p.sendMessage(Language.COMMAND_ERROR);
                                }
                            }
                        } else {
                            if (sender == Bukkit.getConsoleSender()) {
                                Bukkit.getConsoleSender().sendMessage(Language.COMMAND_ERROR);
                            } else {
                                p.sendMessage(Language.COMMAND_ERROR);
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("update")) {
                        if (args.length == 2) {
                            if (args[1].equalsIgnoreCase("on")) {
                                SkipSleep.getPlugin().getConfig().set("update", true);
                                SkipSleep.getPlugin().saveConfig();
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_UPDATE);
                                } else {
                                    p.sendMessage(Language.COMMAND_UPDATE);
                                }
                            } else if (args[1].equalsIgnoreCase("off")) {
                                SkipSleep.getPlugin().getConfig().set("update", false);
                                SkipSleep.getPlugin().saveConfig();
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_UPDATE);
                                } else {
                                    p.sendMessage(Language.COMMAND_UPDATE);
                                }
                            } else {
                                if (sender == Bukkit.getConsoleSender()) {
                                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_ERROR);
                                } else {
                                    p.sendMessage(Language.COMMAND_ERROR);
                                }
                            }
                        } else {
                            if (sender == Bukkit.getConsoleSender()) {
                                Bukkit.getConsoleSender().sendMessage(Language.COMMAND_ERROR);
                            } else {
                                p.sendMessage(Language.COMMAND_ERROR);
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("model") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("update")) {
                        subCmd(args[0], sender);
                    } else {
                        if (sender == Bukkit.getConsoleSender()) {
                            Bukkit.getConsoleSender().sendMessage(Language.COMMAND_ERROR);
                        } else {
                            p.sendMessage(Language.COMMAND_ERROR);
                        }
                    }
                } else {
                    if (sender == Bukkit.getConsoleSender()) {
                        Bukkit.getConsoleSender().sendMessage(Language.COMMAND_ERROR);
                    } else {
                        p.sendMessage(Language.COMMAND_ERROR);
                    }
                }
            } else {
                p.sendMessage(Language.COMMAND_NO_PERMISSION);
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
                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_SKIP_ON);
                } else {
                    p.sendMessage(Language.COMMAND_SKIP_ON);
                }
                break;
            case "off":
                SkipSleep.getPlugin().getConfig().set("skip", false);
                SkipSleep.getPlugin().saveConfig();
                if (sender == Bukkit.getConsoleSender()) {
                    Bukkit.getConsoleSender().sendMessage(Language.COMMAND_SKIP_OFF);
                } else {
                    p.sendMessage(Language.COMMAND_SKIP_OFF);
                }
                break;
            case "reload":
                SkipSleep.getPlugin().reloadConfig();
                Language.getLanguage();
                if (sender == Bukkit.getConsoleSender()) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + SkipSleep.getPlugin().getDescription().getName() + ChatColor.GREEN + "Reload succeeded!");
                } else {
                    p.sendMessage(Language.COMMAND_RELOAD);
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
            Bukkit.getConsoleSender().sendMessage(Language.HELP_HEAD);
            Bukkit.getConsoleSender().sendMessage(Language.HELP_COMMAND_ON);
            Bukkit.getConsoleSender().sendMessage(Language.HELP_COMMAND_OFF);
            Bukkit.getConsoleSender().sendMessage(Language.HELP_COMMAND_SET_NUMBER);
            Bukkit.getConsoleSender().sendMessage(Language.HELP_COMMAND_MODEL_NUM);
            Bukkit.getConsoleSender().sendMessage(Language.HELP_COMMAND_MODEL_PET);
            Bukkit.getConsoleSender().sendMessage(Language.HELP_COMMAND_MODEL_SET_PET);
            Bukkit.getConsoleSender().sendMessage(Language.HELP_COMMAND_RELOAD);
            Bukkit.getConsoleSender().sendMessage(Language.HELP_COMMAND_HELP);
        } else {
            p.sendMessage(Language.HELP_HEAD);
            p.sendMessage(Language.HELP_COMMAND_ON);
            p.sendMessage(Language.HELP_COMMAND_OFF);
            p.sendMessage(Language.HELP_COMMAND_SET_NUMBER);
            p.sendMessage(Language.HELP_COMMAND_MODEL_NUM);
            p.sendMessage(Language.HELP_COMMAND_MODEL_PET);
            p.sendMessage(Language.HELP_COMMAND_MODEL_SET_PET);
            p.sendMessage(Language.HELP_COMMAND_RELOAD);
            p.sendMessage(Language.HELP_COMMAND_HELP);
        }
    }
}
