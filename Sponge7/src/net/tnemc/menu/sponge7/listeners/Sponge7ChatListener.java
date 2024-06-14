package net.tnemc.menu.sponge7.listeners;
/*
 * The New Menu Library
 * Copyright (C) 2022 - 2023 Daniel "creatorfromhell" Vidmar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import net.tnemc.menu.core.callbacks.ChatCallback;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.viewer.CoreStatus;
import net.tnemc.menu.core.viewer.MenuViewer;
import net.tnemc.menu.sponge7.SpongePlayer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.plugin.Plugin;

import java.util.Optional;

/**
 * SpongeChatListener
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class Sponge7ChatListener {

  private final Plugin plugin;

  public Sponge7ChatListener(Plugin plugin) {
    this.plugin = plugin;
  }

  @Listener
  public void onClose(MessageChannelEvent.Chat event) {

    if(event.getSource() instanceof Player) {
      final SpongePlayer sPlayer = new SpongePlayer((Player)event.getSource(), plugin);

      final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(sPlayer.identifier());

      if(viewer.isPresent() && viewer.get().status().awaitingChatInput()) {
        event.setCancelled(true);

        final ChatCallback callback = new ChatCallback(sPlayer, event.getRawMessage().toPlain(),
                viewer.get().menu(),
                viewer.get().page());

        if(viewer.get().chat(callback)) {

          sPlayer.inventory().openMenu(sPlayer, callback.getMenu(), callback.getPage());
          viewer.get().setStatus(CoreStatus.IN_MENU);
          viewer.get().setChatHandler(null);
        }
      }
    }
  }
}