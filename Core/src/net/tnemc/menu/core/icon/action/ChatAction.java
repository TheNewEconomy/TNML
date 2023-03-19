package net.tnemc.menu.core.icon.action;

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

import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.callbacks.player.PlayerChatCallback;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.icon.IconAction;
import net.tnemc.menu.core.page.Page;

import java.util.function.Predicate;

/**
 * DataAction represents an {@link IconAction}, which is used to get feedback from the player, through
 * chat.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class ChatAction extends IconAction {

  private final Predicate<PlayerChatCallback> chatCallback;

  public ChatAction(Predicate<PlayerChatCallback> chatCallback) {
    super(ActionType.ANY);
    this.chatCallback = chatCallback;
  }

  public ChatAction(Predicate<PlayerChatCallback> chatCallback, final ActionType type) {
    super(type);
    this.chatCallback = chatCallback;
  }

  /**
   * Determines if any other icon actions should be performed after this action is performed.
   *
   * @return True if other actions should be performed, otherwise false.
   */
  @Override
  public boolean continueOther() {
    return true;
  }

  /**
   * This method is called when the action happens.
   *
   * @param menu   The menu that the action happened in.
   * @param page   The page of the menu that the action happened in.
   * @param player The player that performed the action.
   * @param icon   The icon clicked in the action.
   */
  @Override
  public void onPerform(Menu menu, Page page, MenuPlayer player, Icon icon) {
    MenuManager.instance().pauseViewer(player.identifier(), chatCallback);
    player.inventory().close();
  }
}