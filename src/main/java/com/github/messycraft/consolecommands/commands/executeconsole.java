package com.github.messycraft.consolecommands.commands;

import com.github.messycraft.consolecommands.ConsoleCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Locale;

import static org.bukkit.Bukkit.*;

public class executeconsole implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String arg = new String();
        for (String s : args) {
            arg = arg + s + " ";
        }
        if (args.length == 0) {
            sender.sendMessage("§e用法: §6/executeconsole <命令>");
        }
        else {
            if (sender.hasPermission("consolecommands.execute.console")) {
                Plugin mainclass = ConsoleCommands.getProvidingPlugin(ConsoleCommands.class);
                if (mainclass.getConfig().getBoolean("allow_lp_command")) {
                    cmdBannedC(arg, sender);
                }
                else {
                    Boolean allow = true;
                    if (arg.startsWith("lp")) allow = false;
                    if (allow && arg.startsWith("luckperms")) allow = false;
                    if (allow && arg.startsWith("perm")) allow = false;
                    if (allow && arg.startsWith("perms")) allow = false;
                    if (allow && arg.startsWith("permission")) allow = false;
                    if (allow && arg.startsWith("permissions")) allow = false;
                    if (allow) {
                        cmdBannedC(arg, sender);
                    }
                    else {
                        sender.sendMessage("§c您不能以控制台身份使用 LuckPerms 的命令! ");
                    }
                }
            }
            else {
                sender.sendMessage("§c您没有权限");
            }
        }
        return false;
    }

    public void cmdBannedC(String cmd, CommandSender sender) {
        Plugin m = ConsoleCommands.getProvidingPlugin(ConsoleCommands.class);
        List<String> list = m.getConfig().getStringList("bannedcmd_asconsole");
        boolean banned = false;
        for (int i=0;i<list.size();i++) {
            if (cmd.toLowerCase().startsWith(list.get(i).toLowerCase() + " ")) {
                banned = true;
                break;
            }
        }
        if (!banned) {
            if (getServer().dispatchCommand(getConsoleSender(), cmd)) {
                sender.sendMessage("§e成功以控制台身份执行 §6" + cmd);
            } else {
                sender.sendMessage("§e未找到命令 §6" + cmd);
            }
        }
        else {
            sender.sendMessage("§c您不能以控制台身份执行这个命令! ");
        }
    }
}
