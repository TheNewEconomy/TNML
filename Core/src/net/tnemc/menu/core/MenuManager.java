package net.tnemc.menu.core;

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

import net.tnemc.menu.core.callbacks.player.PlayerChatCallback;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.utils.CloseType;
import net.tnemc.menu.core.viewer.ViewerData;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * MenuManager
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class MenuManager {

  private final Map<String, Menu> menus = new HashMap<>();

  private final ConcurrentHashMap<UUID, ViewerData> data = new ConcurrentHashMap<>();

  private static MenuManager instance;

  public MenuManager() {

    instance = this;
  }

  public boolean onClick(String menu, ActionType type, MenuPlayer player, int page, int slot) {

    if(menus.containsKey(menu) && data.containsKey(player.identifier())) {
      return menus.get(menu).onClick(type, player, page, slot);
    }

    return false;
  }

  public void onClose(MenuPlayer player, CloseType type) {

    final Optional<ViewerData> viewer = Optional.ofNullable(data.get(player.identifier()));

    if(viewer.isPresent() && menus.containsKey(viewer.get().getMenu())) {

      menus.get(viewer.get().getMenu()).onClose(player, viewer.get().getPage(), type);

      if(!viewer.get().isPaused() && !type.equals(CloseType.TEMPORARY)) {
        data.remove(player.identifier());
      }
    }
  }

  public void onClose(String menu, MenuPlayer player, int page, CloseType type) {

    if(menus.containsKey(menu) && data.containsKey(player.identifier())) {

      if(!data.get(player.identifier()).isPaused() && !type.equals(CloseType.TEMPORARY)) {
        menus.get(menu).onClose(player, page, type);
      }
    }
  }

  public void addMenu(final Menu menu) {
    menus.put(menu.getName(), menu);
  }

  public Optional<Menu> getMenu(final String menu) {
    return Optional.ofNullable(menus.get(menu));
  }

  public boolean inMenu(UUID id) {
    return data.containsKey(id);
  }

  public void pauseViewer(final UUID id, Predicate<PlayerChatCallback> chatCallback) {
    if(data.containsKey(id)) {

      ViewerData viewer = data.get(id);

      viewer.setPaused(true);
      viewer.setChatCallback(chatCallback);

      data.put(id, viewer);
    }
  }

  public void resumeViewer(final UUID id) {
    if(data.containsKey(id)) {

      ViewerData viewer = data.get(id);

      if(viewer.isPaused()) {
        viewer.setPaused(false);
        viewer.setChatCallback(null);

        data.put(id, viewer);
      }
    }
  }

  public void updateViewer(final UUID id, final String menu, final int page) {
    ViewerData viewer = data.getOrDefault(id, new ViewerData(id, menu));

    if(viewer.isPaused()) {
      viewer.setPaused(false);
      return;
    }

    viewer.setMenu(menu);
    viewer.setPage(page);

    data.put(id, viewer);
  }

  public Optional<ViewerData> getViewer(final UUID id) {
    return Optional.ofNullable(data.get(id));
  }

  public void removeViewer(UUID id) {
    data.remove(id);
  }

  public Optional<Object> getViewerData(UUID viewer, String identifier) {
    if(data.containsKey(viewer)) {
      return Optional.ofNullable(data.get(viewer).getValue(identifier));
    }
    return Optional.empty();
  }

  public void appendViewerData(UUID viewer, Map<String, Object> toAppend) {
    if(!data.containsKey(viewer)) {
      data.put(viewer, new ViewerData(viewer, toAppend));
      return;
    }
    data.get(viewer).getData().putAll(toAppend);
  }

  public void appendViewerData(UUID viewer, final String identifier, final Object value) {

    if(data.containsKey(viewer)) {
      data.get(viewer).getData().put(identifier, value);
    }
  }

  public void setViewerData(UUID viewer, final String menu, String identifier, Object value) {
    if(!data.containsKey(viewer)) {
      data.put(viewer, new ViewerData(viewer, menu));
    }
    data.get(viewer).setValue(identifier, value);
  }

  public static MenuManager instance() {
    return instance;
  }
}