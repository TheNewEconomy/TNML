package net.tnemc.menu.sponge8;

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import net.tnemc.menu.core.icon.Icon;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SpongeInventory implements PlayerInventory<Inventory> {

  private final UUID id;

  public SpongeInventory(UUID id) {
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
    Inventory inventory = Inventory.builder().slots(menu.getSize() * 9).build();

    //TODO: inventory title? where did it go?

    for(Map.Entry<Integer, Icon> entry : menu.getPages().get(page).getIcons().entrySet()) {

      final int y = entry.getKey() / 9;
      final int x = (entry.getKey() % 9);


      //TODO: set item to slot sponge 8 nuked everything.
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
    final Optional<Player> player = Sponge.getServer().getPlayer(id);

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
    final Optional<Player> player = Sponge.getServer().getPlayer(id);
    if(player.isPresent()) {

      final int y = slot / 9;
      final int x = (slot % 9);

      player.get().getInventory().query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(x, y)))
          .set((ItemStack)item.locale());
    }
  }
}
