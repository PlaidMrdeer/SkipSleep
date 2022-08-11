package com.skipsleep.Listener;

import com.skipsleep.SkipSleep;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SleepEvent implements Listener {
    int num = 0;
    int num1 = 0;
    long time;
    Player p;
    boolean isFlag;
    boolean isSkip;
    @EventHandler
    public void onSleep(PlayerBedEnterEvent e) {
        num = SkipSleep.getPlugin().getConfig().getInt("skipNum");
        p = e.getPlayer();
        if (num1 == num) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§l>> §b睡觉人数已经满了哦, 请等待跳过天亮");
            return;
        }
        isSkip = false;
        if (num1 > num || num1 < 0) {
            num1 = 0;
        }
        isFlag = SkipSleep.getPlugin().getConfig().getBoolean("skip");
        if (isFlag) {
            time = p.getWorld().getTime();
            if (time >= 12000 || time == 0 || p.getWorld().hasStorm()) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                if (!e.isCancelled()) {
                    num1++;
                    if (num1 != num) {
                        Bukkit.broadcastMessage("§l>> §a已经有 §l§e" + num1 + "/" + num + "人 §r§a躺在了床上");
                    }
                    Sound sound = Sound.BLOCK_NOTE_BLOCK_BASS;
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.playSound(p.getLocation(), sound, 3.0F, 3.0F);
                    }
                    if (num1 == num) {
                        isSkip = true;
                        Bukkit.broadcastMessage("§l>> §a即将跳过夜晚...");
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (num1 < num) {
                                    Bukkit.broadcastMessage("§l>> §a人数不足哦!");
                                } else {
                                    num1 = 0;
                                    p.getWorld().setTime(0);
                                    p.getWorld().setStorm(false);
                                    p.getWorld().setClearWeatherDuration(1);
                                }
                                cancel();
                            }
                        }.runTaskTimer(SkipSleep.getPlugin(), 100L, 0L);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onLeaveSleep(PlayerBedLeaveEvent e) {
        if (isFlag) {
            if (time >= 12000 || time == 0 || !p.getWorld().isClearWeather()) {
                num1--;
                if (!isSkip) {
                    Bukkit.broadcastMessage("§l>> §a已经有 §l§e" + num1 + "/" + num + "人 §r§a躺在了床上");
                    Sound sound = Sound.BLOCK_NOTE_BLOCK_BASS;
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.playSound(p.getLocation(), sound, 3.0F, 3.0F);
                    }
                }
            }
        }
    }
}
