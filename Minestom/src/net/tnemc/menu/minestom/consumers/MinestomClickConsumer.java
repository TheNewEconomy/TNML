package net.tnemc.menu.minestom.consumers;

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

import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.click.ClickType;
import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.compatibility.InventoryClickHandler;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.viewer.ViewerData;
import net.tnemc.menu.minestom.MinestomPlayer;

import java.util.Optional;
import java.util.function.Consumer;

public class MinestomClickConsumer implements Consumer<InventoryPreClickEvent> {

  /**
   * Performs this operation on the given argument.
   *
   * @param event the input argument
   */
  @Override
  public void accept(InventoryPreClickEvent event) {
    final MinestomPlayer player = new MinestomPlayer(event.getPlayer());

    final Optional<ViewerData> data = MenuManager.instance().getViewer(player.identifier());
    if(player.inventory().inMenu() && data.isPresent()) {
      final boolean cancel = new InventoryClickHandler().handle(convertClick(event.getClickType()),
                                                                player, event.getSlot());

      if(cancel) {
        event.setCancelled(true);
      }
    }
  }

  private ActionType convertClick(ClickType click) {
    return switch(click) {
      case SHIFT_CLICK -> ActionType.LEFT_SHIFT;
      case RIGHT_CLICK -> ActionType.RIGHT_CLICK;
      case DOUBLE_CLICK -> ActionType.DOUBLE_CLICK;
      case DROP -> ActionType.DROP;
      case CHANGE_HELD -> ActionType.OFFHAND_SWAP;
      default -> ActionType.LEFT_CLICK;
    };
  }
}