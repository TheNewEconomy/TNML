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

import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.Page;
import net.tnemc.menu.core.PlayerInstancePage;
import net.tnemc.menu.core.callbacks.ChatCallback;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.manager.MenuManager;

import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;

/**
 * MenuViewer
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class MenuViewer {

  private final Map<String, Object> data = new ConcurrentHashMap<>();

  /**
   * Represents a collection that contains all of
   * {@link net.tnemc.menu.core.PlayerInstancePage PlayerPages} that this viewer has an instance in.
   * This will let use remove them from the memory so we are holding them forever.
   */
  private final Queue<String> menuInstances = new ConcurrentLinkedQueue<>();

  private final UUID uuid;

  private String menu;
  private int page = -1;

  private Predicate<ChatCallback> chatHandler;

  private ViewerStatus status = CoreStatus.IN_MENU;

  public MenuViewer(final UUID uuid) {

    this.uuid = uuid;
  }

  /**
   * Merges the properties of another MenuViewer into this MenuViewer.
   *
   * @param viewer The MenuViewer to merge into this MenuViewer.
   */
  public void merge(final MenuViewer viewer) {

    if(viewer == null || !uuid.equals(viewer.uuid)) {
      return;
    }

    // Update variables if they are different in the otherMenuViewer

    if(!menu.equals(viewer.menu)) {
      menu = viewer.menu;
    }

    if(page != viewer.page) {
      page = viewer.page;
    }

    if(status != viewer.status) {
      status = viewer.status;
    }

    if(chatHandler != viewer.chatHandler) {
      chatHandler = viewer.chatHandler;
    }
  }

  public void addData(final String identifier, final Object data) {

    this.data.put(identifier, data);
  }

  public void addInstance(final String identifier) {

    this.menuInstances.add(identifier);
  }

  public void removeInstances() {

    for(final String menu : menuInstances) {

      final Optional<Menu> menuObj = MenuManager.instance().findMenu(menu);
      if(menuObj.isPresent()) {

        for(final Page page : menuObj.get().pages.values()) {

          if(page instanceof PlayerInstancePage playerPage) {
            playerPage.removeInstance(uuid);
          }
        }
      }
    }
  }

  public Optional<Object> findData(final String identifier) {

    return Optional.ofNullable(data.get(identifier));
  }

  public Object dataOrDefault(final String identifier, final Object defaultData) {

    return data.getOrDefault(identifier, defaultData);
  }

  /**
   * Closes the menu for a specific player and removes the associated
   * {@link MenuViewer viewer data}.
   *
   * @param player The {@link MenuPlayer} for whom the menu should be closed.
   */
  public void close(final MenuPlayer player) {

    final Optional<Menu> menuObj = MenuManager.instance().findMenu(menu);
    menuObj.ifPresent(value->value.onClose(player));

    removeInstances();

    MenuManager.instance().removeViewer(uuid);
  }

  public UUID uuid() {

    return uuid;
  }

  public String menu() {

    return menu;
  }

  public void setMenu(final String menu) {

    this.menu = menu;
  }

  public int page() {

    return page;
  }

  public void setPage(final int page) {

    this.page = page;
  }

  public ViewerStatus status() {

    return status;
  }

  public void setStatus(final ViewerStatus status) {

    this.status = status;
  }

  public boolean chat(final ChatCallback callback) {

    return chatHandler.test(callback);
  }

  public Predicate<ChatCallback> getChatHandler() {

    return chatHandler;
  }

  public void setChatHandler(final Predicate<ChatCallback> chatHandler) {

    this.chatHandler = chatHandler;
  }
}