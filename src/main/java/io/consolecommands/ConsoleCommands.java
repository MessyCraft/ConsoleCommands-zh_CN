package io.consolecommands;

import io.consolecommands.commands.consolecommands;
import io.consolecommands.commands.executeconsole;
import io.consolecommands.commands.executeplayer;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConsoleCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Consolecommands loaded.");
        //getCommand("consolecommands").setExecutor(new consolecommands());
        getCommand("executeconsole").setExecutor(new executeconsole());
        getCommand("executeplayer").setExecutor(new executeplayer());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Consolecommands unloaded.");
    }
}
