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
import net.tnemc.menu.core.utils.CloseType;
import net.tnemc.menu.core.viewer.ViewerData;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Optional;

public class BukkitInventoryCloseListener implements Listener {

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onClick(final InventoryCloseEvent event) {
    final BukkitPlayer player = new BukkitPlayer((OfflinePlayer)event.getPlayer());

    final Optional<ViewerData> data = MenuManager.instance().getViewer(player.identifier());
    if(data.isPresent()) {

      MenuManager.instance().onClose(data.get().getMenu(), player, data.get().getPage(), CloseType.CLOSE);
    }
  }
}
