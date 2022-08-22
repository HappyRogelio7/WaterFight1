package com.github.happyrogelio7.waterfight.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtils {

    public static Location getStringLocation(String location) {
        if (location == null)
            return null;
        String[] l = location.split(";");
        if (l.length < 6)
            return null;
        World world = Bukkit.getWorld(l[0]);
        double x = Double.parseDouble(l[1]);
        double y = Double.parseDouble(l[2]);
        double z = Double.parseDouble(l[3]);
        float yaw = Float.parseFloat(l[4]);
        float pitch = Float.parseFloat(l[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static String getLocationString(Location loc) {
        if (loc == null)
            return "set!";
        return loc.getWorld().getName() + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch();
    }
}
