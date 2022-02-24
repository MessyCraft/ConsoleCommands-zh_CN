# ConsoleCommands
A minecraft bukkit plugin allows you to execute commands as player or server console.

Commands:

  /consolecommands: main command
  
    permission: no required permission
    alias: /cco
    usage: /consolecommands version/help
    
  /executeconsole: execute commands as console
    
    permission: consolecommands.execute.console
    alias: /execc,/console
    usage: /executeconsole <commands>
    
  /executeplayer: execute commands as player
  
    permission: consolecommands.execute.player
    alias: /execp,/fsudo
    usage: /executeplayer <player> <commands>
    
