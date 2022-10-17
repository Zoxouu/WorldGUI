package me.zoxymodz.worldgui.utils;

import org.bukkit.inventory.ItemStack;

public enum HeadList {

    END("OTY2MzZiYTY5ODhjZTliNDBkZGM3NDlhMDljZTBmYjkzOWFmNTI2MDA1OTk1YzE4ZDMyM2FjOTY2MjVmMGQ2ZCJ9fX0=","end"),
    NETHER("ZDUwMDI5MmY0YWZlNTJkMTBmMjk5ZGZiMjYwMzYzMjI4MzA0NTAzMzFlMDAzMDg0YmIyMjAzMzM1MzA2NjRlMSJ9fX0=","nether"),
    WORLD("ODc5ZTU0Y2JlODc4NjdkMTRiMmZiZGYzZjE4NzA4OTQzNTIwNDhkZmVjZDk2Mjg0NmRlYTg5M2IyMTU0Yzg1In19fQ==", "world"),
    ADD("NjMyZmZmMTYzZTIzNTYzMmY0MDQ3ZjQ4NDE1OTJkNDZmODVjYmJmZGU4OWZjM2RmNjg3NzFiZmY2OWE2NjIifX19","add"),
    DELETE("ZGE2M2IwZjU2ZjdlYzY0ZWFjYmI3MWZjYTMxNTQ5ZDAyMjc0MGQ5YjdkNGI2MTc2MmEyZWZlNTg0MWE0YmYyNSJ9fX0=", "delete");

    private final String prefix = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";

    private final ItemStack item;

    private final String idTag;

    HeadList(String texture, String id) {
        this.item = CreateSkull.createSkull(this.prefix + texture, id);
        this.idTag = id;
    }

    public ItemStack getItemStack() {
        return this.item;
    }

    public String getName() {
        return this.idTag;
    }
}