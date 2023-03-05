package net.tnemc.menu.core.utils;

/*
 * The New Economy
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

import net.tnemc.menu.core.Menu;

/**
 * Represents the various close types for a {@link Menu}.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public enum CloseType {
  CLOSE,
  //If a menu needs to be temporarily closed. This doesn't remove the player from being a viewer. This
  //could be for something such as requiring a text chat.
  TEMPORARY
}