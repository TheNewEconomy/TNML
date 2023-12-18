package net.tnemc.menu.core.viewer;

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

/**
 * CoreStatus
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public enum CoreStatus implements ViewerStatus {

  IN_MENU {

    /**
     * Checks whether the viewer wants to close the menu.
     *
     * @return {@code true} if the viewer wants to close the menu, {@code false} otherwise.
     */
    @Override
    public boolean closeMenu() {
      return false;
    }
  },

  AWAITING_CHAT {
    /**
     * Checks whether the viewer is currently awaiting chat input.
     *
     * @return {@code true} if the viewer is awaiting chat input, {@code false} otherwise.
     */
    @Override
    public boolean awaitingChatInput() {
      return true;
    }

    /**
     * Checks whether the viewer wants to close the menu.
     *
     * @return {@code true} if the viewer wants to close the menu, {@code false} otherwise.
     */
    @Override
    public boolean closeMenu() {
      return false;
    }
  },

  SWITCHING {

    /**
     * Checks whether the viewer wants to close the menu.
     *
     * @return {@code true} if the viewer wants to close the menu, {@code false} otherwise.
     */
    @Override
    public boolean closeMenu() {
      return false;
    }
  },

  CLOSING {
    /**
     * Checks whether the viewer wants to close the menu.
     *
     * @return {@code true} if the viewer wants to close the menu, {@code false} otherwise.
     */
    @Override
    public boolean closeMenu() {
      return true;
    }
  }
}