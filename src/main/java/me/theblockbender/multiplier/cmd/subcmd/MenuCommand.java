package me.theblockbender.multiplier.cmd.subcmd;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.gui.ScrollerInventory;
import me.theblockbender.multiplier.util.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class MenuCommand {
    private Main main;

    public MenuCommand(Main main) {
        this.main = main;
    }

    /**
     * Method that is run upon executing /multiplier (with no other args)
     *
     * @param player Executor of the command. Cannot be the console.
     */
    public void run(Player player) {
        // Validator:
        if (!player.hasPermission("multiplier.menu")) {
            main.getLanguage().sendMessage(player, "no-permission");
            return;
        }
        // Executor:
        openGui(player);
    }

    /**
     * Opens the booster menu for the given Player. Will perform an async lookup of the booster-data.
     * Will check if the given player is still online when trying to open the menu after the data is loaded.
     *
     * @param player Player that requests to view their boosters.
     */
    private void openGui(Player player) {
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
