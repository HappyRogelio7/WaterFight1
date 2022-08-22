package com.github.happyrogelio7.waterfight.managers;

import com.github.happyrogelio7.waterfight.WaterFight;
import com.github.happyrogelio7.waterfight.utils.LocationUtils;
import com.github.happyrogelio7.waterfight.utils.MessageColors;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectTypeWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    private final WaterFight plugin;

    public GameManager(WaterFight plugin) {
        this.plugin = plugin;
    }

    public static Map<String, Player> players = new HashMap<>();

    public static Map<String, List<Player>> playersInArena = new HashMap<>();

    public void addPlayer(Player player) {
        players.put(player.getName(), player);
    }

    public void removePlayer(Player player) {
        players.remove(player.getName());
    }

    public void addPlayerToArena(Player player, String arena) {
        if (!playersInArena.containsKey(arena)) {
            playersInArena.put(arena, new ArrayList<Player>());
        }
        playersInArena.get(arena).add(player);
    }

    public void removePlayerFromArena(Player player, String arena) {
        if (playersInArena.containsKey(arena)) {
            playersInArena.get(arena).remove(player);
        }
    }

    public void removePlayerFromAllArenas(Player player) {
        for (String arena : playersInArena.keySet()) {
            playersInArena.get(arena).remove(player);
        }
    }

    public List<Player> getPlayersInArena(String arena) {
        return playersInArena.get(arena);
    }

    public boolean isPlayerInArena(Player player, String arena) {
        return playersInArena.get(arena).contains(player);
    }

    public boolean isPlayerInAnyArena(Player player) {
        for (String arena : playersInArena.keySet()) {
            if (playersInArena.get(arena).contains(player)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPlaying(Player player) {
        return players.containsKey(player);
    }

    public void joinGame(String arena, Player player) {

        if (!plugin.getArenas().contains(arena)) {
            player.sendMessage(MessageColors.getMsgColor("&cThe arena you are trying to join does not exist."));
            return;
        }

        /*if (!(plugin.getArenas().getInt(arena + ".maxPlayers") == this.getPlayersInArena(arena).size())) {
            player.sendMessage(MessageColors.getMsgColor("&cThe arena you are trying to join is full."));
            return;
        }*/

        if ((plugin.getConfig().getString("lobby.spawn") == null)) {
            player.sendMessage(MessageColors.getMsgColor("&cThe arenas you are trying to join does not have a Lobby."));
            return;
        }

        if ((plugin.getArenas().getString(arena + ".spawn") == null)) {
            player.sendMessage(MessageColors.getMsgColor("&cThe arena you are trying to join does not have a Spawn."));
            return;
        }

        if ((plugin.getArenas().getString(arena + ".void") == null)) {
            player.sendMessage(MessageColors.getMsgColor("&cThe arena you are trying to join does not have a Void."));
            return;
        }

        /*if ((this.isPlayerInAnyArena(player))) {
            player.sendMessage(MessageColors.getMsgColor("&cYou are already in an arena."));
        }*/

        if (!this.isPlayerInAnyArena(player)) {
            GameManager.players.put(arena, player);
            this.addPlayerToArena(player, arena);
            player.sendMessage(MessageColors.getMsgColor("&aYou have joined the arena."));

            ArenaManager am = new ArenaManager(plugin);

            Location l = LocationUtils.getStringLocation(plugin.getArenas().getString(arena + ".spawn"));

            player.teleport(l);

            KitsManager km = new KitsManager(plugin);
            km.giveDefaultKit(player);

        } else {
            player.sendMessage(MessageColors.getMsgColor("&cYou are already in the arena."));
        }

    }

    public void leaveGame(Player player) {
        if (this.isPlayerInAnyArena(player)) {
            GameManager.players.remove(player);
            this.removePlayerFromAllArenas(player);

            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            //player.getInventory().setContents(null);

            player.sendMessage(MessageColors.getMsgColor("&aYou have left the arena."));

            Location l = LocationUtils.getStringLocation(plugin.getConfig().getString("lobby.spawn"));

            player.teleport(l);

        } else {
            player.sendMessage(MessageColors.getMsgColor("&cYou are not in an arena."));
        }
    }

    public void leaveAllGames(Player player) {
        if (this.isPlayerInAnyArena(player)) {
            this.removePlayerFromAllArenas(player);

            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.getInventory().setContents(null);

            Location l = LocationUtils.getStringLocation(plugin.getConfig().getString("lobby.spawn"));

            player.teleport(l);

            player.sendMessage(MessageColors.getMsgColor("&aYou have left all arenas."));
        } else {
            player.sendMessage(MessageColors.getMsgColor("&cYou are not in an arena."));
        }
    }




}

