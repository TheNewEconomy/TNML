package net.tnemc.menu.folia;

import net.tnemc.menu.bukkit.BukkitInventory;
import net.tnemc.menu.bukkit.BukkitPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

public class FoliaPlayer extends BukkitPlayer {
  public FoliaPlayer(OfflinePlayer player, JavaPlugin plugin) {
    super(player, plugin);
  }

  @Override
  public BukkitInventory inventory() {
    return new FoliaInventory(identifier(), plugin);
  }
}