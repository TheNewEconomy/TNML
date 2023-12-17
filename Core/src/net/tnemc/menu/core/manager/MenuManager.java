package net.tnemc.menu.core.manager;
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

import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.viewer.MenuViewer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@link MenuManager} class represents a manager for a collection of menus.
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class MenuManager {

  public static final int ROW_SIZE = 9;

  /**
   * The collection to store menus, where the key is the menu name and the value is the menu itself.
   */
  private final Map<String, Menu> menus = new HashMap<>();

  /**
   * Represents a collection of MenuViewers associated with unique UUIDs.
   */
  private final Map<UUID, MenuViewer> viewers = new ConcurrentHashMap<>();

  /**
   * Singleton instance of the MenuManager.
   */
  private static final MenuManager instance = new MenuManager();

  /**
   * Handles a click action for a specific viewer.
   *
   * @param handler The {@link  MenuClickHandler} for the click.
   * @return {@code true} if the click action is blocked, indicating that it should be prevented,
   *         {@code false} if the click action is allowed to proceed.
   */
  public boolean onClick(final MenuClickHandler handler) {

    final Optional<MenuViewer> viewer = findViewer(handler.getPlayer().identifier());
    if(viewer.isPresent()) {

      final Optional<Menu> menu = findMenu(viewer.get().getMenu());
      if(menu.isPresent()) {
        return menu.get().onClick(handler);
      }
    }
    return false;
  }

  /**
   * Finds a menu by its name and returns it as an Optional.
   *
   * @param name The name of the menu to find.
   * @return An Optional containing the found menu, or an empty Optional if not found.
   */
  public Optional<Menu> findMenu(final String name) {
    return Optional.ofNullable(menus.get(name));
  }

  /**
   * Adds a menu to the collection.
   *
   * @param menu The menu to be added.
   */
  public void addMenu(final Menu menu) {
    menus.put(menu.getName(), menu);
  }

  public boolean inMenu(final UUID id) {
    return findViewer(id).isPresent();
  }

  /**
   * Finds a MenuViewer by its UUID and returns it as an Optional.
   *
   * @param id The UUID of the MenuViewer to find.
   * @return An Optional containing the found MenuViewer, or an empty Optional if not found.
   */
  public Optional<MenuViewer> findViewer(final UUID id) {
    return Optional.ofNullable(viewers.get(id));
  }

  /**
   * Adds a MenuViewer to the collection.
   *
   * @param viewer The MenuViewer to be added.
   */
  public void addViewer(final MenuViewer viewer) {
    viewers.put(viewer.getUuid(), viewer);
  }

  /**
   * Returns the singleton instance of the MenuManager.
   *
   * @return The singleton instance of the MenuManager.
   */
  public static MenuManager instance() {
    return instance;
  }
}