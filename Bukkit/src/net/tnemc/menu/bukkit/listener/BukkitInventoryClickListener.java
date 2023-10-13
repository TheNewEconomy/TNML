package net.tnemc.menu.bukkit.listener;

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

import net.tnemc.menu.bukkit.BukkitPlayer;
import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.compatibility.InventoryClickHandler;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.viewer.ViewerData;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class BukkitInventoryClickListener implements Listener {

  private final JavaPlugin plugin;

  public BukkitInventoryClickListener(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onClick(final InventoryClickEvent event) {
    final BukkitPlayer player = new BukkitPlayer((OfflinePlayer)event.getWhoClicked(), plugin);

    final Optional<ViewerData> data = MenuManager.instance().getViewer(player.identifier());
    if(player.inventory().inMenu() && data.isPresent()) {

      final boolean cancel = new InventoryClickHandler().handle(convertClick(event.getClick()),
                                                                player, event.getSlot()
      );

      if(cancel) {
        event.setCancelled(true);
      }
    }
  }

  private ActionType convertClick(ClickType click) {
    ActionType type = ActionType.LEFT_CLICK;

    switch(click) {
      case SHIFT_LEFT:
        type = ActionType.LEFT_SHIFT;
        break;
      case RIGHT:
        type = ActionType.RIGHT_CLICK;
        break;
      case SHIFT_RIGHT:
        type = ActionType.RIGHT_SHIFT;
        break;
      case MIDDLE:
        type = ActionType.SCROLL_CLICK;
        break;
      case DOUBLE_CLICK:
        type = ActionType.DOUBLE_CLICK;
        break;
      case DROP:
        type = ActionType.DROP;
        break;
      case CONTROL_DROP:
        type = ActionType.DROP_CTRL;
        break;
      case SWAP_OFFHAND:
        type = ActionType.OFFHAND_SWAP;
        break;
      case NUMBER_KEY:
        type = ActionType.NUMBER;
        break;
      default:
    }


    return type;
  }
}