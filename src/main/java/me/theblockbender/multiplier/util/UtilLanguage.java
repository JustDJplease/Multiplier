package me.theblockbender.multiplier.util;

import me.theblockbender.multiplier.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
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
        if (!main.messages.contains(path)) {
            return "Message not found: " + path;
        }
        return main.messages.getString("path");
    }


    /**
     * Sends a message to the player
     *
     * @param player Player to receive the message
     * @param string Path to the message in the configuration file.
     */
    public void sendMessage(Player player, String string) {
        player.sendMessage(translateColour(string));
    }

    /**
     * Sends a message to the players
     *
     * @param players Players to receive the message
     * @param string  Path to the message in the configuration file.
     */
    public void sendMessage(List<Player> players, String string) {
        for (Player player : players) {
            sendMessage(player, string);
        }
    }

    /**
     * Sends a message to all online players
     *
     * @param string Path to the message in the configuration file.
     */
    public void broadcastMessage(String string) {
        Bukkit.broadcastMessage(translateColour(string));
    }

    /**
     * Fetches and formats a message from the language file.
     *
     * @param string Path to the message in the configuration file.
     * @return formatted Message.
     */
    public String getFormattedMessage(String string) {
        return translateColour(string);
    }

    /**
     * Sends a message to the CommandSender
     *
     * @param commandSender CommandSender (Console/Player) to receive the message
     * @param string        Path to the message in the configuration file.
     */
    public void sendMessage(CommandSender commandSender, String string) {
        commandSender.sendMessage(translateColour(string));
    }
}
