package me.theblockbender.multiplier.listener;

import me.theblockbender.multiplier.Main;
import me.theblockbender.multiplier.gui.ScrollerInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiPageListener implements Listener {

    private Main main;

    public GuiPageListener(Main main) {
        this.main = main;
    }

    /**
     * Event to manage the ScrollerInventory next and previous page buttons.
     *
     * @param event The called event (InventoryClickEvent)
     */
    @EventHandler(ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        if (!ScrollerInventory.users.containsKey(player.getUniqueId())) return;
        ScrollerInventory inv = ScrollerInventory.users.get(player.getUniqueId());
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.nextPageName)) {
            event.setCancelled(true);
            if (inv.currpage >= inv.pages.size() - 1) {
                main.getLanguage().sendMessage(player, "no-next-page");
            } else {
                inv.currpage += 1;
                player.openInventory(inv.pages.get(inv.currpage));
            }
        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.previousPageName)) {
            event.setCancelled(true);
            if (inv.currpage > 0) {
                inv.currpage -= 1;
                player.openInventory(inv.pages.get(inv.currpage));
            } else {
                main.getLanguage().sendMessage(player, "no-previous-page");
            }
        }
    }
}
