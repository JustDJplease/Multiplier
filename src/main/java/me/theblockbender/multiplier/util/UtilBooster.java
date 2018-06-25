package me.theblockbender.multiplier.util;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.booster.Booster;
import me.theblockbender.multiplier.booster.BoosterType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UtilBooster {
    /**
     * Get a word to describe who is currently boosting experience.
     *
     * @param boosters A list of boosters that is active for a given BoosterType.
     * @return A word to describe who is currently boosting experience.
     */
    public static String getDisplayName(List<Booster> boosters) {
        if (boosters.size() == 1) return boosters.get(0).getDisplayName();
        return Main.getInstance().getLanguage().getFormattedMessage("multiple-people");
    }

    /**
     * Get the total multiplier amount that is currently active for a given BoosterType.
     *
     * @param boosters A list of boosters that is active for a given BoosterType.
     * @return The total multiplier amount that is currently active for a given BoosterType.
     */
    public static Integer getAddedMultipliersTogether(List<Booster> boosters) {
        int total = 0;
        for (Booster booster : boosters) {
            total += booster.getMultiplier();
        }
        return total;
    }

    /**
     * Get the shortest lasting boosters of the boosters that are currently active for a given BoosterType.
     *
     * @param boosters A list of boosters that is active for a given BoosterType.
     * @return The shortest lasting booster-duration of the boosters that are currently active for a given BoosterType.
     */
    public static Long getShortestBooster(List<Booster> boosters) {
        long smallest = 86400L;
        for (Booster booster : boosters) {
            long left = booster.getTimeLeft();
            if (left < smallest) {
                smallest = left;
            }
        }
        return smallest;
    }

    /**
     * Get a sorted map where boosters are sorted by their BoosterType.
     *
     * @param boosters All active boosters.
     * @return A sorted map where boosters are sorted by their BoosterType.
     */
    public static HashMap<BoosterType, List<Booster>> sortBoostersByType(List<Booster> boosters) {
        HashMap<BoosterType, List<Booster>> map = new HashMap<>();
        for (Booster booster : boosters) {
            BoosterType type = booster.getType();
            if (!map.containsKey(type)) {
                List<Booster> container = new ArrayList<>();
                container.add(booster);
                map.put(type, container);
            } else {
                List<Booster> container = map.get(type);
                container.add(booster);
                map.put(type, container);
            }
        }
        return map;
    }

    /**
     * Check is there is still a booster active of the given BoosterType. (Similar to isBoosterActive)
     *
     * @param boosters All active boosters, unsorted.
     * @return Whether or not there is still a booster active of the given BoosterType.
     */
    public static boolean hasBoosterOfTypeLeft(List<Booster> boosters, BoosterType type) {
        for (Booster booster : boosters) {
            if (booster.getType() == type) return true;
        }
        return false;
    }

    /**
     * Get the current active multiplier for a BoosterType.
     *
     * @param type The BoosterType.
     * @return Current active multiplier for that BoosterType.
     */
    public static int getMultiplier(BoosterType type) {
        HashMap<BoosterType, List<Booster>> boosters = sortBoostersByType(Main.getInstance().boosters);
        return getAddedMultipliersTogether(boosters.get(type));
    }

    /**
     * Check is there is a booster active of the given BoosterType. (Similar to hasBoosterOfTypeLeft)
     *
     * @param type The BoosterType.
     * @return Whether or not there is a booster active of the given BoosterType.
     */
    public static boolean isBoosterActive(BoosterType type) {
        HashMap<BoosterType, List<Booster>> boosters = sortBoostersByType(Main.getInstance().boosters);
        return boosters.containsKey(type);
    }

    public static void startBooster(String owner, BoosterType boosterType, int multiplier, int duration) {
        if(owner == null) owner = Main.getInstance().getLanguage().getFormattedMessage("messages.the-server");
        Booster booster = new Booster(owner, boosterType, multiplier, duration);
        Main.getInstance().boosters.add(booster);
        // TODO broadcast
    }
}
