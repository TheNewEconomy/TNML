package net.tnemc.menu.sponge8.listeners;

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

import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.viewer.CoreStatus;
import net.tnemc.menu.core.viewer.MenuViewer;
import net.tnemc.menu.sponge8.SpongePlayer;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.plugin.PluginContainer;

import java.util.Optional;


public class Sponge8InteractInventoryListener {

  private final PluginContainer container;

  public Sponge8InteractInventoryListener(PluginContainer container) {
    this.container = container;
  }

  @Listener
  public void onClose(ChangeInventoryEvent event, @First ServerPlayer player) {

    final SpongePlayer sPlayer = new SpongePlayer(player.user(), container);
    final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(sPlayer.identifier());
    if(viewer.isPresent()) {

      if(viewer.get().status().closeMenu()) {

        viewer.get().close(sPlayer);
        return;
      }

      if(viewer.get().status().changing()) {
        MenuManager.instance().updateViewer(sPlayer.identifier(), CoreStatus.IN_MENU);
      }
    }
  }
}
