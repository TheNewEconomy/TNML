package net.tnemc.menu.core.compatibility;

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

import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.viewer.MenuViewer;
import net.tnemc.menu.core.viewer.ViewerStatus;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents a player object for a platform. This holds all methods that need to be converted to
 * each individual platform.
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface MenuPlayer {

  /**
   * The {@link UUID unique identifier} for this player.
   * @return The {@link UUID} for the player.
   */
  UUID identifier();

  /**
   * The {@link PlayerInventory} for this player.
   * @return The {@link PlayerInventory} for this player.
   */
  PlayerInventory<?> inventory();

  /**
   * Used to determine if this player has the specified permission node.
   *
   * @param permission The node to check for.
   * @return True if the player has the permission, otherwise false.
   */
  boolean hasPermission(String permission);

  /**
   * Used to send a message to this player.
   * @param message The message.
   */
  void message(final String message);

  default void status(final ViewerStatus status) {
    MenuManager.instance().updateViewer(identifier(), status);
  }

  /**
   * Retrieves the {@link MenuViewer} associated with the identifier.
   *
   * @return An {@link Optional} containing the found {@link MenuViewer} based on the identifier,
   *         or an empty {@code Optional} if the viewer is not found.
   * @see MenuManager#findViewer(UUID)
   */
  default Optional<MenuViewer> viewer() {
    return MenuManager.instance().findViewer(identifier());
  }
}