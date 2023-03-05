package net.tnemc.menu.core;

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

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.callbacks.menu.MenuCloseCallback;
import net.tnemc.menu.core.callbacks.menu.MenuOpenCallback;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.icon.ActionType;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Consumer;

/**
 * Represents an inventory-based menu, which may be utilized for the player to perform actions or
 * view information in-game in a neat and organized manner.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class Menu {

  protected final ConcurrentSkipListMap<Integer, Page> pages = new ConcurrentSkipListMap<>();

  protected String name;
  protected String title;
  protected int size;

  protected boolean readOnly = true;

  protected int page;

  //Callbacks
  protected Consumer<MenuOpenCallback> open;

  protected Consumer<MenuCloseCallback> close;

  public Menu(String name, String title, int size) {
    this.name = name;
    this.title = title;
    
    int workingSize = size;
    if(workingSize % 9 > 0) {
      workingSize = workingSize + (workingSize % 9);
    }
    this.size = Math.min(workingSize, 45);

    this.page = 1;
  }

  public void build(MenuPlayer player) {
    player.inventory().openMenu(this);
  }

  public void update(MenuPlayer player, int slot, AbstractItemStack<?> item) {
    player.inventory().updateInventory(slot, item);
  }

  public boolean onClick(ActionType type, MenuPlayer provider, int page, int slot) {
    if(pages.containsKey(page)) {
      return pages.get(page).onClick(type, provider, this, slot);
    }
    return false;
  }

  public Map<Integer, Page> getPages() {
    return pages;
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

  public int getSize() {
    return size;
  }

  public boolean isReadOnly() {
    return readOnly;
  }

  public void setReadOnly(boolean readOnly) {
    this.readOnly = readOnly;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
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
}