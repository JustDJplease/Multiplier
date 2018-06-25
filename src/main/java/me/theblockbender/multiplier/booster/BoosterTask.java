package me.theblockbender.multiplier.booster;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.util.UtilBooster;
import me.theblockbender.multiplier.util.UtilBossBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BoosterTask implements Runnable {

    private Main main;

    public BoosterTask(Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        List<Booster> endingBoosters = new ArrayList<>();
        for (Booster booster : main.boosters) {
            if (!booster.hasTimeLeft()) {
                endingBoosters.add(booster);
            }
        }
        for (Booster booster : endingBoosters) {
            main.boosters.remove(booster);
            if (!UtilBooster.hasBoosterOfTypeLeft(main.boosters, booster.getType())) {
                UtilBossBar.hideBossBar(booster.getType());
            }
        }

        HashMap<BoosterType, List<Booster>> sortedBoosters = UtilBooster.sortBoostersByType(main.boosters);
        for (BoosterType type : BoosterType.values()) {
            List<Booster> boosters = sortedBoosters.get(type);
            Long shortestTime = UtilBooster.getShortestBooster(boosters);
            Integer multiplier = UtilBooster.getAddedMultipliersTogether(boosters);
            String displayName = UtilBooster.getDisplayName(boosters);
            UtilBossBar.showBossBar(type, shortestTime, multiplier, displayName);
        }
    }
}
