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

import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.viewer.ViewerData;

import java.util.Optional;

/**
 * InventoryClickHandler represents a click event that happens within an inventory when a player is
 * inside a menu.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class InventoryClickHandler {

  /**
   * Used to handle a click event from an inventory in the platform implementation.
   *
   * @param type The {@link ActionType} performed during the event.
   * @param player The player performing the click action.
   * @param slot The slot that was interacted with.
   * @return True if the corresponding click event should be cancelled, otherwise false.
   */
  public boolean handle(ActionType type, MenuPlayer player, int slot) {


    final Optional<ViewerData> data = MenuManager.instance().getViewer(player.identifier());
    if(data.isPresent()) {
      MenuManager.instance().onClick(data.get().getMenu(), type, player, data.get().getPage(), slot);

      return true;
    }
    return false;
  }
}