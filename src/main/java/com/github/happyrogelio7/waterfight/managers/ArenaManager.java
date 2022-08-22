package com.github.happyrogelio7.waterfight.managers;

import com.github.happyrogelio7.waterfight.WaterFight;
import com.github.happyrogelio7.waterfight.utils.LocationUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ArenaManager {

    private final WaterFight plugin;

    public ArenaManager(WaterFight plugin) {
        this.plugin = plugin;
    }

    public void setLobby (Player player) {
        plugin.getConfig().set("lobby.spawn", LocationUtils.getLocationString(player.getLocation()));
        plugin.getConfig().save();

        System.out.println(LocationUtils.getLocationString(player.getLocation()));
    }

    public void setSpawn (String arena, Player player) {
        plugin.getArenas().set(arena + ".spawn", LocationUtils.getLocationString(player.getLocation()));
        plugin.getArenas().save();
    }

    public void setVoid (String arena, Player player){
        plugin.getArenas().set(arena + ".void", player.getLocation().getBlockY());
        plugin.getArenas().save();
    }


    public void setMaxPlayers (String arena, int maxPlayers) {
        plugin.getArenas().set(arena + ".maxPlayers", maxPlayers);
        plugin.getArenas().save();
    }


    public void getArena (String arena) {
        plugin.getArenas().get(arena);
    }

    public void getSpawn (String arena) {
        LocationUtils.getStringLocation(plugin.getArenas().getString(arena + ".spawn"));
    }

    public void getVoid (String arena) {
        plugin.getArenas().get(arena + ".void");
    }

    public void getMaxPlayers (String arena) {
        plugin.getArenas().get(arena + ".maxPlayers");
    }

    public void createArena (String arena){
        ArrayList<String> arenas = new ArrayList<>();
        if (plugin.getArenaslist().contains("arenas")) {
            arenas = (ArrayList<String>) plugin.getArenas().getStringList("arenas");
        }
        arenas.add(arena);
        plugin.getArenaslist().set("arenas", arenas);
        plugin.getArenaslist().save();

        plugin.getArenas().set(arena + ".name", arena);
        plugin.getArenas().set(arena + ".customname", arena);
        plugin.getArenas().set(arena + ".spawn", null);
        plugin.getArenas().set(arena + ".void", null);
        plugin.getArenas().set(arena + ".maxPlayers", null);
        plugin.getArenas().save();
    }

    public void deleteArena (String arena){
        ArrayList<String> arenas = new ArrayList<>();
        if (plugin.getArenaslist().contains("arenas")) {
            arenas = (ArrayList<String>) plugin.getArenas().getStringList("arenas");
        }
        arenas.remove(arena);
        plugin.getArenas().set(arena, null);
        plugin.getArenas().save();
    }

    public void setCustomName (String arena, String customname){
        plugin.getArenas().set(arena + ".customname", customname);
        plugin.getArenas().save();
    }

}
