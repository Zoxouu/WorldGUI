package me.zoxymodz.worldgui.manager;

import me.zoxymodz.worldgui.WorldGUI;
import me.zoxymodz.worldgui.utils.HeadList;
import me.zoxymodz.worldgui.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.io.IOException;

public class InventoryManager implements Listener {

    private final WorldGUI main;
    public InventoryManager(WorldGUI main){
        this.main = main;
    }

    public Inventory invHomeMenu() {
        Inventory inv = Bukkit.createInventory(null,3*9,"§fWorldGUI");
        inv.setItem(15,new ItemBuilder(HeadList.DELETE.getItemStack()).setName("§cDelete World").toItemStack());
        inv.setItem(13,new ItemBuilder(HeadList.WORLD.getItemStack()).setName("§bWorld List").toItemStack());
        inv.setItem(11,new ItemBuilder(HeadList.ADD.getItemStack()).setName("§aAdd World").toItemStack());
        return inv;
    }
    public Inventory invWorldMenu() {
        Inventory inv = Bukkit.createInventory(null, 3*9, "§7WorldsListe");
        if (Bukkit.getServer().getWorlds().size() > 3*9){
            inv = Bukkit.createInventory(null, 6*9, "§7WorldsListe");
        }
        int slot = 0;
        for (World words : Bukkit.getServer().getWorlds()) {
            switch (words.getEnvironment()) {
                case NETHER:
                    inv.setItem(slot, new ItemBuilder(HeadList.NETHER.getItemStack()).setName(words.getName()).setLore("§6Type§7: §e" + words.getEnvironment().toString(), "§6Difficulty§7: §e" + words.getDifficulty(), "§6Players§7: §e" + words.getPlayers().size()).toItemStack());
                    break;
                case THE_END:
                    inv.setItem(slot, new ItemBuilder(HeadList.END.getItemStack()).setName(words.getName()).setLore("§6Type§7: §e" + words.getEnvironment().toString(), "§6Difficulty§7: §e" + words.getDifficulty(), "§6Players§7: §e"  + words.getPlayers().size()).toItemStack());
                    break;
                case NORMAL:
                    inv.setItem(slot, new ItemBuilder(HeadList.WORLD.getItemStack()).setName(words.getName()).setLore("§6Type§7: §e" + words.getEnvironment().toString(), "§6Difficulty§7: §e" + words.getDifficulty(), "§6Players§7: §e" + words.getPlayers().size()).toItemStack());
                    break;
                default:
                    break;
            }
            slot++;
        }
        return inv;
    }

    public Inventory invDeleteWorld() {
        Inventory inv = Bukkit.createInventory(null, 3*9, "§7Worlds Delete");
        if (Bukkit.getServer().getWorlds().size() > 3*9){
            inv = Bukkit.createInventory(null, 6*9, "§7Worlds Delete");
        }
        int slot = 0;
        for (World words : Bukkit.getServer().getWorlds()) {
            switch (words.getEnvironment()) {
                case NETHER:
                    inv.setItem(slot, new ItemBuilder(HeadList.NETHER.getItemStack()).setName(words.getName()).setLore("§6Type§7: §e" + words.getEnvironment().toString(), "§6Difficulty§7: §e" + words.getDifficulty(), "§6Players§7: §e" + words.getPlayers().size()).toItemStack());
                    break;
                case THE_END:
                    inv.setItem(slot, new ItemBuilder(HeadList.END.getItemStack()).setName(words.getName()).setLore("§6Type§7: §e" + words.getEnvironment().toString(), "§6Difficulty§7: §e" + words.getDifficulty(), "§6Players§7: §e"  + words.getPlayers().size()).toItemStack());
                    break;
                case NORMAL:
                    inv.setItem(slot, new ItemBuilder(HeadList.WORLD.getItemStack()).setName(words.getName()).setLore("§6Type§7: §e" + words.getEnvironment().toString(), "§6Difficulty§7: §e" + words.getDifficulty(), "§6Players§7: §e" + words.getPlayers().size()).toItemStack());
                    break;
                default:
                    break;
            }
            slot++;
        }
        return inv;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) throws IOException {
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getInventory() == null) return;
        final Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        if (inv.getName().equals(invHomeMenu().getName())) {
            event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()){
                case "§cDelete World":
                    if (!player.hasPermission("worldgui.delete")) {
                        player.sendMessage(main.getPrefix() +"§cYou don't have permission");
                        return;
                    }
                    player.openInventory(invDeleteWorld());
                    break;
                case "§bWorld List":
                    player.openInventory(invWorldMenu());
                    break;
                case "§aAdd World":
                    System.out.println(event.getCurrentItem().getItemMeta().getDisplayName());
                    break;
                default:
                    break;
            }
            return;
        }
        if (inv.getName().equals(invWorldMenu().getName())) {
            String world = event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
            event.setCancelled(true);
            player.teleport(Bukkit.getServer().getWorld(world).getSpawnLocation());
            player.closeInventory();
            return;
        }
        if (inv.getName().equals(invDeleteWorld().getName())){
            event.setCancelled(true);
            WorldManager.deleteWorld(event.getCurrentItem().getItemMeta().getDisplayName(),player);
            player.sendMessage(main.getPrefix() +"§eThe world §a"+ event.getCurrentItem().getItemMeta().getDisplayName() +" §ehas been successfully deleted");
            player.closeInventory();
        }
    }
}
