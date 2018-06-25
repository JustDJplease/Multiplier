package me.theblockbender.multiplier.util;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.booster.BoosterType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class UtilBossBar {

    private static HashMap<BoosterType, BossBar> bars = new HashMap<>();

    public static void showBossBar(BoosterType type, Long shortestTime, Integer multiplier, String displayName) {
        BossBar bossbar;
        if (bars.containsKey(type)) {
            bossbar = bars.get(type);
            bossbar.setTitle(formatTitle(type, shortestTime, multiplier, displayName));
        } else {
            bossbar = createNewBossBar(type, shortestTime, multiplier, displayName);
        }
        if (bossbar == null) return;
        for (Player player : Bukkit.getOnlinePlayers()) {
            bossbar.addPlayer(player);
        }
    }

    public static void hideBossBar(BoosterType type) {
        if (bars.containsKey(type)) {
            BossBar bar = bars.get(type);
            bar.removeAll();
            bars.remove(type);
        }
    }

    public static void hideAllBossBars() {
        for (Map.Entry<BoosterType, BossBar> entry : bars.entrySet()) {
            BossBar bar = entry.getValue();
            bar.removeAll();
        }
        bars.clear();
    }

    private static String formatTitle(BoosterType type, Long shortestTime, Integer multiplier, String displayName) {
        String title = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("BossBar." + type.name() + ".title"));
        title.replace("{multiplier}", Main.getInstance().getLanguage().getMultiplierWord(multiplier));
        title.replace("{player}", displayName);
        title.replace("{time}", Main.getInstance().getLanguage().getFormattedTime(shortestTime));
        return title;
    }

    private static BossBar createNewBossBar(BoosterType type, Long shortestTime, Integer multiplier, String displayName) {
        return Bukkit.createBossBar(formatTitle(type, shortestTime, multiplier, displayName), getBarColor(type), BarStyle.SOLID);
    }

    private static BarColor getBarColor(BoosterType type) {
        try {
            return BarColor.valueOf(Main.getInstance().getConfig().getString("BossBar." + type.name() + ".color"));
        } catch (IllegalArgumentException ex) {
            return BarColor.RED;
        }
    }
}
