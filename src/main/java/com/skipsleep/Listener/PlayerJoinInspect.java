package com.skipsleep.Listener;

import com.skipsleep.SkipSleep;
import com.skipsleep.language.Language;
import com.skipsleep.update.UpdateCheck;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinInspect implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        int num = Bukkit.getOnlinePlayers().size();
        if (num == 1) {
            SkipSleep.getPlugin().getConfig().set("skip", false);
            SkipSleep.getPlugin().saveConfig();
        } else {
            SkipSleep.getPlugin().getConfig().set("skip", true);
            SkipSleep.getPlugin().saveConfig();
        }

        if (UpdateCheck.isUpDate()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!UpdateCheck.isLatestVersion() && e.getPlayer().isOp()) {
                        Language.getLanguage();
                        e.getPlayer().sendMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "SkipSleep" + ChatColor.YELLOW + "]" + Language.DETECTED_NEW_VERSION);
                        e.getPlayer().sendMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "SkipSleep" + ChatColor.YELLOW + "]" + Language.NEW_VERSION_WEBSITE);
                    }
                    cancel();
                }
            }.runTaskAsynchronously(SkipSleep.getPlugin());
        }
    }

}
