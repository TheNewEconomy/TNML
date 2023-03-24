package net.tnemc.menu.bukkit.listener;

import net.tnemc.menu.bukkit.BukkitPlayer;
import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.callbacks.player.PlayerChatCallback;
import net.tnemc.menu.core.viewer.ViewerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

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
public class BukkitChatListener implements Listener {

  private final JavaPlugin plugin;

  public BukkitChatListener(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onChat(final AsyncPlayerChatEvent event) {

    final BukkitPlayer player = new BukkitPlayer(event.getPlayer(), plugin);
    if(MenuManager.instance().inMenu(player.identifier())) {
      event.setCancelled(true);

      Optional<ViewerData> viewer = MenuManager.instance().getViewer(player.identifier());
      if(viewer.isPresent() && viewer.get().awaitingChat()) {

        PlayerChatCallback callback = new PlayerChatCallback(player, event.getMessage(),
                                                             viewer.get().getMenu(),
                                                             viewer.get().getPage());

        if(viewer.get().getChatCallback().test(callback)) {

          MenuManager.instance().switchViewer(player.identifier(), true);

          player.inventory().openMenu(player, callback.getMenu(), callback.getPage());
        }
      }
    }
  }
}