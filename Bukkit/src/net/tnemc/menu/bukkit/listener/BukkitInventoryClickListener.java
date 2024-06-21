package net.tnemc.menu.bukkit.listener;

/*
 * The New Menu Library
 *
 * Copyright (C) 2022 - 2024 Daniel "creatorfromhell" Vidmar
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
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.action.ActionType;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.utils.SlotPos;
import net.tnemc.menu.core.viewer.MenuViewer;
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

    final Optional<MenuViewer> data = MenuManager.instance().findViewer(player.identifier());
    if(data.isPresent()) {

      final Optional<Menu> menu = MenuManager.instance().findMenu(data.get().menu());
      if(menu.isPresent()) {

        final boolean cancel = menu.get().onClick(new MenuClickHandler(new SlotPos(event.getRawSlot()),
                                                                       player, menu.get(), data.get().page(),
                                                                       convertClick(event.getClick())));

        if(cancel) {
          event.setCancelled(true);
        }
      }
    }
  }

  private ActionType convertClick(ClickType click) {
    return switch(click) {
      case SHIFT_LEFT -> ActionType.LEFT_SHIFT;
      case RIGHT -> ActionType.RIGHT_CLICK;
      case SHIFT_RIGHT -> ActionType.RIGHT_SHIFT;
      case MIDDLE -> ActionType.SCROLL_CLICK;
      case DOUBLE_CLICK -> ActionType.DOUBLE_CLICK;
      case DROP -> ActionType.DROP;
      case CONTROL_DROP -> ActionType.DROP_CTRL;
      case SWAP_OFFHAND -> ActionType.OFFHAND_SWAP;
      case NUMBER_KEY -> ActionType.NUMBER;
      default -> ActionType.LEFT_CLICK;
    };
  }
}