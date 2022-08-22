package com.github.happyrogelio7.waterfight.managers;

import com.github.happyrogelio7.waterfight.WaterFight;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
import java.util.Objects;

public class KitsManager {

    private final WaterFight plugin;

    public KitsManager(WaterFight plugin) {
        this.plugin = plugin;
    }

    public void createKit (String kit) {
        plugin.getKits().set("kits." + kit + ".name", kit);
        plugin.getKits().set("kits." + kit + ".customname", null);
        plugin.getKits().set("kits." + kit + ".description", null);
        plugin.getKits().set("kits." + kit + ".permission", null);
        plugin.getKits().set("kits." + kit + ".icon", null);
        plugin.getKits().set("kits." + kit + ".items", null);
        plugin.getKits().set("kits." + kit + ".armor", null);
        plugin.getKits().set("kits." + kit + ".effects", null);
        plugin.getKits().save();
        plugin.getKits().reload();
    }

    public void deleteKit (String kit) {
        plugin.getKits().set("kits." + kit, null);
        plugin.getKits().save();
        plugin.getKits().reload();
    }

    public void getKit (String kit) {
        plugin.getKits().get("kits." + kit);
    }

    public void getKitCustomName (String kit) {
        plugin.getKits().get("kits." + kit + ".customname");
    }

    public void getKitDescription (String kit) {
        plugin.getKits().get("kits." + kit + ".description");
    }

    public String getKitPermission (String kit) {
        plugin.getKits().get("kits." + kit + ".permission");
        return kit;
    }

    public void getKitIcon (String kit) {
        plugin.getKits().get("kits." + kit + ".icon");
    }

    public void getKitItems (String kit) {
        plugin.getKits().get("kits." + kit + ".items");
    }

    public void getKitArmor (String kit) {
        plugin.getKits().get("kits." + kit + ".armor");
    }

    public void getKitEffects (String kit) {
        plugin.getKits().get("kits." + kit + ".effects");
    }

    public void setKitCustomName (String kit, String customname) {
        plugin.getKits().set("kits." + kit + ".customname", customname);
    }

    public void setKitDescription (String kit, String description) {
        plugin.getKits().set("kits." + kit + ".description", description);
    }

    public void setKitPermission (String kit, String permission) {
        plugin.getKits().set("kits." + kit + ".permission", permission);
    }

    public void setKitIcon (String kit, String icon) {
        plugin.getKits().set("kits." + kit + ".icon", icon);
    }

    public void setKitItems (String kit, String items) {
        plugin.getKits().set("kits." + kit + ".items", items);
    }

    public void setKitArmor (String kit, String armor) {
        plugin.getKits().set("kits." + kit + ".armor", armor);
    }

    public void setKitEffects (String kit, String effects) {
        plugin.getKits().set("kits." + kit + ".effects", effects);
    }

    public void unsetKitEffects (String kit, String effects) {
        plugin.getKits().set("kits." + kit + ".effects", effects);
    }

    public void giveKit (String kit, Player player) {
        /*ItemStack a = (ItemStack) Objects.requireNonNull(plugin.getKits().get("kits." + kit + ".items"));
        plugin.getKits().get("kits." + kit + ".armor");
        plugin.getKits().get("kits." + kit + ".effects");*/

        player.getInventory().setContents((ItemStack[]) Objects.requireNonNull(plugin.getKits().get("kits." + kit + ".items")));
        player.getInventory().setArmorContents((ItemStack[]) plugin.getKits().get("kits." + kit + ".armor"));
        player.addPotionEffects((Collection<PotionEffect>) plugin.getKits().get("kits." + kit + ".effects"));
    }

    public void giveDefaultKit (Player player) {

        ItemStack STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);

        ItemMeta STONE_SWORD_META = STONE_SWORD.getItemMeta();
        STONE_SWORD_META.setDisplayName("Stone Sword");
        STONE_SWORD_META.addEnchant(Enchantment.KNOCKBACK, 1, true);
        STONE_SWORD_META.addEnchant(Enchantment.DURABILITY, 10, true);
        STONE_SWORD.setItemMeta(STONE_SWORD_META);

        ItemMeta FISHING_ROD_META = FISHING_ROD.getItemMeta();
        FISHING_ROD_META.setDisplayName("Fishing Rod");
        FISHING_ROD_META.addEnchant(Enchantment.DURABILITY, 10, true);
        FISHING_ROD.setItemMeta(FISHING_ROD_META);

        ItemStack SNOWBALL = new ItemStack(Material.SNOWBALL, 16);

        player.getInventory().setItem(0, STONE_SWORD);
        player.getInventory().setItem(1, FISHING_ROD);
        player.getInventory().setItem(2, SNOWBALL);
        player.getInventory().setItem(3, SNOWBALL);

        player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET, 1));
        player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
        player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS, 1));
        player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS, 1));

        //player.getInventory().setContents((ItemStack[]) Objects.requireNonNull(plugin.getKits().get("kits." + "default" + ".items")));
        //player.getInventory().setArmorContents((ItemStack[]) plugin.getKits().get("kits." + "default" + ".armor"));
        //player.addPotionEffects((Collection<PotionEffect>) plugin.getKits().get("kits." + "default" + ".effects"));
    }


}
