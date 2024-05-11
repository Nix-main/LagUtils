package org.nixtils.lagutils;

import org.bukkit.plugin.java.JavaPlugin;
import org.nixtils.lagutils.commands.LagCmd;
import org.nixtils.lagutils.util.Util;

import java.util.ArrayList;

public final class LagDetector extends JavaPlugin {

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();

        saveDefaultConfig();
        new LagCmd(this);
        String version = getDescription().getVersion();
        getCommand("lagutils").setExecutor(new LagCmd(this));
        Util.log("&aSuccessfully enabled %s&7 in &b%.2f seconds", version, (float) (System.currentTimeMillis() - start) / 1000);


    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        long start = System.currentTimeMillis();
        String version = getDescription().getVersion();
        Util.log("&aSuccessfully disabled %s&7 in %.2f seconds", version, (float) (System.currentTimeMillis() - start) / 1000);
    }



    }


