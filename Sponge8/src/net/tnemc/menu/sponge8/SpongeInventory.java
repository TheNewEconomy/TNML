package net.tnemc.menu.sponge8;

import net.kyori.adventure.text.Component;
import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import net.tnemc.menu.core.icon.Icon;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.item.inventory.ContainerTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.menu.InventoryMenu;
import org.spongepowered.api.item.inventory.type.ViewableInventory;

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
  public ViewableInventory build(Menu menu, int page) {
    ViewableInventory inventory = ViewableInventory.builder().type(ContainerTypes.GENERIC_9X6).completeStructure().build();

    //TODO: convert container type.

    for(Map.Entry<Integer, Icon> entry : menu.getPages().get(page).getIcons().entrySet()) {

      inventory.offer(entry.getKey(), (ItemStack)entry.getValue().getItem().locale());
    }

    return inventory;
  }

  /**
   * Used to open the provided menu for this player on the specified page.
   *
   * @param menu The menu to open.
   * @param page The page to open.
   */
  @Override
  public void openMenu(Menu menu, int page) {

    InventoryMenu invMenu = InventoryMenu.of(build(menu, page));
    invMenu.setTitle(Component.text(menu.getTitle()));
    //invMenu.registerClick();
    //invMenu.registerClose();

    final Optional<ServerPlayer> player = Sponge.server().player(id);
    if(player.isPresent()) {
      invMenu.open(player.get());
    }


    openInventory(build(menu, page));


    MenuManager.instance().updateViewer(player(), menu.getName(), page);
  }

  /**
   * Used to open the provided inventory for this player.
   *
   * @param inventory The inventory to open.
   */
  @Override
  public void openInventory(Inventory inventory) {
    final Optional<ServerPlayer> player = Sponge.server().player(id);

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
    final Optional<ServerPlayer> player = Sponge.server().player(id);

    player.ifPresent(serverPlayer->serverPlayer.inventory().offer(slot, (ItemStack)item.locale()));
  }
}
