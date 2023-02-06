package com.skipsleep.command;

import com.skipsleep.SkipSleep;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SkipSleepEnableCommand implements CommandExecutor {
    @Override
    public boolean onCommand (@NotNull CommandSender sender,
                              @NotNull Command command,
                              @NotNull String label,
                              @NotNull String[] args) {

        if (!(sender.hasPermission("SkipSleep.command.skipsleepenable"))) {
            SkipSleep.sendMessage(sender, "COMMAND_NO_PERMISSION");
            return true;
        }

        if (args.length != 1) {
            SkipSleep.sendMessage(sender, "COMMAND_ERROR");
            return true;
        }

        switch (args[0]) {
            case "on":
                SkipSleep.config.set("skip", true);
                break;
            case "off":
                SkipSleep.config.set("skip", false);
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
