package me.theblockbender.multiplier.gui;

import me.theblockbender.multiplier.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ScrollerInventory {

    public static final String nextPageName = Main.getInstance().getLanguage().getFormattedMessage("next-page");
    public static final String previousPageName = Main.getInstance().getLanguage().getFormattedMessage("previous-page");
    public static HashMap<UUID, ScrollerInventory> users = new HashMap<>();
    public ArrayList<Inventory> pages = new ArrayList<>();
    public int currpage = 0;
    /**
     * This creates a GUI for the player specified, with the items from the list.
     *
     * @param items  List of Items that should appear in the gui.
     * @param name   Name of the inventory.
     * @param player Player this inventory should open for.
     */
    public ScrollerInventory(ArrayList<ItemStack> items, String name, Player player) {
        Inventory page = getBlankPage(name);
        for (ItemStack item : items) {
            if (page.firstEmpty() == 46) {
                pages.add(page);
                page = getBlankPage(name);
                page.addItem(item);
            } else {
                page.addItem(item);
            }
        }
        pages.add(page);
        player.openInventory(pages.get(currpage));
        users.put(player.getUniqueId(), this);
    }

    /**
     * Creates a blank inventory menu with the page icons.
     *
     * @param name Name of the inventory.
     * @return Returns a blank inventory.
     */
    private Inventory getBlankPage(String name) {
        Inventory page = Bukkit.createInventory(null, 54, name);
        ItemStack nextPage = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
        ItemMeta meta = nextPage.getItemMeta();
        meta.setDisplayName(nextPageName);
        nextPage.setItemMeta(meta);
        ItemStack prevPage = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 2);
        meta = prevPage.getItemMeta();
        meta.setDisplayName(previousPageName);
        prevPage.setItemMeta(meta);
        page.setItem(53, nextPage);
        page.setItem(45, prevPage);
        return page;
    }
}