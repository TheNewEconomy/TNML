package net.tnemc.menu.core.manager;

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

import net.tnemc.item.platform.registry.BaseHelper;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.viewer.MenuViewer;
import net.tnemc.menu.core.viewer.ViewerStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MenuManager class is responsible for managing menus and menu viewers.
 */
public class MenuManager {

  public static final int ROW_SIZE = 9;
  /**
   * Singleton instance of the MenuManager.
   */
  private static final MenuManager instance = new MenuManager();
  /**
   * The collection to store menus, where the key is the menu name and the value is the menu
   * itself.
   */
  private final Map<String, Menu> menus = new HashMap<>();
  private final Map<UUID, Long> recentlyClosed = new ConcurrentHashMap<>();
  /**
   * Represents a collection of MenuViewers associated with unique UUIDs.
   */
  private final Map<UUID, MenuViewer> viewers = new ConcurrentHashMap<>();
  private BaseHelper helper;

  //private constructor for MenuManager.
  private MenuManager() {

  }

  /**
   * Returns the singleton instance of the MenuManager.
   *
   * @return The singleton instance of the MenuManager.
   */
  public static MenuManager instance() {

    return instance;
  }

  public void open(final String menu, final int page, final MenuPlayer player) {

    final Optional<Menu> menuObj = findMenu(menu);
    menuObj.ifPresent(value->value.onOpen(player, page));
  }

  /**
   * Finds a menu by its name and returns it as an Optional.
   *
   * @param name The name of the menu to find.
   *
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
   *
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

    final Optional<MenuViewer> existing = MenuManager.instance().findViewer(viewer.uuid());
    if(existing.isPresent()) {

      existing.get().merge(viewer);
      return;
    }

    viewers.put(viewer.uuid(), viewer);
  }

  /**
   * Updates the menu and page for a MenuViewer identified by the given UUID.
   *
   * @param identifier The UUID identifying the player associated with the MenuViewer.
   * @param menu       The new menu to set for the MenuViewer.
   * @param page       The new page to set for the MenuViewer.
   */
  public void updateViewer(final UUID identifier, final String menu, final int page) {

    final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(identifier);
    if(viewer.isPresent()) {

      viewer.get().setMenu(menu);
      viewer.get().setPage(page);
      return;
    }

    final MenuViewer newViewer = new MenuViewer(identifier);
    newViewer.setMenu(menu);
    newViewer.setPage(page);

    viewers.put(identifier, newViewer);
  }

  public void updateViewer(final UUID identifier, final ViewerStatus status) {

    final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(identifier);

    viewer.ifPresent(menuViewer->menuViewer.setStatus(status));
  }

  /**
   * Removes a MenuViewer associated with the given UUID from the viewers map.
   *
   * @param identifier The UUID identifying the player whose {@link MenuViewer viewer data} should
   *                   be removed.
   */
  public void removeViewer(final UUID identifier) {

    //recentlyClosed.put(identifier, System.currentTimeMillis());
    viewers.remove(identifier);
  }

  public Map<UUID, Long> recentlyClosed() {

    return recentlyClosed;
  }

  /**
   * Returns the HelperMethods object associated with this MenuManager.
   *
   * @return The HelperMethods object associated with this MenuManager.
   */
  public BaseHelper getHelper() {

    return helper;
  }

  /**
   * Sets the HelperMethods object associated with this MenuManager.
   *
   * @param helper The HelperMethods object to be set.
   */
  public void setHelper(final BaseHelper helper) {

    this.helper = helper;
  }
}