package net.tnemc.menu.core.viewer;

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

/**
 * Represents the status of a {@link MenuViewer viewer} in the menu system.
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public interface ViewerStatus {

  /**
   * Checks whether the viewer is currently awaiting chat input.
   *
   * @return {@code true} if the viewer is awaiting chat input, {@code false} otherwise.
   */
  default boolean awaitingChatInput() {

    return false;
  }

  /**
   * Checks whether the viewer wants to close the menu.
   *
   * @return {@code true} if the viewer wants to close the menu, {@code false} otherwise.
   */
  default boolean closeMenu() {

    return true;
  }

  /**
   * Checks whether the viewer wants to change to a different menu.
   *
   * @return {@code true} if the viewer wants to change to a different menu, {@code false}
   * otherwise.
   */
  default boolean changing() {

    return false;
  }
}