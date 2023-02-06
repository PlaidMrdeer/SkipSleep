package com.skipsleep.command;

import com.skipsleep.SkipSleep;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;

public class SkipSleepHelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!(sender.hasPermission("SkipSleep.command.skipsleephelp"))) {
            SkipSleep.sendMessage(sender, "COMMAND_NO_PERMISSION");
            return true;
        }

        help(sender);

        return true;
    }

    private void help(CommandSender sender) {
        SkipSleep.sendMessage(sender, "HELP_HEAD");
        SkipSleep.sendMessage(sender, "HELP_COMMAND_ENABLE");
        SkipSleep.sendMessage(sender, "HELP_COMMAND_NUMBER");
        SkipSleep.sendMessage(sender, "HELP_COMMAND_MODEL");
        SkipSleep.sendMessage(sender, "HELP_COMMAND_PET");
        SkipSleep.sendMessage(sender, "HELP_COMMAND_RELOAD");
        SkipSleep.sendMessage(sender, "HELP_COMMAND_HELP");
    }
}
