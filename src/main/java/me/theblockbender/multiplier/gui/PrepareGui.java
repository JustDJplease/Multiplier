package me.theblockbender.multiplier.gui;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.util.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class PrepareGui {

    private Main main;

    public PrepareGui(Main main) {
        this.main = main;
    }

    /**
     * Opens the booster menu for the given Player. Will perform an async lookup of the booster-data.
     * Will check if the given player is still online when trying to open the menu after the data is loaded.
     *
     * @param player Player that requests to view their boosters.
     */
    public void openGui(Player player) {
        final UUID uuid = player.getUniqueId();
        main.getLanguage().sendMessage(player, "loading-boosters");
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskAsynchronously(main, new Runnable() {
            @Override
            public void run() {
                final HashMap<Integer, HashMap<String, Object>> results = main.getDatabase().getBoostersForPlayer(uuid);
                Bukkit.getScheduler().runTask(main, new Runnable() {
                    @Override
                    public void run() {
                        if (Bukkit.getPlayer(uuid) != null)
                            new ScrollerInventory(UtilItem.createItemsFromResultSet(results), main.getLanguage().getFormattedMessage("gui-title"), player);
                    }
                });
            }
        });
    }

}
