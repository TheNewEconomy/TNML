package net.tnemc.menu.paper.listener;

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

import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.action.ActionType;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.utils.SlotPos;
import net.tnemc.menu.core.viewer.MenuViewer;
import net.tnemc.menu.paper.PaperPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.UUID;

public class PaperInventoryClickListener implements Listener {

  private final JavaPlugin plugin;

  public PaperInventoryClickListener(final JavaPlugin plugin) {

    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onClick(final InventoryClickEvent event) {

    if(isNoGrab(event.getCurrentItem(), plugin)) {
      event.setCancelled(true);
    }

    final UUID id = event.getWhoClicked().getUniqueId();

    final PaperPlayer player = new PaperPlayer((OfflinePlayer)event.getWhoClicked(), plugin);

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

    if(MenuManager.instance().recentlyClosed().containsKey(id)) {

      final Long time = System.currentTimeMillis();
      final Long closedTime = MenuManager.instance().recentlyClosed().get(id);

      if(time - closedTime < 6000) {

        event.setCancelled(true);
      } else {
        MenuManager.instance().recentlyClosed().remove(id);
      }
    }
  }

  private boolean isNoGrab(final ItemStack item, final JavaPlugin plugin) {
    if (item == null || !item.hasItemMeta()) return false;

    final ItemMeta meta = item.getItemMeta();
    if (meta == null) return false;

    final NamespacedKey key = new NamespacedKey(plugin, "no-grab");
    return meta.getPersistentDataContainer().has(key, PersistentDataType.STRING);
  }

  private ActionType convertClick(final ClickType click) {

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