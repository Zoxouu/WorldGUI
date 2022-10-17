package me.zoxymodz.worldgui.manager;

import me.zoxymodz.worldgui.WorldGUI;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.omg.SendingContext.RunTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class WorldManager {

    public WorldGUI main;

    public WorldManager(WorldGUI main){
        this.main = main;
    }

    public void importWorld() {
        File dir = new File("./");
        File[] liste = dir.listFiles();
        for (File item : liste) {
            if (item.isDirectory() && !item.getName().equals("logs") && !item.getName().equals("plugins")){
                String worldName = item.getName();
                Bukkit.getServer().createWorld(new WorldCreator(worldName));
                System.out.println("The World : "+ worldName +" has been loaded");
            }
        }
    }

    public void deleteWorld(String world, Player player) throws IOException {
        System.out.println("the world "+ world +" will be unloaded...");
        Bukkit.getServer().unloadWorld(Bukkit.getWorld(world),false);
        System.out.println("The world has been unloaded");
        File path = Paths.get(Paths.get("").toAbsolutePath().toString(), world).toFile();
        if (path.exists()) {
            if (path.isDirectory()) {
                System.out.println("deletion of the current world...");
                ProcessBuilder builder = new ProcessBuilder();
                if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
                    builder.command("rmdir",path.toString());
                } else {
                    builder.command("rm","-r",path.toString());
                }
                builder.start();
                System.out.println("The world "+ world +" has been successfully deleted");
                player.sendMessage(main.getPrefix() +"§eThe world §a"+ world +" §ehas been successfully deleted");
            }
        }
    }
}