package me.theblockbender.multiplier.gui;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.util.UtilItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class PrepareGui {

    private Main main;

    public PrepareGui(Main main) {
        this.main = main;
    }

    public void openGui(Player player) {
        main.getLanguage().sendMessage(player, "loading-boosters");
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskAsynchronously(main, new Runnable() {
            @Override
            public void run() {
                final HashMap<Integer, HashMap<String, Object>> results = main.getDatabase().getBoostersForPlayer(player.getUniqueId());
                Bukkit.getScheduler().runTask(main, new Runnable() {
                    @Override
                    public void run() {
                        new ScrollerInventory(UtilItem.createItemsFromResultSet(results), main.getLanguage().getFormattedMessage("gui-title"), player);
                    }
                });
            }
        });
    }

}
