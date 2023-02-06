package com.skipsleep.command;

import com.skipsleep.SkipSleep;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SkipSleepReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {

        if (!(sender.hasPermission("SkipSleep.command.skipsleepreload"))) {
            SkipSleep.sendMessage(sender, "COMMAND_NO_PERMISSION");
            return true;
        }

        SkipSleep.reloadPlugin();

        SkipSleep.sendMessage(sender, "COMMAND_RELOAD");

        return true;
    }
}
