package com.github.happyrogelio7.waterfight.listeners;

import com.github.happyrogelio7.waterfight.WaterFight;
import com.github.happyrogelio7.waterfight.managers.ArenaManager;
import com.github.happyrogelio7.waterfight.managers.GameManager;
import com.github.happyrogelio7.waterfight.utils.LocationUtils;
import com.github.happyrogelio7.waterfight.utils.MessageColors;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnMoveEvent implements Listener {

    private final WaterFight plugin;

    public OnMoveEvent(WaterFight plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        GameManager gm = new GameManager(plugin);

        ArenaManager am = new ArenaManager(plugin);

        if (!GameManager.isPlaying(p)) {
            p.sendMessage(MessageColors.getMsgColor("&cYou are not playing!"));
            System.out.println("You are not playing!");
            return;
        }

        if (p.getLocation().getY() <= plugin.getArenas().getInt("arenas." + GameManager.isPlaying(p) + ".void")) {
            p.sendMessage(MessageColors.getMsgColor("&cYou fell out of the arena!"));
            System.out.println("You fell out of the arena!");
        }

        System.out.println("You are playing!");
    }

}
