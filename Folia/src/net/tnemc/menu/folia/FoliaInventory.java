package net.tnemc.menu.folia;

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.paper.PaperInventory;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class FoliaInventory extends PaperInventory {

  public FoliaInventory(final UUID id, final JavaPlugin plugin) {

    super(id, plugin);
  }

  /**
   * Used to open the provided inventory for this player.
   *
   * @param inventory The inventory to open.
   */
  @Override
  public void openInventory(final Inventory inventory) {

    final OfflinePlayer player = Bukkit.getOfflinePlayer(player());
    if(player.getPlayer() != null) {
      Bukkit.getGlobalRegionScheduler().run(plugin, (scheduledTask)->{
        player.getPlayer().openInventory(inventory);
      });
    }
  }

  /**
   * Used to update the inventory the player is in with a new item for a specific slot.
   *
   * @param slot The slot to update.
   * @param item The item to update the specified slot with.
   */
  @Override
  public void updateInventory(final int slot, final AbstractItemStack<?> item) {

    final OfflinePlayer player = Bukkit.getOfflinePlayer(player());
    if(player.getPlayer() != null) {
      Bukkit.getGlobalRegionScheduler().run(plugin, (scheduledTask)->{
        player.getPlayer().getOpenInventory().setItem(slot, (ItemStack)item.locale());
      });
    }
  }

  /**
   * Used to close the player's currently open inventory.
   */
  @Override
  public void close() {

    final OfflinePlayer player = Bukkit.getOfflinePlayer(player());
    if(player.getPlayer() != null) {
      Bukkit.getGlobalRegionScheduler().run(plugin, (scheduledTask)->{
        player.getPlayer().closeInventory();
      });
    }
  }
}
