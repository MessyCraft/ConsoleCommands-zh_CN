package io.consolecommands;

import io.consolecommands.commands.ccreload;
import io.consolecommands.commands.executeconsole;
import io.consolecommands.commands.executeplayer;
import io.consolecommands.commands.loopcmd;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConsoleCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Consolecommands loaded.");
        saveDefaultConfig();
        reloadConfig();
        getCommand("executeconsole").setExecutor(new executeconsole());
        getCommand("executeplayer").setExecutor(new executeplayer());
        getCommand("executeplayer").setTabCompleter(new executeplayer());
        getCommand("loopcmd").setExecutor(new loopcmd());
        getCommand("loopcmd").setTabCompleter(new loopcmd());
        getCommand("ccreload").setExecutor(new ccreload());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Consolecommands unloaded.");
    }
}
