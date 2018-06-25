package me.theblockbender.multiplier.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilItem {
    public static ArrayList<ItemStack> createItemsFromResultSet(HashMap<Integer, HashMap<String, Object>> data) {
        ArrayList<ItemStack> items = new ArrayList<>();
        for (Map.Entry<Integer, HashMap<String, Object>> data_id : data.entrySet()) {
            Integer id = data_id.getKey();
            HashMap<String, Object> results = data_id.getValue();
            for (Map.Entry<String, Object> item_data : results.entrySet()) {
                // TODO allow custom button.
                ItemStack item = new ItemStack(Material.EXP_BOTTLE, 1);
                item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName("§3§l" + results.get("type") + " §b§lExperience multiplier");
                List<String> lore = new ArrayList<>();
                lore.add("§7");
                lore.add("§7Upon activation, this booster will multiply");
                lore.add("§7the experience gained by all online players.");
                lore.add("§7");
                lore.add("§b» §fMultiplier: §7" + results.get("multiplier") + "x");
                lore.add("§b» §fDuration: §7" + results.get("duration"));
                lore.add("§b» §fExpiry date: §7" + results.get("time_expires"));
                lore.add("§7");
                lore.add("§8Click to activate this booster");
                itemMeta.setLore(lore);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(itemMeta);
                items.add(item);
                // TODO save booster ID in NBT data.
            }
        }
        return items;
    }
}
