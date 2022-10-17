package me.zoxymodz.worldgui;

import me.zoxymodz.worldgui.Listeners.PlayersListener;
import me.zoxymodz.worldgui.cmd.TxtCmd;
import me.zoxymodz.worldgui.cmd.WorldGUICmd;
import me.zoxymodz.worldgui.manager.InventoryManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldGUI extends JavaPlugin {

    public WorldGUI instance;

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new PlayersListener(this),this);
        getServer().getPluginManager().registerEvents(new InventoryManager(this),this);
        getCommand("worldgui").setExecutor(new WorldGUICmd(this));
        getCommand("txtcmd").setExecutor(new TxtCmd(this));
    }

    public String getPrefix(){
        return "§aWorld§eGUI §7-> ";
    }

    public WorldGUI getInstance() {
        return instance;
    }
}
