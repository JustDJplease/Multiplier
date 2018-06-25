package me.theblockbender.multiplier.util;

import me.theblockbender.multiplier.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

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
        if (!main.messages.contains(path)) return "Message not found: " + path;
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

    public String getMultiplierWord(Integer multiplier) {
        if (main.messages.contains("multiplier." + multiplier)) return translateColour("multiplier." + multiplier);
        else return translateColour("multiplier.other");
    }

    public String getFormattedTime(Long shortestTime) {
        return convertString(shortestTime);
    }

    private String convertString(long time) {
        if (time == -1)
            return translateColour("time.infinite");
        String type;
        int trim = 1;
        if (time < 60000)
            type = "SECONDS";
        else if (time < 3600000)
            type = "MINUTES";
        else if (time < 86400000)
            type = "HOURS";
        else
            type = "DAYS";
        String text;
        double num;
        switch (type) {
            case "DAYS":
                text = (num = trim(trim, time / 86400000d)) + " " + translateColour("time.day");
                break;
            case "HOURS":
                text = (num = trim(trim, time / 3600000d)) + " " + translateColour("time.hour");
                break;
            case "MINUTES":
                text = (num = trim(trim, time / 60000d)) + " " + translateColour("time.minute");
                break;
            case "SECONDS":
                text = (num = trim(trim, time / 1000d)) + " " + translateColour("time.second");
                break;
            default:
                text = (int) (num = (int) trim(0, time)) + " " + translateColour("time.millisecond");
                break;
        }
        if (num != 1)
            text += translateColour("time.not-one");
        return text;
    }

    private double trim(int degree, double d) {
        StringBuilder format = new StringBuilder("#.#");
        for (int i = 1; i < degree; i++)
            format.append("#");
        DecimalFormatSymbols symb = new DecimalFormatSymbols(Locale.US);
        DecimalFormat twoDForm = new DecimalFormat(format.toString(), symb);
        return Double.valueOf(twoDForm.format(d));
    }
}
