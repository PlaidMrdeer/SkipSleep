package com.skipsleep.Listener;

import com.skipsleep.SkipSleep;
import com.skipsleep.language.Language;
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
        num = SkipSleep.getPlugin().getConfig().getInt("skipNum");
        p = e.getPlayer();
        if (Objects.requireNonNull(SkipSleep.getPlugin().getConfig().getString("model")).equalsIgnoreCase("num")) {
            if (num1 == num) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Language.SKIP_NIGHT_NUMBER_FULL);
                return;
            }
        } else if (Objects.requireNonNull(SkipSleep.getPlugin().getConfig().getString("model")).equalsIgnoreCase("pet")) {
            if (resultInt >= SkipSleep.getPlugin().getConfig().getInt("skipPet")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Language.SKIP_NIGHT_NUMBER_FULL);
                return;
            }
        }
        isSkip = false;
        if (num1 > num || num1 < 0) {
            num1 = 0;
        }
        isFlag = SkipSleep.getPlugin().getConfig().getBoolean("skip");
        if (isFlag) {
            if (Objects.requireNonNull(SkipSleep.getPlugin().getConfig().getString("model")).equalsIgnoreCase("pet")) {
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
                                Language.getLanguage();
                                if (resultInt <= SkipSleep.getPlugin().getConfig().getInt("skipPet")) {
                                    Language.getLanguage();
                                    Bukkit.broadcastMessage(Language.SKIP_PET_TOTAL_PET);
                                }
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.playSound(p.getLocation(), sound, 3.0F, 3.0F);
                                }
                                if (resultInt >= SkipSleep.getPlugin().getConfig().getInt("skipPet")) {
                                    isSkip = true;
                                    Bukkit.broadcastMessage(Language.SKIP_NIGHT);
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            if (resultInt < SkipSleep.getPlugin().getConfig().getInt("skipPet")) {
                                                Bukkit.broadcastMessage(Language.SKIP_FAILED); //跳过失败
                                            } else {
                                                num1 = 0;
                                                p.getWorld().setTime(0);
                                                p.getWorld().setStorm(false);
                                                p.getWorld().setClearWeatherDuration(0);
                                            }
                                            cancel();
                                        }
                                    }.runTaskTimer(SkipSleep.getPlugin(), 100L, 0L);
                                }
                            }
                            cancel();
                        }
                    }.runTaskTimer(SkipSleep.getPlugin(), 1L, 0L);
                }
            } else if (Objects.requireNonNull(SkipSleep.getPlugin().getConfig().getString("model")).equalsIgnoreCase("num")) {
                time = p.getWorld().getTime();
                if (time >= 12000 || time == 0 || p.getWorld().hasStorm()) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (!e.isCancelled()) {
                                num1++;
                                if (num1 != num) {
                                    Language.getLanguage();
                                    Bukkit.broadcastMessage(Language.SKIP_NUMBER_TOTAL_NUMBER);
                                }
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.playSound(p.getLocation(), sound, 3.0F, 3.0F);
                                }
                                if (num1 == num) {
                                    isSkip = true;
                                    Bukkit.broadcastMessage(Language.SKIP_NIGHT);
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            if (num1 < num) {
                                                Bukkit.broadcastMessage(Language.SKIP_FAILED);
                                            } else {
                                                num1 = 0;
                                                p.getWorld().setTime(0);
                                                p.getWorld().setStorm(false);
                                                p.getWorld().setClearWeatherDuration(0);
                                            }
                                            cancel();
                                        }
                                    }.runTaskTimer(SkipSleep.getPlugin(), 100L, 0L);
                                }
                            }
                            cancel();
                        }
                    }.runTaskTimer(SkipSleep.getPlugin(), 1L, 0L);
                }
            }
        }
    }
    @EventHandler
    public void onLeaveSleep(PlayerBedLeaveEvent e) {
        if (isFlag) {
            if (Objects.requireNonNull(SkipSleep.getPlugin().getConfig().getString("model")).equalsIgnoreCase("pet")) {
                if (time >= 12000 || time == 0 || !p.getWorld().isClearWeather()) {
                    num1--;
                    numberFormat = NumberFormat.getInstance();
                    numberFormat.setMaximumFractionDigits(2);
                    result = numberFormat.format((float) num1 / (float) petNum * 100);
                    resultInt = Double.parseDouble(result);
                    if (!isSkip) {
                        Language.getLanguage();
                        Bukkit.broadcastMessage(Language.SKIP_PET_TOTAL_PET);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.playSound(p.getLocation(), sound, 3.0F, 3.0F);
                        }
                    }
                }
            } else if (Objects.requireNonNull(SkipSleep.getPlugin().getConfig().getString("model")).equalsIgnoreCase("num")) {
                if (time >= 12000 || time == 0 || !p.getWorld().isClearWeather()) {
                    num1--;
                    if (!isSkip) {
                        Language.getLanguage();
                        Bukkit.broadcastMessage(Language.SKIP_NUMBER_TOTAL_NUMBER);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.playSound(p.getLocation(), sound, 3.0F, 3.0F);
                        }
                    }
                }
            }
        }
    }
}
