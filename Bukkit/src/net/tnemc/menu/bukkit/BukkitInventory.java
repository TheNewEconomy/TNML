package net.tnemc.menu.bukkit;

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import net.tnemc.menu.core.icon.Icon;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public class BukkitInventory implements PlayerInventory<Inventory> {

  private final UUID id;

  public BukkitInventory(UUID id) {
    this.id = id;
  }

  /**
   * The player associated with this inventory provider.
   *
   * @return The {@link UUID} for the player for this {@link PlayerInventory}
   */
  @Override
  public UUID player() {
    return id;
  }

  /**
   * Builds an inventory object from a menu.
   *
   * @param menu The menu to build.
   * @param page The page to use during the build.
   *
   * @return The built inventory.
   */
  @Override
  public Inventory build(Menu menu, int page) {
    Inventory inventory = Bukkit.createInventory(null, menu.getSize(), menu.getTitle());

    for(Map.Entry<Integer, Icon> entry : menu.getPages().get(page).getIcons().entrySet()) {

      inventory.setItem(entry.getKey(), (ItemStack)entry.getValue().getItem().locale());
    }

    return inventory;
  }

  /**
   * Used to open the provided inventory for this player.
   *
   * @param inventory The inventory to open.
   */
  @Override
  public void openInventory(Inventory inventory) {
    final OfflinePlayer player = Bukkit.getOfflinePlayer(player());
    if(player.getPlayer() != null) {
      player.getPlayer().openInventory(inventory);
    }
  }

  /**
   * Used to update the inventory the player is in with a new item for a specific slot.
   *
   * @param slot The slot to update.
   * @param item The item to update the specified slot with.
   */
  @Override
  public void updateInventory(int slot, AbstractItemStack<?> item) {
    final OfflinePlayer player = Bukkit.getOfflinePlayer(player());
    if(player.getPlayer() != null) {
      player.getPlayer().getInventory().setItem(slot, (ItemStack)item.locale());
    }
  }
}
