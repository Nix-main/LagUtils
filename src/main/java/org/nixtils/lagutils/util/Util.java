package org.nixtils.lagutils.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

import java.util.regex.Pattern;

/**
 * Code from SkBee. Used with permission from shane.
 */
public class Util {

    public static final String PREFIX = "&7[&bLagUtils&7] ";
    private static final Pattern HEX_PATTERN = Pattern.compile("<#([A-Fa-f\\d]){6}>");

    @SuppressWarnings("deprecation") // Paper deprecation
    public static String getColString(String string) {
        string = HEX_PATTERN.matcher(string).replaceAll("");
        return ChatColor.translateAlternateColorCodes('&', string);
    }




    public static void log(String format, Object... objects) {
        String log = String.format(format, objects);
        Bukkit.getConsoleSender().sendMessage(getColString(PREFIX + log));

    }
}