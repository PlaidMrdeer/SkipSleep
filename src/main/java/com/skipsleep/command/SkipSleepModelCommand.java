package com.skipsleep.command;

import com.skipsleep.SkipSleep;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SkipSleepModelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {

        if (!(sender.hasPermission("SkipSleep.command.skipsleepmodel"))) {
            SkipSleep.sendMessage(sender, "COMMAND_NO_PERMISSION");
            return true;
        }

        if (args.length != 1) {
            SkipSleep.sendMessage(sender, "COMMAND_ERROR");
            return true;
        }

        switch (args[0]) {
            case "num":
                SkipSleep.config.set("model", "num");
                break;
            case "pet":
                SkipSleep.config.set("model", "pet");
                break;
            default:
                SkipSleep.sendMessage(sender, "COMMAND_SET_FAIL");
                return true;
        }
        SkipSleep.instance().saveConfig();

        SkipSleep.sendMessage(sender, "COMMAND_SET_SUCCESSFULLY");
        return true;
    }
}
