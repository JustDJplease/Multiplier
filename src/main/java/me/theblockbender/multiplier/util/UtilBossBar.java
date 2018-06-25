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

    /**
     * Method to show a BossBar to online players.
     *
     * @param type         The BoosterType.
     * @param shortestTime The duration left of the shortest booster.
     * @param multiplier   The current multiplier active.
     * @param displayName  The string to be displayed on the {player} placeholder
     */
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

    /**
     * Method to hide all active BossBars of a given BoosterType.
     *
     * @param type The given BoosterType.
     */
    public static void hideBossBar(BoosterType type) {
        if (bars.containsKey(type)) {
            BossBar bar = bars.get(type);
            bar.removeAll();
            bars.remove(type);
        }
    }

    /**
     * Method to hide all booster BossBars.
     */
    public static void hideAllBossBars() {
        for (Map.Entry<BoosterType, BossBar> entry : bars.entrySet()) {
            BossBar bar = entry.getValue();
            bar.removeAll();
        }
        bars.clear();
    }

    /**
     * Creates a string to be used as the title for a booster BossBar.
     *
     * @param type         The BoosterType.
     * @param shortestTime The duration left of the shortest booster.
     * @param multiplier   The current multiplier active.
     * @param displayName  The string to be displayed on the {player} placeholder
     * @return Valid title for the BossBar.
     */
    private static String formatTitle(BoosterType type, Long shortestTime, Integer multiplier, String displayName) {
        String title = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("BossBar." + type.name() + ".title"));
        title.replace("{multiplier}", Main.getInstance().getLanguage().getMultiplierWord(multiplier));
        title.replace("{player}", displayName);
        title.replace("{time}", Main.getInstance().getLanguage().getFormattedTime(shortestTime));
        return title;
    }

    /**
     * Creates a new BossBar.
     *
     * @param type         The BoosterType.
     * @param shortestTime The duration left of the shortest booster.
     * @param multiplier   The current multiplier active.
     * @param displayName  The string to be displayed on the {player} placeholder
     * @return A new BossBar instance.
     */
    private static BossBar createNewBossBar(BoosterType type, Long shortestTime, Integer multiplier, String displayName) {
        return Bukkit.createBossBar(formatTitle(type, shortestTime, multiplier, displayName), getBarColor(type), BarStyle.SOLID);
    }

    /**
     * Gets the corresponding BarColor from the config.
     *
     * @param type The BoosterType.
     */
    private static BarColor getBarColor(BoosterType type) {
        try {
            return BarColor.valueOf(Main.getInstance().getConfig().getString("BossBar." + type.name() + ".color"));
        } catch (IllegalArgumentException ex) {
            return BarColor.RED;
        }
    }
}
