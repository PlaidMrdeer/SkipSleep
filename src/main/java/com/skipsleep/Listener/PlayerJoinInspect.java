package com.skipsleep.Listener;

import com.skipsleep.SkipSleep;
import com.skipsleep.update.UpdateCheck;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinInspect implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        int num = Bukkit.getOnlinePlayers().size();
        if (num == 1) {
            SkipSleep.instance().getConfig().set("skip", false);
        } else {
            SkipSleep.instance().getConfig().set("skip", true);
        }
        SkipSleep.instance().saveConfig();

        if (UpdateCheck.isUpDate()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!UpdateCheck.isLatestVersion() && e.getPlayer().isOp()) {
                        SkipSleep.sendMessage(e.getPlayer(), "DETECTED_NEW_VERSION");
                        SkipSleep.sendMessage(e.getPlayer(), "NEW_VERSION_WEBSITE");
                    }
                    cancel();
                }
            }.runTaskAsynchronously(SkipSleep.instance());
        }
    }

}
