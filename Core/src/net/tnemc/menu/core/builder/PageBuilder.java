package net.tnemc.menu.core.builder;

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

import net.tnemc.menu.core.Page;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.Icon;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Builder class for creating instances of the {@link Page} class.
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 * @see Page
 */
public class PageBuilder {

  private final Map<Integer, Icon> icons = new HashMap<>();
  private Function<MenuClickHandler, Boolean> clickHandler;

  private final int pageNumber;

  public PageBuilder(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  /**
   * Adds icons to the page.
   *
   * @param icons The {@link Icon icons} to be added to the page.
   * @return This {@code PageBuilder} instance for method chaining.
   */
  public PageBuilder withIcons(Icon... icons) {

    for(Icon icon : icons) {
      this.icons.put(icon.slot(), icon);
    }
    return this;
  }

  /**
   * Sets the click handler for the page.
   *
   * @param clickHandler The click handler to be set for the page.
   * @return This {@code PageBuilder} instance for method chaining.
   */
  public PageBuilder withClickHandler(Function<MenuClickHandler, Boolean> clickHandler) {
    this.clickHandler = clickHandler;
    return this;
  }

  /**
   * Builds and returns the {@link Page} instance based on the provided configuration.
   *
   * @return The constructed {@code Page} instance.
   */
  public Page build() {
    final Page page = new Page(pageNumber);

    page.getIcons().putAll(icons);
    page.setClickHandler(clickHandler);

    return page;
  }
}