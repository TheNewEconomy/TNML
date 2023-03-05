package net.tnemc.menu.core.builder;

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

import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.Page;
import net.tnemc.menu.core.callbacks.menu.MenuCloseCallback;
import net.tnemc.menu.core.callbacks.menu.MenuOpenCallback;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Consumer;

public class MenuBuilder {

  private final ConcurrentSkipListMap<Integer, Page> pages = new ConcurrentSkipListMap<>();

  private String name;
  private String title;
  private int size;

  //Callbacks
  private Consumer<MenuOpenCallback> open;

  private Consumer<MenuCloseCallback> close;

  public static MenuBuilder of(final String name) {
    return new MenuBuilder(name);
  }

  public MenuBuilder(String name) {
    this.name = name;
  }

  public MenuBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public MenuBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public MenuBuilder withSize(int size) {
    this.size = size;
    return this;
  }

  public MenuBuilder open(Consumer<MenuOpenCallback> callback) {
    this.open = callback;
    return this;
  }

  public MenuBuilder close(Consumer<MenuCloseCallback> callback) {
    this.close = callback;
    return this;
  }

  public MenuBuilder withPage(final Page page) {
    this.pages.put(page.getId(), page);
    return this;
  }

  public Menu create() {
    Menu menu = new Menu(name, title, size);
    menu.setOpen(open);
    menu.setClose(close);
    menu.getPages().putAll(pages);

    return menu;
  }
}