package me.theblockbender.multiplier.util;

import me.theblockbender.multiplier.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class UtilLanguage {

    private Main main;

    public UtilLanguage(Main main) {
        this.main = main;
    }

    /**
     * Translates the style as configured with the four primary colours of the palette.
     * Also applies the prefix.
     *
     * @param input String to be translated.
     * @return Coloured string.
     */
    private String translateColour(String input) {
        input.replace("{1}", getMessage("color.1"));
        input.replace("{2}", getMessage("color.2"));
        input.replace("{3}", getMessage("color.3"));
        input.replace("{4}", getMessage("color.4"));
        input.replace("{prefix}", getMessage("messages.prefix"));
        return applyBukkitColour(input);
    }

    /**
     * Translates colour codes.
     *
     * @param input String to be translated.
     * @return Coloured string.
     */
    private String applyBukkitColour(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    /**
     * Returns a message from the configuration file.
     *
     * @param path Path to the message.
     * @return Message found at path.
     */
    private String getMessage(String path) {
        // TODO
        return "placeholder";
    }


    public void sendMessage(Player player, String string) {
        player.sendMessage(translateColour(string));
    }

    public void sendMessage(List<Player> players, String string) {
        for (Player player : players) {
            sendMessage(player, string);
        }
    }

    public void broadcastMessage(String string) {
        Bukkit.broadcastMessage(translateColour(string));
    }

    public String getFormattedMessage(String string) {
        return translateColour(string);
    }
}
