package net.tnemc.menu.bukkit.listener;

import net.tnemc.menu.bukkit.BukkitPlayer;
import net.tnemc.menu.core.callbacks.ChatCallback;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.viewer.MenuViewer;
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
    final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(player.identifier());

    if(viewer.isPresent() && viewer.get().status().awaitingChatInput()) {
      event.setCancelled(true);

      final ChatCallback callback = new ChatCallback(player, event.getMessage(),
              viewer.get().menu(),
              viewer.get().page());
      System.out.println("OnChat Viewer: Callback");

      if(viewer.get().chat(callback)) {
        System.out.println("OnChat Viewer: Callback true");
        System.out.println("OnChat Viewer: Callback");

        player.inventory().openMenu(player, callback.getMenu(), callback.getPage());
      }
    }
  }
}