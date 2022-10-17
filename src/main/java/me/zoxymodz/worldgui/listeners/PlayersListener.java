package me.zoxymodz.worldgui.listeners;

import lombok.Getter;
import me.zoxymodz.worldgui.WorldGUI;
import me.zoxymodz.worldgui.cmd.WorldGUICmd;
import me.zoxymodz.worldgui.utils.TextComponentBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.simple.parser.ParseException;

import java.io.*;

public class PlayersListener implements Listener {

    public WorldGUI main;
    public WorldGUICmd worldListCommand;
    @Getter private File folder;

    public PlayersListener(WorldGUI main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException, ParseException {
        Player player = e.getPlayer();
        World world = player.getWorld();
        switch (world.getEnvironment()){
            case THE_END:
                TextComponent endtxt = new TextComponent(main.getPrefix() + "§eyou are in the world §b"+ e.getPlayer().getWorld().getName() +" §eclick ");
                endtxt.addExtra(new TextComponentBuilder("§ahere").setClickAction(ClickEvent.Action.RUN_COMMAND,"/txtcmd").build());
                endtxt.addExtra(new TextComponentBuilder(" §eto see the list.").build());
                player.spigot().sendMessage(endtxt);
                break;
            case NETHER:
                TextComponent nethertxt = new TextComponent(main.getPrefix() + "§eyou are in the world §c"+ e.getPlayer().getWorld().getName() +" §eclick ");
                nethertxt.addExtra(new TextComponentBuilder("§ahere").setClickAction(ClickEvent.Action.RUN_COMMAND, "/txtcmd").build());
                nethertxt.addExtra(new TextComponentBuilder(" §eto see the list.").build());
                player.spigot().sendMessage(nethertxt);
                break;
            case NORMAL:
                TextComponent normaltxt = new TextComponent(main.getPrefix() + "§eyou are in the world §a"+ e.getPlayer().getWorld().getName()+" §eclick ");
                normaltxt.addExtra(new TextComponentBuilder("§ahere ").setClickAction(ClickEvent.Action.RUN_COMMAND, "/txtcmd").build());
                normaltxt.addExtra(new TextComponentBuilder(" §eto see the list.").build());
                player.spigot().sendMessage(normaltxt);
                break;
        }
    }
    @EventHandler
    public void onLookCmd(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();

    }
}
