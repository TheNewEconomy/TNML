package net.tnemc.menu.folia;

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.paper.PaperInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

    final Player player = Bukkit.getPlayer(player());
    if(player != null) {
      player.getScheduler().run(plugin, task->{
        player.openInventory(inventory);
      }, null);
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

    final Player player = Bukkit.getPlayer(player());
    if(player != null) {
      player.getScheduler().run(plugin, task->{
        final ItemStack stack = (ItemStack)item.cacheLocale();
        setNoGrab(stack, plugin);
        player.getOpenInventory().setItem(slot, stack);
      }, null);
    }
  }

  /**
   * Used to close the player's currently open inventory.
   */
  @Override
  public void close() {

    final Player player = Bukkit.getPlayer(player());
    if(player != null) {
      player.getScheduler().run(plugin, task->{
        player.closeInventory();
      }, null);
    }
  }
}
