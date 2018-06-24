package me.theblockbender.multiplier.data;

import me.theblockbender.multiplier.Main;

import java.util.logging.Level;

public class Error {
    public static void execute(Main plugin, Exception ex) {
        plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
    }

    static void close(Main plugin, Exception ex) {
        plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}