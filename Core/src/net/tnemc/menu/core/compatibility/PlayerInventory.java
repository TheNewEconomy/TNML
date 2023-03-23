package net.tnemc.menu.core.compatibility;

/*
 * The New Menu Library
 *
 * Copyright (C) 2022 - 2023 Daniel "creatorfromhell" Vidmar
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.viewer.ViewerData;

import java.util.Optional;
import java.util.UUID;

/**
 * A class that acts as a bridge between various inventory objects on different server software providers.
 *
 * @param <I> Represents the platform's Inventory object.
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface PlayerInventory<I> {

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
  default I build(final MenuPlayer player, final Menu menu) {
    return build(player, menu, 1);
  }

  /**
   * Builds an inventory object from a menu.
   *
   * @param menu The menu to build.
   * @param page The page to use during the build.
   *
   * @return The built inventory.
   */
  I build(final MenuPlayer player, final Menu menu, int page);

  /**
   * Used to open the provided inventory for this player.
   *
   * @param inventory The inventory to open.
   */
  void openInventory(final I inventory);

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
   * @param player The menu player.
   * @param menu The menu to open.
   */
  default void openMenu(final MenuPlayer player, final Menu menu) {
    openMenu(player, menu, 1);
  }

  /**
   * Used to open the provided menu for this player on the specified page.
   * @param player The menu player.
   * @param menu The menu to open.
   * @param page The page to open.
   */
  default void openMenu(final MenuPlayer player, final Menu menu, final int page) {

    MenuManager.instance().switchViewer(player.identifier(), true);

    openInventory(build(player, menu, page));

    MenuManager.instance().switchViewer(player.identifier(), false);

    MenuManager.instance().updateViewer(player(), menu.getName(), page);
  }

  /**
   * Used to open the provided menu for this player.
   * @param player The menu player.
   * @param menu The menu to open.
   */
  default void openMenu(final MenuPlayer player, final String menu) {
    openMenu(player, menu, 1);
  }

  /**
   * Used to open the provided menu for this player on the specified page.
   * @param player The menu player.
   * @param menu The menu to open.
   * @param page The page to open.
   */
  default void openMenu(final MenuPlayer player, final String menu, final int page) {

    final Optional<Menu> menuObj = MenuManager.instance().getMenu(menu);

    if(menuObj.isPresent()) {
      openMenu(player, menuObj.get(), page);
    }
  }

  /**
   * Used to update the menu the player is in with a new item for a specific slot.
   *
   * @param slot The slot to update.
   * @param item The item to update the specified slot with.
   */
  void updateInventory(int slot, AbstractItemStack<?> item);

  /**
   * Used to close the player's currently open inventory.
   */
  void close();
}