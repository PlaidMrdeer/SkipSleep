package com.skipsleep.Listener;

import com.skipsleep.SkipSleep;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class SleepEvent implements Listener {
    int num = 0;
    int num1 = 0;
    long time;
    Player p;
    @EventHandler
    public void onSleep(PlayerBedEnterEvent e) {
        if (num1 > num) {
            num1 = 0;
        }
        num1++;
        p = e.getPlayer();
        time = p.getWorld().getTime();
        boolean isFlag = SkipSleep.getPlugin().getConfig().getBoolean("skip");
        if (isFlag) {
            if (time >= 12000 || time == 0 || !p.getWorld().isClearWeather()) {
                num = SkipSleep.getPlugin().getConfig().getInt("skipNum");
                if (num1 == num) {
                    num1 = 0;
                    p.getWorld().setTime(0);
                }
                if (num1 != num) {
                    Bukkit.broadcastMessage(ChatColor.AQUA + "已有 " + ChatColor.YELLOW + num1 + "/" + num + ChatColor.GREEN + " 躺在了床上");
                }
            }
        }
    }
    @EventHandler
    public void onLeaveSleep(PlayerBedLeaveEvent e) {
        if (time >= 12000 || time == 0 || !p.getWorld().isClearWeather()) {
            num1--;
        }
    }
}
