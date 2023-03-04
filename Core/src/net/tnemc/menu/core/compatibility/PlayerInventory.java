package net.tnemc.menu.core.compatibility;

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.MenuManager;

import java.util.Optional;
import java.util.UUID;

/**
 * A class that acts as a bridge between various inventory objects on different server software providers.
 *
 * @param <INV> Represents the platform's Inventory object.
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface PlayerInventory<INV> {

  /**
   * The player associated with this inventory provider.
   * @return The {@link UUID} for the player for this {@link PlayerInventory}
   */
  UUID player();

  /**
   * Builds an inventory object from a menu.
   *
   * @param menu The menu to build.
   *
   * @return The built inventory.
   */
  default INV build(final Menu menu) {
    return build(menu, 1);
  }

  /**
   * Builds an inventory object from a menu.
   *
   * @param menu The menu to build.
   * @param page The page to use during the build.
   *
   * @return The built inventory.
   */
  INV build(final Menu menu, int page);

  /**
   * Used to open the provided inventory for this player.
   *
   * @param inventory The inventory to open.
   */
  void openInventory(final INV inventory);

  /**
   * Used to determine if this player is inside of a {@link Menu}
   *
   * @return True if this player is inside the specified menu, otherwise false.
   */
  default boolean inMenu() {
    return MenuManager.instance().inMenu(player());
  }

  /**
   * Used to open the provided menu for this player.
   * @param menu The menu to open.
   */
  default void openMenu(final Menu menu) {
    openMenu(menu, 1);
  }

  /**
   * Used to open the provided menu for this player on the specified page.
   * @param menu The menu to open.
   * @param page The page to open.
   */
  default void openMenu(final Menu menu, final int page) {

    openInventory(build(menu, page));
    MenuManager.instance().updateViewer(player(), menu.getName(), page);
  }

  /**
   * Used to open the provided menu for this player.
   * @param menu The menu to open.
   */
  default void openMenu(final String menu) {
    openMenu(menu, 1);
  }

  /**
   * Used to open the provided menu for this player on the specified page.
   * @param menu The menu to open.
   * @param page The page to open.
   */
  default void openMenu(final String menu, final int page) {

    final Optional<Menu> menuObj = MenuManager.instance().getMenu(menu);

    if(menuObj.isPresent()) {

      openInventory(build(menuObj.get(), page));
      MenuManager.instance().updateViewer(player(), menu, page);
    }
  }

  /**
   * Used to update the menu the player is in with a new item for a specific slot.
   *
   * @param slot The slot to update.
   * @param item The item to update the specified slot with.
   */
  void updateMenu(int slot, AbstractItemStack<?> item);
}