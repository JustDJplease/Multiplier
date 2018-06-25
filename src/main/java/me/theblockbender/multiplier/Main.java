package me.theblockbender.multiplier;

import me.theblockbender.multiplier.booster.Booster;
import me.theblockbender.multiplier.booster.BoosterTask;
import me.theblockbender.multiplier.cmd.BaseCommand;
import me.theblockbender.multiplier.data.Database;
import me.theblockbender.multiplier.data.SQLite;
import me.theblockbender.multiplier.listener.GuiPageListener;
import me.theblockbender.multiplier.util.UtilBossBar;
import me.theblockbender.multiplier.util.UtilLanguage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    private static Main instance;
    public FileConfiguration messages;
    public List<Booster> boosters = new ArrayList<>();
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
        initializeDatabase();
        registerLocale();
        registerEvents();
        registerCommands();
        startRunnables();
    }

    public void onDisable() {
        UtilBossBar.hideAllBossBars();
        db = null;
        messages = null;
        boosters.clear();
        language = null;
        instance = null;
    }

    /**
     * Get an instance of the database.
     *
     * @return Current database instance.
     */
    public Database getDatabase() {
        return db;
    }

    /**
     * Register all events.
     */
    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new GuiPageListener(this), this);
    }

    /**
     * Register all commands.
     */
    private void registerCommands() {
        getCommand("multiplier").setExecutor(new BaseCommand(this));
    }

    /**
     * Get an instance of the language handler.
     *
     * @return Current language instance.
     */
    public UtilLanguage getLanguage() {
        return language;
    }

    /**
     * Creates additional files.
     */
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
        saveDefaultConfig();
    }

    /**
     * Creates an instance of the locale.
     */
    private void registerLocale() {
        language = new UtilLanguage(this);
    }

    /**
     * Creates an instance of the database.
     */
    private void initializeDatabase() {
        db = new SQLite(this);
        db.load();
    }

    /**
     * Starts all runnables.
     */
    private void startRunnables() {
        Bukkit.getScheduler().runTaskTimer(this, new BoosterTask(this), 1L, 1L);
    }
}
