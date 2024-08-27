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

import net.tnemc.menu.core.Page;
import net.tnemc.menu.core.callbacks.page.PageOpenCallback;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.utils.SlotPos;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.tnemc.menu.core.manager.MenuManager.ROW_SIZE;

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

  protected Consumer<PageOpenCallback> open;

  private final int pageNumber;

  public PageBuilder(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  /**
   * Adds the specified {@link IconBuilder} to the specified row.
   *
   * @param row The row index where the icons will be set. Rows are indexed starting from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified row.
   */
  public PageBuilder withRow(final int row, final IconBuilder iconBuilder) {
    for(int i = 1; i <= ROW_SIZE; i++) {
      final Icon icon = iconBuilder.withSlot(new SlotPos(row, i)).build();
      this.icons.put(icon.slot(), icon);
    }
    return this;
  }

  /**
   * Adds the specified {@link IconBuilder} to the specified column.
   *
   * @param menuRows The number of rows that the menu has.
   * @param column The column index where the icons will be set. Columns are indexed starting from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified column.
   */
  public PageBuilder withColumn(final int menuRows, final int column, final IconBuilder iconBuilder) {

    return withColumn(menuRows, 1, menuRows, column, iconBuilder);
  }

  /**
   * Adds the specified {@link IconBuilder} to the specified column.
   *
   * @param menuRows The number of rows that the menu has.
   * @param startingRow The row that the icons should start at, inclusive.
   * @param column The column index where the icons will be set. Columns are indexed starting from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified column.
   */
  public PageBuilder withColumn(final int menuRows, final int startingRow, final int column, final IconBuilder iconBuilder) {

    return withColumn(menuRows, startingRow, menuRows, column, iconBuilder);
  }

  /**
   * Adds the specified {@link IconBuilder} to the specified column.
   *
   * @param menuRows The number of rows that the menu has.
   * @param startingRow The row that the icons should start at, inclusive.
   * @param endingRow The row that the icons should end at, inclusive.
   * @param column The column index where the icons will be set. Columns are indexed starting from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified column.
   */
  public PageBuilder withColumn(final int menuRows, final int startingRow, final int endingRow, final int column, final IconBuilder iconBuilder) {

    for(int i = startingRow; i <= endingRow; i++) {
      final Icon icon = iconBuilder.withSlot(new SlotPos(i, column)).build();
      this.icons.put(icon.slot(), icon);
    }
    return this;
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
   * Sets the open handler for the page.
   *
   * @param open The click handler to be set for the page.
   * @return This {@code PageBuilder} instance for method chaining.
   */
  public PageBuilder withOpenHandler(Consumer<PageOpenCallback> open) {
    this.open = open;
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
    page.setOpen(open);

    return page;
  }
}