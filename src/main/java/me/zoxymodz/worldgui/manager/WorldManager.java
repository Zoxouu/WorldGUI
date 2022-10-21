package me.zoxymodz.worldgui.manager;

import me.zoxymodz.worldgui.WorldGUI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Comparator;

public class WorldManager {

    private static WorldGUI main;

    static {
        main = (WorldGUI) JavaPlugin.getPlugin(WorldGUI.class);
    }

    public static void importWorld() {
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

    public static void deleteWorld(String world, Player player) throws IOException {
        System.out.println("the world "+ world +" will be unloaded...");
        Bukkit.getServer().unloadWorld(Bukkit.getWorld(world),false);
        System.out.println("The world has been unloaded");
        Files.walk(Paths.get("").toAbsolutePath().resolve(world)).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        main.getLogger().info(() -> "The world " + world + " has been successfully deleted.");
        player.sendMessage(main.getPrefix() + "§eThe world §a" + world + " §ehas been successfully deleted.");
    }
}
