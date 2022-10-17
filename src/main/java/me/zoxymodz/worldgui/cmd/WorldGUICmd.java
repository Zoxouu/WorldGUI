package me.zoxymodz.worldgui.cmd;

import me.zoxymodz.worldgui.WorldGUI;
import me.zoxymodz.worldgui.manager.InventoryManager;
import me.zoxymodz.worldgui.manager.WorldManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class WorldGUICmd implements CommandExecutor, Listener {

    public WorldGUI main;

    public WorldGUICmd(WorldGUI main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("worldgui.cmd.worldlist")) {
            player.sendMessage(main.getPrefix() + "§cyou don't have permission !");
            return true;
        }

        WorldManager worldManager = new WorldManager(main);
        worldManager.importWorld();

        InventoryManager inv = new InventoryManager(main);
        player.openInventory(inv.invHomeMenu());

        return false;
    }
}
