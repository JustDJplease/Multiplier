package me.theblockbender.multiplier.util;

import me.theblockbender.multiplier.booster.BoosterType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UtilMain {

    /**
     * Command argument validator: Tests if the argument is an online player.
     *
     * @param argument Argument.
     * @return If the argument is an online player.
     */
    public static Boolean isArgumentOnlinePlayer(String argument) {
        Player player = Bukkit.getPlayer(argument);
        return (player != null);
    }

    /**
     * Command argument validator: Tests if the argument is a number.
     *
     * @param argument Argument.
     * @return If the argument is a number.
     */
    private static Boolean isArgumentNumber(String argument) {
        try {
            int i = Integer.parseInt(argument);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * Command argument validator: Tests if the argument is a number in the given range.
     *
     * @param argument Argument.
     * @return If the argument is a number in the given range.
     */
    public static Boolean isArgumentNumberInRange(String argument, int min, int max) {
        if (!isArgumentNumber(argument)) return false;
        int i = Integer.parseInt(argument);
        return i >= min && i <= max;
    }

    /**
     * Command argument validator: Tests if the argument is a BoosterType.
     *
     * @param argument Argument.
     * @return If the argument is a BoosterType.
     */
    public static Boolean isArgumentValidType(String argument) {
        try {
            BoosterType.valueOf(argument.toLowerCase());
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
}
