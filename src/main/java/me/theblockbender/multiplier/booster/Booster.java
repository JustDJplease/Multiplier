package me.theblockbender.multiplier.booster;

public class Booster {
    private String playerName;
    private BoosterType type;
    private Integer multiplier;
    private Integer duration;
    private Long timeStarted;

    /**
     * Constructor for a new booster object.
     *
     * @param playerName Name of the player who is activating the booster.
     * @param type       Type of experience multiplied by the booster.
     * @param multiplier Multiplier strength of the booster.
     * @param duration   Length of the booster in seconds.
     */
    public Booster(String playerName, BoosterType type, Integer multiplier, Integer duration) {
        this.playerName = playerName;
        this.type = type;
        this.multiplier = multiplier;
        this.duration = duration;
        timeStarted = System.currentTimeMillis();
    }

    /**
     * Get the name of the player that activated the booster.
     *
     * @return Name of an (possibly offline) player.
     */
    public String getDisplayName() {
        return playerName;
    }

    /**
     * Get the type of the booster.
     *
     * @return BoosterType of the booster.
     */
    public BoosterType getType() {
        return type;
    }

    /**
     * Get the strength of the booster.
     *
     * @return Multiplier strength of the booster.
     */
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * Get if this booster still has some time left or if it should expire.
     *
     * @return Whether or not the booster has time left.
     */
    public boolean hasTimeLeft() {
        if (duration == -1) return true;
        Long timeEnd = timeStarted + duration;
        return System.currentTimeMillis() <= timeEnd;
    }

    /**
     * Get how much time is left of this booster.
     *
     * @return How much time is left of the booster.
     */
    public long getTimeLeft() {
        Long timeEnd = timeStarted + duration;
        return timeEnd - System.currentTimeMillis();
    }
}
