package net.tnemc.menu.core.icon.action.impl;
/*
 * The New Menu Library
 * Copyright (C) 2022 - 2024 Daniel "creatorfromhell" Vidmar
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
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.action.ActionType;
import net.tnemc.menu.core.icon.action.IconAction;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.viewer.CoreStatus;
import net.tnemc.menu.core.viewer.MenuViewer;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * ChatAction
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class ChatAction extends IconAction {

  private final Predicate<ChatCallback> chatCallback;

  public ChatAction(final Predicate<ChatCallback> chatCallback) {

    super(ActionType.ANY);
    this.chatCallback = chatCallback;
  }

  public ChatAction(final Predicate<ChatCallback> chatCallback, final ActionType type) {

    super(type);
    this.chatCallback = chatCallback;
  }

  /**
   * Handles the click action for the icon using the provided {@link MenuClickHandler}.
   *
   * @param handler The {@link MenuClickHandler} to be executed upon the click action.
   *
   * @return {@code true} if the click action is blocked or has special behavior, otherwise
   * {@code false}.
   */
  @Override
  public boolean onClick(final MenuClickHandler handler) {

    final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(handler.player().identifier());
    if(viewer.isPresent()) {

      viewer.get().setStatus(CoreStatus.AWAITING_CHAT);
      viewer.get().setChatHandler(chatCallback);

      handler.player().inventory().close();
    }
    return true;
  }
}