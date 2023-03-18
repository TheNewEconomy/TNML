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
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.icon.IconAction;
import net.tnemc.menu.core.page.Page;

/**
 * UpdateAction is used to update a menu after an icon click.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class UpdateAction extends IconAction {

  public UpdateAction() {
    super(ActionType.ANY);
  }

  public UpdateAction(ActionType type) {
    super(type);
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
  public void onPerform(Menu menu, Page page, MenuPlayer player, final Icon icon) {
    menu.update(player, icon.getSlot(), icon.getItem());
  }
}