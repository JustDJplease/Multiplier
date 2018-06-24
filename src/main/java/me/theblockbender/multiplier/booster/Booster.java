package me.theblockbender.multiplier.booster;

public class Booster {
    private String playerName;
    private BoosterType type;
    private Integer multiplier;
    private Integer duration;
    private Long timeStarted;

    public Booster(String playerName, BoosterType type, Integer multiplier, Integer duration) {
        this.playerName = playerName;
        this.type = type;
        this.multiplier = multiplier;
        this.duration = duration;
        timeStarted = System.currentTimeMillis();
    }

    public String getDisplayName() {
        return playerName;
    }

    public BoosterType getType() {
        return type;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public boolean hasTimeLeft() {
        if (duration == -1) return true;
        Long timeEnd = timeStarted + duration;
        return System.currentTimeMillis() <= timeEnd;
    }

    public long getTimeLeft() {
        Long timeEnd = timeStarted + duration;
        return timeEnd - System.currentTimeMillis();
    }
}
