package com.skipsleep.thead;

import com.skipsleep.SkipSleep;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class InspectNum {
    public static void num() {
        new BukkitRunnable() {
            @Override
            public void run() {
                int num = Bukkit.getOnlinePlayers().size();
                if (num == 1) {
                    SkipSleep.getPlugin().getConfig().set("skip", false);
                    SkipSleep.getPlugin().saveConfig();
                } else {
                    SkipSleep.getPlugin().getConfig().set("skip", true);
                    SkipSleep.getPlugin().saveConfig();
                }
            }
        }.runTaskTimer(SkipSleep.getPlugin(), 0L, 100L);
    }
}
