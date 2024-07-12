package net.tnemc.menu.paper;

import net.tnemc.menu.bukkit.BukkitInventory;
import net.tnemc.menu.bukkit.BukkitPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

public class PaperPlayer extends BukkitPlayer {
  public PaperPlayer(OfflinePlayer player, JavaPlugin plugin) {
    super(player, plugin);
  }

  @Override
  public BukkitInventory inventory() {
    return new BukkitInventory(identifier(), plugin);
  }
}