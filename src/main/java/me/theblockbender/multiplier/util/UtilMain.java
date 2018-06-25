package me.theblockbender.multiplier.util;

import me.theblockbender.multiplier.booster.BoosterType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UtilMain {

    public static Boolean isArgumentOnlinePlayer(String argument) {
        Player player = Bukkit.getPlayer(argument);
        return (player != null);
    }

    private static Boolean isArgumentNumber(String argument) {
        try {
            int i = Integer.parseInt(argument);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static Boolean isArgumentNumberInRange(String argument, int min, int max) {
        if (!isArgumentNumber(argument)) return false;
        int i = Integer.parseInt(argument);
        return i >= min && i <= max;
    }

    public static Boolean isArgumentValidType(String argument) {
        try {
            BoosterType.valueOf(argument.toLowerCase());
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
}
