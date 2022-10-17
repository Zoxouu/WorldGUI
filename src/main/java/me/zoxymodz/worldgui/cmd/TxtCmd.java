package me.zoxymodz.worldgui.cmd;

import me.zoxymodz.worldgui.WorldGUI;
import me.zoxymodz.worldgui.manager.InventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TxtCmd implements CommandExecutor {

    private final WorldGUI main;
    public TxtCmd(WorldGUI main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (!(sender instanceof Player)){
            return true;
        }
        Player player = (Player) sender;
        InventoryManager inventoryManager = new InventoryManager(main);
        player.openInventory(inventoryManager.invWorldMenu());
        return false;
    }
}
