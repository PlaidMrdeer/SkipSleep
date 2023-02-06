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

import java.text.NumberFormat;
import java.util.Objects;

public class SleepEvent implements Listener {
    int num = 0;
    public static int num1 = 0;
    long time;
    Player p;
    boolean isFlag;
    boolean isSkip;
    int petNum;
    public static double resultInt;
    Sound sound;
    NumberFormat numberFormat;
    String result;
    @EventHandler
    public void onSleep(PlayerBedEnterEvent e) {
        sound = Sound.BLOCK_NOTE_BLOCK_BASS;
        num = SkipSleep.instance().getConfig().getInt("skipNum");
        p = e.getPlayer();
        if (Objects.requireNonNull(SkipSleep.instance().getConfig().getString("model")).equalsIgnoreCase("num")) {
            if (num1 == num) {
                e.setCancelled(true);
                SkipSleep.sendMessage(p, "SKIP_NIGHT_NUMBER_FULL");
                return;
            }
        } else if (Objects.requireNonNull(SkipSleep.instance().getConfig().getString("model")).equalsIgnoreCase("pet")) {
            if (resultInt >= SkipSleep.instance().getConfig().getInt("skipPet")) {
                e.setCancelled(true);
                SkipSleep.sendMessage(p, "SKIP_NIGHT_NUMBER_FULL");
                return;
            }
        }
        isSkip = false;
        if (num1 > num || num1 < 0) {
            num1 = 0;
        }
        isFlag = SkipSleep.instance().getConfig().getBoolean("skip");
        if (isFlag) {
            if (Objects.requireNonNull(SkipSleep.instance().getConfig().getString("model")).equalsIgnoreCase("pet")) {
                petNum = Bukkit.getOnlinePlayers().size();
                if (time >= 12000 || time == 0 || p.getWorld().hasStorm()) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (!e.isCancelled()) {
                                num1++;
                                numberFormat = NumberFormat.getInstance();
                                numberFormat.setMaximumFractionDigits(2);
                                result = numberFormat.format((float) num1 / (float) petNum * 100);
                                resultInt = Double.parseDouble(result);
                                if (resultInt <= SkipSleep.instance().getConfig().getInt("skipPet")) {
                                    SkipSleep.broadcastMessage("SKIP_PET_TOTAL_PET");
                                }
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.playSound(p.getLocation(), sound, 3.0F, 3.0F);
                                }
                                if (resultInt >= SkipSleep.instance().getConfig().getInt("skipPet")) {
                                    isSkip = true;
                                    SkipSleep.broadcastMessage("SKIP_NIGHT");
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            if (resultInt < SkipSleep.instance().getConfig().getInt("skipPet")) {
                                                SkipSleep.broadcastMessage("SKIP_FAILED");
                                            } else {
                                                num1 = 0;
                                                p.getWorld().setTime(0);
                                                p.getWorld().setStorm(false);
                                                p.getWorld().setClearWeatherDuration(0);
                                            }
                                            cancel();
                                        }
                                    }.runTaskTimer(SkipSleep.instance(), 100L, 0L);
                                }
                            }
                            cancel();
                        }
                    }.runTaskTimer(SkipSleep.instance(), 1L, 0L);
                }
            } else if (Objects.requireNonNull(SkipSleep.instance().getConfig().getString("model")).equalsIgnoreCase("num")) {
                time = p.getWorld().getTime();
                if (time >= 12000 || time == 0 || p.getWorld().hasStorm()) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (!e.isCancelled()) {
                                num1++;
                                if (num1 != num) {
                                    SkipSleep.broadcastMessage("SKIP_NUMBER_TOTAL_NUMBER");
                                }
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.playSound(p.getLocation(), sound, 3.0F, 3.0F);
                                }
                                if (num1 == num) {
                                    isSkip = true;
                                    SkipSleep.broadcastMessage("SKIP_NIGHT");
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            if (num1 < num) {
                                                SkipSleep.broadcastMessage("SKIP_FAILED");
                                            } else {
                                                num1 = 0;
                                                p.getWorld().setTime(0);
                                                p.getWorld().setStorm(false);
                                                p.getWorld().setClearWeatherDuration(0);
                                            }
                                            cancel();
                                        }
                                    }.runTaskTimer(SkipSleep.instance(), 100L, 0L);
                                }
                            }
                            cancel();
                        }
                    }.runTaskTimer(SkipSleep.instance(), 1L, 0L);
                }
            }
        }
    }
    @EventHandler
    public void onLeaveSleep(PlayerBedLeaveEvent e) {
        if (isFlag) {
            if (Objects.requireNonNull(SkipSleep.instance().getConfig().getString("model")).equalsIgnoreCase("pet")) {
                if (time >= 12000 || time == 0 || !p.getWorld().isClearWeather()) {
                    num1--;
                    numberFormat = NumberFormat.getInstance();
                    numberFormat.setMaximumFractionDigits(2);
                    result = numberFormat.format((float) num1 / (float) petNum * 100);
                    resultInt = Double.parseDouble(result);
                    if (!isSkip) {
                        SkipSleep.broadcastMessage("SKIP_PET_TOTAL_PET");
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.playSound(p.getLocation(), sound, 3.0F, 3.0F);
                        }
                    }
                }
            } else if (Objects.requireNonNull(SkipSleep.instance().getConfig().getString("model")).equalsIgnoreCase("num")) {
                if (time >= 12000 || time == 0 || !p.getWorld().isClearWeather()) {
                    num1--;
                    if (!isSkip) {
                        SkipSleep.broadcastMessage("SKIP_NUMBER_TOTAL_NUMBER");
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.playSound(p.getLocation(), sound, 3.0F, 3.0F);
                        }
                    }
                }
            }
        }
    }
}
