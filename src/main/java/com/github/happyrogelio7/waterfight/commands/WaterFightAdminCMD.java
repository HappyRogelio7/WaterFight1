package com.github.happyrogelio7.waterfight.commands;

import com.github.happyrogelio7.waterfight.WaterFight;
import com.github.happyrogelio7.waterfight.managers.ArenaManager;
import com.github.happyrogelio7.waterfight.utils.MessageColors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WaterFightAdminCMD implements CommandExecutor {

    private final WaterFight plugin;

    public WaterFightAdminCMD(WaterFight plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageColors.getMsgColor("&cYou must be a player to use this command."));
            return true;
        }

        if (!sender.hasPermission("waterfight.cmd.admin")) {
            sender.sendMessage(MessageColors.getMsgColor("&cYou don't have permission to use this command."));
            return true;
        }

        Player player = (Player) sender;

        ArenaManager am = new ArenaManager(plugin);

        if (args.length == 0) {
            player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightadmin help"));
            return true;
        }

        if (args[0].equalsIgnoreCase("help")){

            player.sendMessage(MessageColors.getMsgColor("&r "));
            player.sendMessage(MessageColors.getMsgColor("&a&lWaterFight Help"));
            player.sendMessage(MessageColors.getMsgColor("&r "));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfightadmin help &7- &fShows this help menu."));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfightadmin create &7- &fCreates a new arena."));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfightadmin delete &7- &fDeletes an arena."));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfightadmin setspawn &7- &fSets the spawn of an arena."));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfightadmin setvoid &7- &fSets the void of an arena."));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfightadmin setmaxplayers &7- &fSets the max players of an arena."));
            player.sendMessage(MessageColors.getMsgColor("&a/waterfightadmin setlobby &7- &fSets the main lobby."));
            player.sendMessage(MessageColors.getMsgColor("&r "));

            return true;

        } else if (args[0].equalsIgnoreCase("create")){

            if (args.length == 1){
                player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightadmin create <arena>"));
                return true;
            }

            if (plugin.getArenas().getString(args[1] + ".name") != null){
                player.sendMessage(MessageColors.getMsgColor("&cThat arena already exists."));
                return true;
            }

            am.createArena(args[1]);

            player.sendMessage(MessageColors.getMsgColor("&aArena " + args[1] + " created."));

            return true;

        } else if (args[0].equalsIgnoreCase("delete")){

            if (args.length == 1){
                player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightadmin delete <arena>"));
                return true;
            }

            if (plugin.getArenas().getString(args[1] + ".name") == null){
                player.sendMessage(MessageColors.getMsgColor("&cThat arena doesn't exist."));
                return true;
            }

            am.deleteArena(args[1]);

            return true;

        } else if (args[0].equalsIgnoreCase("setspawn")){

            if (args.length == 1){
                player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightadmin setspawn <arena>"));
                return true;
            }

            if (plugin.getArenas().getString(args[1] + ".name") == null){
                player.sendMessage(MessageColors.getMsgColor("&cThat arena doesn't exist."));
                return true;
            }

            am.setSpawn(args[1], player);

            return true;

        } else if (args[0].equalsIgnoreCase("setvoid")){

            if (args.length == 1){
                player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightadmin setvoid <arena>"));
                return true;
            }

            if (plugin.getArenas().getString(args[1] + ".name") == null){
                player.sendMessage(MessageColors.getMsgColor("&cThat arena doesn't exist."));
                return true;
            }

            am.setVoid(args[1], player);

            return true;

        } else if (args[0].equalsIgnoreCase("setmaxplayers")) {

            if (args.length == 1) {
                player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightadmin setmaxplayers <arena> <max players>"));
                return true;
            }

            if (args.length == 2) {
                player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightadmin setmaxplayers <arena> <max players>"));
                return true;
            }

            if (plugin.getArenas().getString(args[1] + ".name") == null) {
                player.sendMessage(MessageColors.getMsgColor("&cThat arena doesn't exist."));
                return true;
            }

            am.setMaxPlayers(args[1], Integer.parseInt(args[2]));

            return true;

        } else if (args[0].equalsIgnoreCase("setlobby")) {

            am.setLobby(player);

            player.sendMessage(MessageColors.getMsgColor("&aLobby set."));

            return true;

        } else {
            player.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightadmin help"));
            return true;
        }
    }
}
