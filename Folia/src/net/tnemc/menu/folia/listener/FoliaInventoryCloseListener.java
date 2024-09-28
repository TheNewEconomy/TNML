package net.tnemc.menu.folia.listener;

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

import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.viewer.CoreStatus;
import net.tnemc.menu.core.viewer.MenuViewer;
import net.tnemc.menu.folia.FoliaPlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class FoliaInventoryCloseListener implements Listener {

  private final JavaPlugin plugin;

  public FoliaInventoryCloseListener(final JavaPlugin plugin) {

    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onClose(final InventoryCloseEvent event) {

    final FoliaPlayer player = new FoliaPlayer((OfflinePlayer)event.getPlayer(), plugin);
    final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(player.identifier());

    if(viewer.isPresent()) {

      if(viewer.get().status().closeMenu()) {
        viewer.get().close(player);
        return;
      }

      if(viewer.get().status().changing()) {
        MenuManager.instance().updateViewer(player.identifier(), CoreStatus.IN_MENU);
      }
    }
  }
}
