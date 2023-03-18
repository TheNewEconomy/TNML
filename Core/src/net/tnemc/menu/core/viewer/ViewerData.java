package net.tnemc.menu.core.viewer;

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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Represents data that belongs to a user that is interacting with a menu.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class ViewerData {

  private final Map<String, Object> data = new HashMap<>();

  private UUID viewer;
  private String menu;
  private int page = 1;

  private boolean paused = false;

  private Predicate<PlayerChatCallback> chatCallback;

  public ViewerData(UUID viewer, final String menu) {
    this.viewer = viewer;
    this.menu = menu;
  }

  public ViewerData(UUID viewer, Map<String, Object> data) {
    this.viewer = viewer;
    this.data.putAll(data);
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data.clear();
    this.data.putAll(data);
  }

  public UUID getViewer() {
    return viewer;
  }

  public void setViewer(UUID viewer) {
    this.viewer = viewer;
  }

  public String getMenu() {
    return menu;
  }

  public void setMenu(String menu) {
    this.menu = menu;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public boolean awaitingChat() {
    return chatCallback != null;
  }

  public boolean isPaused() {
    return paused;
  }

  public void setPaused(boolean paused) {
    this.paused = paused;
  }

  public Predicate<PlayerChatCallback> getChatCallback() {
    return chatCallback;
  }

  public void setChatCallback(Predicate<PlayerChatCallback> chatCallback) {
    this.chatCallback = chatCallback;
  }

  public Object getValue(String identifier) {
    return data.get(identifier);
  }

  public void setValue(String identifier, Object value) {
    data.put(identifier, value);
  }
}