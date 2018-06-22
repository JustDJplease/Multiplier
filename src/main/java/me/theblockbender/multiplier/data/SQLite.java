package me.theblockbender.multiplier.data;

import me.theblockbender.multiplier.Main;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;


public class SQLite extends Database {

    public SQLite(Main instance) {
        super(instance);
    }

    public Connection getSQLConnection() {
        String dbname = "data";
        File dataFolder = new File(plugin.getDataFolder(), dbname + ".db");
        if (!dataFolder.exists()) {
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: " + dbname + ".db");
            }
        }
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            String SQLiteCreateTokensTable = "CREATE TABLE IF NOT EXISTS multipliers (`id` int NOT NULL," +
                    "`type` varchar NOT NULL," +
                    "`duration` int NOT NULL," +
                    "`multiplier` int NOT NULL," +
                    "`owner_uuid` varchar NOT NULL," +
                    "`time_purchased` int) NOT NULL," +
                    "`time_expires` int NOT NULL," +
                    "PRIMARY KEY (`id`));";
            s.executeUpdate(SQLiteCreateTokensTable);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }
}
