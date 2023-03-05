package net.tnemc.menu.minestom;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import net.tnemc.menu.core.icon.Icon;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class MinestomInventory implements PlayerInventory<Inventory> {

  private final UUID id;

  public MinestomInventory(UUID id) {
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
    Inventory inventory = new Inventory(typeFromSize(menu.getSize()), menu.getTitle());

    for(Map.Entry<Integer, Icon> entry : menu.getPages().get(page).getIcons().entrySet()) {

      inventory.setItemStack(entry.getKey(), (ItemStack)entry.getValue().getItem().locale());
    }
    return null;
  }

  /**
   * Used to open the provided inventory for this player.
   *
   * @param inventory The inventory to open.
   */
  @Override
  public void openInventory(Inventory inventory) {
    Optional<Player> player = Optional.ofNullable(MinecraftServer.getConnectionManager().getPlayer(id));

    player.ifPresent(value->value.openInventory(inventory));
  }

  /**
   * Used to update the menu the player is in with a new item for a specific slot.
   *
   * @param slot The slot to update.
   * @param item The item to update the specified slot with.
   */
  @Override
  public void updateInventory(int slot, AbstractItemStack<?> item) {
    Optional<Player> player = Optional.ofNullable(MinecraftServer.getConnectionManager().getPlayer(id));

    player.ifPresent(value->value.getInventory().setItemStack(slot, (ItemStack)item.locale()));

  }

  private InventoryType typeFromSize(final int size) {
    return switch(size) {
      case 1 -> InventoryType.CHEST_1_ROW;
      case 2 -> InventoryType.CHEST_2_ROW;
      case 3 -> InventoryType.CHEST_3_ROW;
      case 4 -> InventoryType.CHEST_4_ROW;
      case 5 -> InventoryType.CHEST_5_ROW;
      default -> InventoryType.CHEST_6_ROW;
    };
  }
}