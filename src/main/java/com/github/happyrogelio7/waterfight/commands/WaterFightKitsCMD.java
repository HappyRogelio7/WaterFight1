package com.github.happyrogelio7.waterfight.commands;

import com.github.happyrogelio7.waterfight.WaterFight;
import com.github.happyrogelio7.waterfight.managers.KitsManager;
import com.github.happyrogelio7.waterfight.utils.MessageColors;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WaterFightKitsCMD implements CommandExecutor {

    private final WaterFight plugin;

    public WaterFightKitsCMD(WaterFight plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageColors.getMsgColor("&cYou must be a player to use this command."));
            return true;
        }

        Player p = (Player) sender;

        KitsManager km = new KitsManager(plugin);

        if (args.length == 0) {
            p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits help"));
            return true;
        }

        if (args.length > 1) {

            if (args[0].equalsIgnoreCase("help")){



            } else if (args[0].equalsIgnoreCase("create")){

                if (args.length == 1) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits create <name>"));
                    return true;
                }

                km.createKit(args[1]);

                p.sendMessage(MessageColors.getMsgColor("&aKit created: &f" + args[1]));

            } else if (args[0].equalsIgnoreCase("delete")){

                if (args.length == 1) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits delete <name>"));
                    return true;
                }

                km.deleteKit(args[1]);

                p.sendMessage(MessageColors.getMsgColor("&aKit deleted: &f" + args[1]));

            } else if (args[0].equalsIgnoreCase("customname")){

                if (args.length == 1) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits customname <name> <custom name>"));
                    return true;
                }

                km.setKitCustomName(args[1], args[2]);

                p.sendMessage(MessageColors.getMsgColor("&aKit &f"+args[1]+" &aCustom name set: &f" + args[2]));

            } else if (args[0].equalsIgnoreCase("description")){

                if (args.length == 1) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits description <name> <description>"));
                    return true;
                }

                km.setKitDescription(args[1], args[2]);

                p.sendMessage(MessageColors.getMsgColor("&aKit &f"+args[1]+" &aDescription set: &f" + args[2]));

            } else if (args[0].equalsIgnoreCase("setperm")){

                if (args.length == 1) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits setperm <name> <permission>"));
                    return true;
                }

                km.setKitPermission(args[1], args[2]);

                p.sendMessage(MessageColors.getMsgColor("&aKit &f"+args[1]+" &aPermission set: &f" + args[2]));

            } else if (args[0].equalsIgnoreCase("seticon")) {

                if (args.length == 1) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits seticon <name> <icon>"));
                    return true;
                }

                km.setKitIcon(args[1], args[2]);

                p.sendMessage(MessageColors.getMsgColor("&aKit &f"+args[1]+" &aIcon set: &f" + args[2]));

            } else if (args[0].equalsIgnoreCase("setitems")){

                if (args.length == 1) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits setitems <name>"));
                    return true;
                }

                String items = String.valueOf(p.getInventory().getContents());

                km.setKitItems(args[1], items);

                p.sendMessage(MessageColors.getMsgColor("&aKit &f"+args[1]+" &aItems Set successfully"));

            } else if (args[0].equalsIgnoreCase("setarmor")){

                if (args.length == 1) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits setarmor <name>"));
                    return true;
                }

                String armor = String.valueOf(p.getInventory().getArmorContents());

                km.setKitArmor(args[1], armor);

                p.sendMessage(MessageColors.getMsgColor("&aKit &f"+args[1]+" &aArmor Set successfully"));

            } else if (args[0].equalsIgnoreCase("seteffect")){

                if (args.length == 1 || args.length == 2 || args.length == 3) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits seteffect <name> <effect> <duration> <amplifier>"));
                    return true;
                }

                String effect = args[2];
                String duration = args[3];
                String amplifier = args[4];

                PotionEffectType e = PotionEffectType.getByName(effect);
                int d = Integer.parseInt(duration);
                int a = Integer.parseInt(amplifier);

                PotionEffect pe = new PotionEffect(e, d, a);

                km.setKitEffects(args[1], String.valueOf(pe));

                p.sendMessage(MessageColors.getMsgColor("&aKit &f"+args[1]+" &aEffect set: &f" + effect + " &aDuration: &f" + duration + " &aAmplifier: &f" + amplifier));


            } else if (args[0].equalsIgnoreCase("unseteffect")){

                if (args.length == 1  || args.length == 2 || args.length == 3) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits unseteffect <name> <duration> <amplifier>"));
                    return true;
                }

                String effect = args[2];
                String duration = args[3];
                String amplifier = args[4];

                PotionEffectType e = PotionEffectType.getByName(effect);
                int d = Integer.parseInt(duration);
                int a = Integer.parseInt(amplifier);

                PotionEffect pe = new PotionEffect(e, d, a);

                km.unsetKitEffects(args[1], String.valueOf(pe));

            } if (args[0].equalsIgnoreCase("give")){

                if (!p.hasPermission("waterfight.kit.give")){
                    p.sendMessage(MessageColors.getMsgColor("&cYou do not have permission to use this command"));
                    return true;
                }

                if (args.length == 1) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits give <name> [player]"));
                    return true;
                }



                if (args.length == 2) {
                    km.giveKit(args[1], p);
                    p.sendMessage(MessageColors.getMsgColor("&aKit &f"+args[1]+" &agiven to &f" + p.getName()));
                } else {
                    Player target = Bukkit.getPlayer(args[2]);
                    km.giveKit(args[1], target);
                    p.sendMessage(MessageColors.getMsgColor("&aKit &f"+args[1]+" &agiven to &f" + target.getName()));
                }

            } if (args[0].equalsIgnoreCase("givep")){

                if (args.length == 1) {
                    p.sendMessage(MessageColors.getMsgColor("&cUsage: /waterfightkits givep <name>"));
                    return true;
                }

                if (!p.hasPermission(km.getKitPermission(args[1]))){
                    p.sendMessage(MessageColors.getMsgColor("&cYou not permission using this kit."));
                    return true;
                }

                km.giveKit(args[1], p);

                p.sendMessage(MessageColors.getMsgColor("&aKit &f"+args[1]+" &agiven to &f" + p.getName()));

            }

            return true;
        }


        return true;
    }
}
