package org.nixtils.lagutils.commands;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.PluginDescriptionFile;
import org.nixtils.lagutils.LagDetector;
import org.nixtils.lagutils.util.Util;

import javax.annotation.Nullable;

public class LagCmd implements CommandExecutor {
    public static final NamespacedKey ALERTS = new NamespacedKey(LagDetector.getPlugin(LagDetector.class), "alerts");
    @Nullable
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(args.length == 0)) {
            if (args[0].equals("menu")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            Util.PREFIX + "This command can only be executed by a player!"));
                    return true;
                }
                Player player = (Player) sender;
                // Will be added in the future
            } else if (args[0].equals("info")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&cLagUtils &ais a plugin developed by Nix to inform server owners and staff members of possible lag machines." +
                                "\n&aIt features one command, with several arguments." +
                                "\n&6/lagutils help - Shows the arguments for the command" +
                                "\n&9The plugin is currently in v" + desc.getVersion() +
                                "\n&bMore info can be found on the github page: " +
                                "\n&ccoming soon."));

            } else if (args[0].equals("help")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "\n&6/lagutils toggle - Toggles lagutils alerts for yourself only" +
                                "\n&6/lagutils global - Toggles lagutils alerts globally, requires Lagutils.global permission)" +
                                "\n&6/lagutils menu - Opens a menu for convenience, not usable by console " +
                                "\n&6/lagutils disable - Completely disables the plugin. The server will have to be restarted to re-enable." +
                                "\n&6/lagutils info - Opens this info page" +
                                "\n&6/lagutils help - Shows the arguments for the command" +
                                "\n&6/lagutils check - Checks the status of your alerts"));
            } else if (args[0].equals("toggle")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            Util.PREFIX + "Console cannot disable LagUtils alerts!"));
                    return true;
                }
                Player p = (Player) sender;
                int on = p.getPersistentDataContainer().getOrDefault(ALERTS, PersistentDataType.INTEGER, 1);
                if (on == 1){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            Util.PREFIX + "&eYour alerts are now: &aOn&e."));
                    p.getPersistentDataContainer().set(ALERTS, PersistentDataType.INTEGER, 0);
                }
                else if (on == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            Util.PREFIX + "&eYour alerts are now: &cOff&e."));
                    p.getPersistentDataContainer().set(ALERTS, PersistentDataType.INTEGER, 1);
                }
            } else if (args[0].equals("check")){
                if(!(sender instanceof Player)){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            Util.PREFIX + "&eConsole always has alerts &aOn&e!"));
                    return true;
                }
                Player p = (Player) sender;
                int on = p.getPersistentDataContainer().getOrDefault(ALERTS, PersistentDataType.INTEGER, 1);
                if (on == 0){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            Util.PREFIX + "&eYour alerts are: &aOn&e."));
                }
                else if (on == 1){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            Util.PREFIX + "&eYour alerts are: &cOff&e."));
                }
            }
        }
        return true;
    }
    private final PluginDescriptionFile desc;
    public LagCmd(LagDetector plugin) {
        this.desc = plugin.getDescription();
    }
}



