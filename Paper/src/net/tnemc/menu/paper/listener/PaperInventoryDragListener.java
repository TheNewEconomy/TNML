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
import net.tnemc.menu.core.Page;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.viewer.MenuViewer;
import net.tnemc.menu.paper.PaperPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class PaperInventoryDragListener implements Listener {

  private final JavaPlugin plugin;

  public PaperInventoryDragListener(final JavaPlugin plugin) {

    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onDrag(final InventoryDragEvent event) {

    final PaperPlayer player = new PaperPlayer((OfflinePlayer)event.getWhoClicked(), plugin);

    final Optional<MenuViewer> data = MenuManager.instance().findViewer(player.identifier());
    if(data.isPresent()) {

      final Optional<Menu> menu = MenuManager.instance().findMenu(data.get().menu());
      if(menu.isPresent()) {

        final Page page = menu.get().getPages().get(data.get().page());
        if(page != null && page.isLockEmptySlots()) {

          for(final int slot : event.getRawSlots()) {
            if(slot < menu.get().maxSlot()) {

              event.setCancelled(true);
              break;
            }
          }
        }
      }
    }
  }
}
