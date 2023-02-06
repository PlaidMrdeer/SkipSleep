package com.skipsleep.command;

import com.skipsleep.SkipSleep;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SkipSleepPetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {

        if (!(sender.hasPermission("SkipSleep.command.skipsleeppet"))) {
            SkipSleep.sendMessage(sender, "COMMAND_NO_PERMISSION");
            return true;
        }

        if (args.length != 1) {
            SkipSleep.sendMessage(sender, "COMMAND_ERROR");
            return true;
        }

        try {
            int totalPet = Integer.parseInt(args[0]);
            if (totalPet >= 100) {
                return true;
            }

            SkipSleep.config.set("skipPet", totalPet);
            SkipSleep.instance().saveConfig();
        } catch (NumberFormatException e) {
            SkipSleep.sendMessage(sender, "COMMAND_SET_FAIL");
            return true;
        }

        SkipSleep.sendMessage(sender, "COMMAND_SET_SUCCESSFULLY");
        return true;
    }
}
