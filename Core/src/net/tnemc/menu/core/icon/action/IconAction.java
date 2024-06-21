package net.tnemc.menu.core.icon.action;

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

import net.tnemc.menu.core.handlers.MenuClickHandler;

/**
 * Represents an action that is performed on an icon within a menu.
 * This class provides a common structure for different types of icon actions.
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public abstract class IconAction {

  protected final ActionType type;

  public IconAction(ActionType type) {
    this.type = type;
  }

  public ActionType getType() {
    return type;
  }

  /**
   * Determines if any other icon actions should be performed after this action is performed.
   *
   * @return {@code true} if other actions should be performed, otherwise {@code false}.
   */
  public boolean continueOther() {
    return true;
  }

  /**
   * Handles the click action for the icon using the provided {@link MenuClickHandler}.
   *
   * @param handler The {@link MenuClickHandler} to be executed upon the click action.
   * @return {@code true} if the click action is blocked or has special behavior, otherwise {@code false}.
   */
  public abstract boolean onClick(final MenuClickHandler handler);
}