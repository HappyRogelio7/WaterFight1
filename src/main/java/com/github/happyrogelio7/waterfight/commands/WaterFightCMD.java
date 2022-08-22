package com.github.happyrogelio7.waterfight.commands;

import com.github.happyrogelio7.waterfight.WaterFight;
import com.github.happyrogelio7.waterfight.managers.GameManager;
import com.github.happyrogelio7.waterfight.utils.MessageColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class WaterFightCMD implements CommandExecutor {

    private final WaterFight plugin;

    public WaterFightCMD(WaterFight plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageColors.getMsgColor("&cYou must be a player to use this command."));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfight help"));
            return true;
        }

        GameManager gm = new GameManager(plugin);

        if (args[0].equalsIgnoreCase("help")){

            player.sendMessage(MessageColors.getMsgColor("&r "));
            player.sendMessage(MessageColors.getMsgColor("&a&lWaterFight Help"));
            player.sendMessage(MessageColors.getMsgColor("&r "));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfight help &7- &fShows this help."));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfightadmin help &7- &fShows the admin help."));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfight join <arena> &7- &fJoins an arena."));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfight leave &7- &fLeaves an arena."));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfight list &7- &fLists all arenas."));
            player.sendMessage(MessageColors.getMsgColor("&r "));

            return true;

        } else if (args[0].equalsIgnoreCase("join")) {

            if (args.length == 1) {
                player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfight join <arena>"));
                return true;
            }

            gm.joinGame(args[1], player);

            return true;

        } else if (args[0].equalsIgnoreCase("leave")) {

            gm.leaveGame(player);

            return true;

        } else if (args[0].equalsIgnoreCase("list")) {

            player.sendMessage(MessageColors.getMsgColor("&r "));
            player.sendMessage(MessageColors.getMsgColor("&a&lWaterFight Arenas:"));
            player.sendMessage(MessageColors.getMsgColor("&r "));

            ArrayList<String> arenaslist = (ArrayList<String>) plugin.getArenaslist().getStringList("arenas");

            for (String arena : arenaslist) {
                player.sendMessage(MessageColors.getMsgColor("&a " + arena));
            }

            /*for (String arena : plugin.getArenaslist().getConfigurationSection("arenas").getKeys(false)) {
                player.sendMessage(MessageColors.getMsgColor("&a" + arena.toString()));
            }*/

            player.sendMessage(MessageColors.getMsgColor("&r "));

            return true;

        } else {

            player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfight help"));
            return true;

        }
    }
}
