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
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.viewer.CoreStatus;
import net.tnemc.menu.core.viewer.MenuViewer;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class BukkitInventoryCloseListener implements Listener {

  private final JavaPlugin plugin;

  public BukkitInventoryCloseListener(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onClose(final InventoryCloseEvent event) {

    final BukkitPlayer player = new BukkitPlayer((OfflinePlayer)event.getPlayer(), plugin);
    final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(player.identifier());
    System.out.println("OnClose Viewer: " + viewer.isPresent());
    if(viewer.isPresent()) {
      System.out.println("OnClose Status: " + viewer.get().status().toString());

      if(viewer.get().status().closeMenu()) {
        System.out.println("OnClose Viewer: Close");

        viewer.get().close(player);
        return;
      }

      if(viewer.get().status().changing()) {
        System.out.println("OnClose Viewer: Changing");
        MenuManager.instance().updateViewer(player.identifier(), CoreStatus.IN_MENU);
      }
    }
  }
}
