package net.tnemc.menu.folia;

import net.tnemc.menu.paper.PaperInventory;
import net.tnemc.menu.paper.PaperPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

public class FoliaPlayer extends PaperPlayer {
  public FoliaPlayer(OfflinePlayer player, JavaPlugin plugin) {
    super(player, plugin);
  }

  @Override
  public PaperInventory inventory() {
    return new FoliaInventory(identifier(), plugin);
  }
}