package net.tnemc.menu.core.builder;

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
import net.tnemc.menu.core.callbacks.menu.MenuCloseCallback;
import net.tnemc.menu.core.callbacks.menu.MenuOpenCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Builder class for creating instances of the {@link Menu} class.
 *
 * @author creatorfromhell
 * @see Menu
 * @since 1.5.0.0
 */
public class MenuBuilder {

  private final Map<Integer, Page> pages = new HashMap<>();
  private final String name;
  private String title;
  private int rows;
  private boolean nonIcon = false;
  private boolean bottom = false;
  private Consumer<MenuOpenCallback> open;
  private Consumer<MenuCloseCallback> close;

  public MenuBuilder(final String name) {

    this.name = name;
  }

  /**
   * Sets the title for the menu.
   *
   * @param title The title to be set for the menu.
   *
   * @return This {@link MenuBuilder} instance for method chaining.
   */
  public MenuBuilder withTitle(final String title) {

    this.title = title;
    return this;
  }

  /**
   * Sets the number of rows for the menu.
   *
   * @param rows The number of rows to be set for the menu.
   *
   * @return This {@link MenuBuilder} instance for method chaining.
   */
  public MenuBuilder withRows(final int rows) {

    this.rows = rows;
    return this;
  }

  /**
   * Sets if the player can still utilize the bottom inventory.
   *
   * @param bottom True if the player can use the bottom inventory, their inventory, when in the
   *               menu.
   *
   * @return This {@link MenuBuilder} instance for method chaining.
   */
  public MenuBuilder withBottom(final boolean bottom) {

    this.bottom = bottom;
    return this;
  }

  /**
   * Sets if the player can still utilize the non icon slots in the menu.
   *
   * @param nonIcon True if the player can click slots in the menu that don't have an icon.
   *
   * @return This {@link MenuBuilder} instance for method chaining.
   */
  public MenuBuilder withNonIcon(final boolean nonIcon) {

    this.nonIcon = nonIcon;
    return this;
  }

  /**
   * Sets the open callback for the menu.
   *
   * @param open The open callback to be set for the menu.
   *
   * @return This {@link MenuBuilder} instance for method chaining.
   */
  public MenuBuilder withOpenCallback(final Consumer<MenuOpenCallback> open) {

    this.open = open;
    return this;
  }

  /**
   * Sets the close callback for the menu.
   *
   * @param close The close callback to be set for the menu.
   *
   * @return This {@link MenuBuilder} instance for method chaining.
   */
  public MenuBuilder withCloseCallback(final Consumer<MenuCloseCallback> close) {

    this.close = close;
    return this;
  }

  /**
   * Adds pages to the menu using varargs.
   *
   * @param pages The pages to be added to the menu.
   *
   * @return This {@link MenuBuilder} instance for method chaining.
   */
  public MenuBuilder withPages(final Page... pages) {

    for(final Page page : pages) {
      this.pages.put(page.number(), page);
    }
    return this;
  }

  /**
   * Builds and returns the {@link Menu} instance based on the provided configuration.
   *
   * @return The constructed {@link Menu} instance.
   */
  public Menu build() {

    final Menu menu = new Menu();

    menu.getPages().putAll(pages);
    menu.setName(name);
    menu.setTitle(title);
    menu.setRows(rows);
    menu.setBottom(bottom);
    menu.setNonIcon(nonIcon);
    menu.setOpen(open);
    menu.setClose(close);

    return menu;
  }
}