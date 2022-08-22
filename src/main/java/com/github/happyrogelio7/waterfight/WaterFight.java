package com.github.happyrogelio7.waterfight;

import com.github.happyrogelio7.waterfight.commands.WaterFightAdminCMD;
import com.github.happyrogelio7.waterfight.commands.WaterFightCMD;
import com.github.happyrogelio7.waterfight.commands.WaterFightKitsCMD;
import com.github.happyrogelio7.waterfight.file.FileManager;
import com.github.happyrogelio7.waterfight.listeners.OnMoveEvent;
import com.github.happyrogelio7.waterfight.managers.ArenaManager;
import com.github.happyrogelio7.waterfight.managers.GameManager;
import com.github.happyrogelio7.waterfight.managers.KitsManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class WaterFight extends JavaPlugin {

    FileManager config;

    FileManager arenas;

    FileManager arenaslist;

    FileManager kits;


    private static WaterFight instance;

    public static WaterFight getInstance() {
        return instance;
    }

    private static ArenaManager arenaManager;

    public static ArenaManager getArenaManager() {
        return arenaManager;
    }

    private static GameManager gameManager;

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static KitsManager kitsManager;

    public static KitsManager getKitsManager() {
        return kitsManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        onLoadFiles();
        registerListeners();
        registerCommands();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveFiles();
    }

    public void registerCommands() {
        getCommand("waterfight").setExecutor((CommandExecutor)new WaterFightCMD(this));
        getCommand("waterfightadmin").setExecutor((CommandExecutor) new WaterFightAdminCMD(this));
        getCommand("waterfightkits").setExecutor((CommandExecutor) new WaterFightKitsCMD(this));
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new OnMoveEvent(this), this);
    }


    public void onLoadFiles() {
        this.config = new FileManager(this, "config");
        this.arenas = new FileManager(this, "arenas");
        this.arenaslist = new FileManager(this, "arenaslist");
        this.kits = new FileManager(this, "kits");
    }

    public void saveFiles() {
        this.config.save();
        this.arenas.save();
        this.arenaslist.save();
        this.kits.save();
    }

    public FileManager getConfig() {
        return config;
    }

    public FileManager getArenas() {
        return arenas;
    }

    public FileManager getArenaslist() {
        return arenaslist;
    }

    public FileManager getKits() {
        return kits;
    }
}
