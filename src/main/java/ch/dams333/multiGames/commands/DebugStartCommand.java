package ch.dams333.multiGames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ch.dams333.multiGames.MultiGames;

public class DebugStartCommand implements CommandExecutor {
    
    private MultiGames main;

    public DebugStartCommand(MultiGames main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        main.gameManager.debugStart();
        return false;
    }

}
