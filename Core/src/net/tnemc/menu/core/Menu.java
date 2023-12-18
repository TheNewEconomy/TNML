package net.tnemc.menu.core;

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

import net.tnemc.menu.core.callbacks.menu.MenuCloseCallback;
import net.tnemc.menu.core.callbacks.menu.MenuOpenCallback;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.handlers.MenuClickHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Menu
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class Menu {

  public final Map<Integer, Page> pages = new HashMap<>();

  protected String name;
  protected String title;
  protected int rows;

  protected Consumer<MenuOpenCallback> open;
  protected Consumer<MenuCloseCallback> close;

  /**
   * Handles a click action for a specific viewer identified by its UUID.
   *
   * @param handler The {@link  MenuClickHandler} for the click.
   * @return {@code true} if the click action is blocked, indicating that it should be prevented,
   *         {@code false} if the click action is allowed to proceed.
   */
  public boolean onClick(final MenuClickHandler handler) {
    System.out.println("Click: " + handler.toString());

    if(pages.containsKey(handler.getPage())) {
      return pages.get(handler.getPage()).onClick(handler);
    }
    return false;
  }

  public void onOpen(final MenuPlayer player, final int page) {
    player.inventory().openMenu(player, this, page);
  }

  public void onClose(final MenuPlayer player) {
    player.inventory().close();
  }

  public Map<Integer, Page> getPages() {
    return pages;
  }

  public Consumer<MenuOpenCallback> getOpen() {
    return open;
  }

  public void setOpen(Consumer<MenuOpenCallback> open) {
    this.open = open;
  }

  public Consumer<MenuCloseCallback> getClose() {
    return close;
  }

  public void setClose(Consumer<MenuCloseCallback> close) {
    this.close = close;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getRows() {
    return rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }
}