package me.theblockbender.multiplier.data;

import me.theblockbender.multiplier.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public abstract class Database {

    Main plugin;
    Connection connection;
    private String table = "multipliers";

    Database(Main instance) {
        plugin = instance;
    }

    public abstract Connection getSQLConnection();

    public abstract void load();

    void initialize() {
        connection = getSQLConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT type FROM " + table + " WHERE id = unset");
            ResultSet rs = ps.executeQuery();
            close(ps, rs);
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retrieve connection", ex);
        }
    }

    /**
     * Returns all boosters associated with this player's uuid from the local storage.
     *
     * @param uuid The UUID of the player you wish to lookup.
     * @return Returns a map with all associated entries.
     */
    public HashMap<Integer, HashMap<String, Object>> getBoostersForPlayer(UUID uuid) {
        HashMap<Integer, HashMap<String, Object>> data = new HashMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT id, type, duration, multiplier, time_purchased, time_expires FROM " + table + " WHERE owner_uuid = '" + uuid + "';");
            rs = ps.executeQuery();
            while (rs.next()) {
                HashMap<String, Object> results = new HashMap<>();
                results.put("type", rs.getString("type"));
                results.put("duration", rs.getInt("duration"));
                results.put("multiplier", rs.getInt("multiplier"));
                results.put("time_purchased", rs.getLong("time_purchased"));
                results.put("time_expires", rs.getLong("time_expires"));
                data.put(rs.getInt("ïd"), results);
            }
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
        return data;
    }

    /**
     * Creates a new booster entry in the local storage.
     *
     * @param uuid        The UUID of the owner of the booster.
     * @param type        The type of the booster.
     * @param duration    The length of the booster
     * @param multiplier  The multiplier of the booster
     * @param timeExpires An expiration timestamp. Leave as -1 for an infinite duration.
     */
    public void setBoosterForPlayer(UUID uuid, String type, Integer duration, Integer multiplier, Long timeExpires) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("REPLACE INTO " + table + " (type,duration,multiplier,owner_uuid,time_purchased,time_expires) VALUES(?,?,?,?,?,?)");
            ps.setString(1, type);
            ps.setInt(2, duration);
            ps.setInt(3, multiplier);
            ps.setString(4, uuid.toString());
            ps.setLong(5, System.currentTimeMillis());
            ps.setLong(6, timeExpires);
            ps.executeUpdate();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
    }

    private void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (SQLException ex) {
            Error.close(plugin, ex);
        }
    }
}