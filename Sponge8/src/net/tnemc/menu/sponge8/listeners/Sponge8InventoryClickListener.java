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

import net.tnemc.menu.core.icon.action.ActionType;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.viewer.MenuViewer;
import net.tnemc.menu.sponge8.SpongePlayer;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.container.ClickContainerEvent;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.plugin.PluginContainer;

import java.util.Optional;

public class Sponge8InventoryClickListener {

  private final PluginContainer container;

  public Sponge8InventoryClickListener(PluginContainer container) {
    this.container = container;
  }

  @Listener
  public void onDouble(ClickContainerEvent event, @First ServerPlayer player) {
    final SpongePlayer sPlayer = new SpongePlayer(player.user(), container);

    final Optional<MenuViewer> data = MenuManager.instance().findViewer(sPlayer.identifier());
    final Optional<Slot> slot = event.slot();
    if(slot.isPresent() && sPlayer.inventory().inMenu() && data.isPresent()) {

      final int slotIndex = slot.get().getInt(Keys.SLOT_INDEX).orElse(-1);

      if(slotIndex > -1) {
        /*final boolean cancel = new InventoryClickHandler().handle(convertClick(event),
                                                                  sPlayer, slotIndex);

        if(cancel) {
          event.setCancelled(true);
        }*/
      }
    }
  }



  private ActionType convertClick(ClickContainerEvent event) {
    if (event instanceof ClickContainerEvent.Shift.Primary) {
      return ActionType.LEFT_SHIFT;
    } else if(event instanceof ClickContainerEvent.Shift.Secondary) {
      return ActionType.RIGHT_SHIFT;
    } else if(event instanceof ClickContainerEvent.Secondary) {
      return ActionType.RIGHT_CLICK;
    } else if(event instanceof ClickContainerEvent.Middle) {
      return ActionType.SCROLL_CLICK;
    } else if(event instanceof ClickContainerEvent.Double) {
      return ActionType.DOUBLE_CLICK;
    } else if(event instanceof ClickContainerEvent.Drop.Full) {
      return ActionType.DROP_CTRL;
    } else if(event instanceof ClickContainerEvent.Drop) {
      return ActionType.DROP;
    } else if(event instanceof ClickContainerEvent.NumberPress) {
      return ActionType.NUMBER;
    } else {
      return ActionType.LEFT_CLICK;
    }
  }
}