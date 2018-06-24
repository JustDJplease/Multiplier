package me.theblockbender.multiplier.util;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.booster.Booster;
import me.theblockbender.multiplier.booster.BoosterType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UtilBooster {
    public static String getDisplayName(List<Booster> boosters) {
        if (boosters.size() == 1) return boosters.get(0).getDisplayName();
        return Main.getInstance().getLanguage().getFormattedMessage("multiple-people");
    }

    public static Integer getAddedMultipliersTogether(List<Booster> boosters) {
        int total = 0;
        for (Booster booster : boosters) {
            total += booster.getMultiplier();
        }
        return total;
    }

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
}
