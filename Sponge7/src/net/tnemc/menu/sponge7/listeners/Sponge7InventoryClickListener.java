package net.tnemc.menu.sponge7.listeners;

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
import net.tnemc.menu.sponge7.SpongePlayer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.plugin.Plugin;

import java.util.Optional;

public class Sponge7InventoryClickListener {

  private final Plugin plugin;

  public Sponge7InventoryClickListener(Plugin plugin) {
    this.plugin = plugin;
  }

  @Listener
  public void onDouble(ClickInventoryEvent event, @First Player player) {
    final SpongePlayer sPlayer = new SpongePlayer(player, plugin);

    final Optional<MenuViewer> data = MenuManager.instance().findViewer(sPlayer.identifier());
    final Optional<Slot> slot = event.getSlot();
    if(data.isPresent()) {

      final Optional<Menu> menu = MenuManager.instance().findMenu(data.get().menu());
      if(menu.isPresent() && slot.isPresent()) {

        final Optional<SlotIndex> property = slot.get().getInventoryProperty(SlotIndex.class);

        if(property.isPresent()) {
          final boolean cancel = menu.get().onClick(new MenuClickHandler(new SlotPos(property.get().getValue()),
                  sPlayer, menu.get(), data.get().page(),
                  convertClick(event)));

          if(cancel) {
            event.setCancelled(true);
          }
        }
      }
    }

    /*final Optional<ViewerData> data = MenuManager.instance().getViewer(sPlayer.identifier());
    final Optional<Slot> slot = event.getSlot();
    if(slot.isPresent() && sPlayer.inventory().inMenu() && data.isPresent()) {

      final Optional<SlotIndex> property = slot.get().getInventoryProperty(SlotIndex.class);

      if(property.isPresent()) {
        final boolean cancel = new InventoryClickHandler().handle(convertClick(event),
                sPlayer, property.get().getValue());

        if(cancel) {
          event.setCancelled(true);
        }
      }
    }*/
  }



  private ActionType convertClick(ClickInventoryEvent event) {
    if (event instanceof ClickInventoryEvent.Shift.Primary) {
      return ActionType.LEFT_SHIFT;
    } else if(event instanceof ClickInventoryEvent.Shift.Secondary) {
      return ActionType.RIGHT_SHIFT;
    } else if(event instanceof ClickInventoryEvent.Secondary) {
      return ActionType.RIGHT_CLICK;
    } else if(event instanceof ClickInventoryEvent.Middle) {
      return ActionType.SCROLL_CLICK;
    } else if(event instanceof ClickInventoryEvent.Double) {
      return ActionType.DOUBLE_CLICK;
    } else if(event instanceof ClickInventoryEvent.Drop.Full) {
      return ActionType.DROP_CTRL;
    } else if(event instanceof ClickInventoryEvent.Drop) {
      return ActionType.DROP;
    } else if(event instanceof ClickInventoryEvent.NumberPress) {
      return ActionType.NUMBER;
    } else {
      return ActionType.LEFT_CLICK;
    }
  }
}