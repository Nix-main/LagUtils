package org.nixtils.lagutils;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;
import org.nixtils.lagutils.commands.LagCmd;
import org.nixtils.lagutils.util.DelayedTask;
import org.nixtils.lagutils.util.Util;

import java.util.ArrayList;


public final class LagDetector extends JavaPlugin {
    @SuppressWarnings({"usage", "deprecation"})
    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();
        saveDefaultConfig();
        new LagCmd(this);
        new DelayedTask(this);
        String version = getDescription().getVersion();
        getCommand("lagutils").setExecutor(new LagCmd(this));
        Util.log("&aSuccessfully enabled v%s&7 in &b%.2f seconds", version, (float) (System.currentTimeMillis() - start) / 1000);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            double[] tps = getServer().getTPS();
            int tps2 = (int)Math.round(tps[0]);
            if (tps2 < 18) {
                getLogger().info("The server is lagging! Current tps: " + tps2);
            }





        }, 20 * 3, 20 * 2);
    }

    @SuppressWarnings({"usage", "deprecation"})
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        long start = System.currentTimeMillis();
        String version = getDescription().getVersion();
        Util.log("&aSuccessfully disabled v%s&7 in %.2f seconds", version, (float) (System.currentTimeMillis() - start) / 1000);
    }
}


