package me.theblockbender.multiplier;

import me.theblockbender.multiplier.cmd.BaseCommand;
import me.theblockbender.multiplier.data.Database;
import me.theblockbender.multiplier.data.SQLite;
import me.theblockbender.multiplier.listener.GuiPageListener;
import me.theblockbender.multiplier.util.UtilLanguage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main instance;
    public FileConfiguration messages;
    private Database db;
    private UtilLanguage language;

    /**
     * Static getter of the Main instance. Used in the ScrollGui section.
     * Preferred is not to access this anywhere else.
     *
     * @return Main class.
     */
    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        createFiles();
        db = new SQLite(this);
        db.load();
        language = new UtilLanguage(this);
        registerEvents();
        registerCommands();
    }

    public void onDisable() {
        db = null;
        instance = null;
    }

    public void log(String message) {
        getLogger().warning(message);
    }

    public Database getDatabase() {
        return db;
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new GuiPageListener(this), this);
    }

    private void registerCommands() {
        getCommand("multiplier").setExecutor(new BaseCommand(this));
    }

    public UtilLanguage getLanguage() {
        return language;
    }

    private void createFiles() {
        File messagesFile = new File(getDataFolder(), "language.yml");
        if (!messagesFile.exists()) {
            messagesFile.getParentFile().mkdirs();
            saveResource("language.yml", false);
        }
        messages = new YamlConfiguration();
        try {
            messages.load(messagesFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
