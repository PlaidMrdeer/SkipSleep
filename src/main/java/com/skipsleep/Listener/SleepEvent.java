package com.skipsleep.Listener;

import com.skipsleep.SkipSleep;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepEvent implements Listener {
    Player p;
    @EventHandler
    public void onSleep(PlayerBedEnterEvent e) {
        boolean isFlag = SkipSleep.getPlugin().getConfig().getBoolean("skip");
        if (isFlag) {
            p = e.getPlayer();
            for (Player p1 : Bukkit.getOnlinePlayers()) {
                p1.setSleepingIgnored(true);
            }
            p.setSleepingIgnored(false);
        }
    }
}
