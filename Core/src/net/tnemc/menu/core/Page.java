package net.tnemc.menu.core;

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

import net.tnemc.menu.core.builder.IconBuilder;
import net.tnemc.menu.core.callbacks.page.PageOpenCallback;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.utils.SlotPos;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.tnemc.menu.core.manager.MenuManager.ROW_SIZE;

/**
 * Page
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class Page {

  protected final Map<Integer, Icon> icons = new HashMap<>();
  private final int pageNumber;
  protected Function<MenuClickHandler, Boolean> clickHandler;

  protected Consumer<PageOpenCallback> open;

  public Page(final int pageNumber) {

    this.pageNumber = pageNumber;
  }

  public int number() {

    return pageNumber;
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified row.
   *
   * @param row         The row index where the icons will be set. Rows are indexed starting from 1,
   *                    through {@link MenuManager#ROW_SIZE}.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified row.
   */
  public void setRow(final int row, final IconBuilder iconBuilder) {

    setRow(row, 1, ROW_SIZE, iconBuilder);
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified row.
   *
   * @param row            The row index where the icons will be set. Rows are indexed starting from
   *                       1, through {@link MenuManager#ROW_SIZE}.
   * @param startingColumn The column to start applying the icons on, inclusive.
   * @param iconBuilder    The {@link IconBuilder} used to construct the icons for the specified
   *                       row.
   */
  public void setRow(final int row, final int startingColumn, final IconBuilder iconBuilder) {

    setRow(row, startingColumn, ROW_SIZE, iconBuilder);
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified row.
   *
   * @param row            The row index where the icons will be set. Rows are indexed starting from
   *                       1, through {@link MenuManager#ROW_SIZE}.
   * @param startingColumn The column to start applying the icons on, inclusive.
   * @param endingColumn   The column to stop applying the icons on, inclusive.
   * @param iconBuilder    The {@link IconBuilder} used to construct the icons for the specified
   *                       row.
   */
  public void setRow(final int row, final int startingColumn, final int endingColumn, final IconBuilder iconBuilder) {

    for(int i = startingColumn; i <= endingColumn; i++) {
      addIcon(iconBuilder.withSlot(new SlotPos(row, i)).build());
    }
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified column.
   *
   * @param menuRows    The number of rows that the menu has.
   * @param column      The column index where the icons will be set. Columns are indexed starting
   *                    from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified
   *                    column.
   */
  public void setColumn(final int menuRows, final int column, final IconBuilder iconBuilder) {

    setColumn(menuRows, 1, menuRows, column, iconBuilder);
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified column.
   *
   * @param menuRows    The number of rows that the menu has.
   * @param startingRow The row that the icons should start at, inclusive.
   * @param column      The column index where the icons will be set. Columns are indexed starting
   *                    from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified
   *                    column.
   */
  public void setColumn(final int menuRows, final int startingRow, final int column, final IconBuilder iconBuilder) {

    setColumn(menuRows, startingRow, menuRows, column, iconBuilder);
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified column.
   *
   * @param menuRows    The number of rows that the menu has.
   * @param startingRow The row that the icons should start at, inclusive.
   * @param endingRow   The row that the icons should end at, inclusive.
   * @param column      The column index where the icons will be set. Columns are indexed starting
   *                    from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified
   *                    column.
   */
  public void setColumn(final int menuRows, final int startingRow, final int endingRow, final int column, final IconBuilder iconBuilder) {

    for(int i = startingRow; i <= endingRow; i++) {
      addIcon(iconBuilder.withSlot(new SlotPos(i, column)).build());
    }
  }

  /**
   * Handles a click action for a specific viewer identified by its UUID.
   *
   * @param handler The {@link  MenuClickHandler} for the click.
   *
   * @return {@code true} if the click action is blocked, indicating that it should be prevented,
   * {@code false} if the click action is allowed to proceed.
   */
  public boolean onClick(final MenuClickHandler handler) {

    if(icons.containsKey(handler.slot().slot())) {
      if(!icons.get(handler.slot().slot()).onClick(handler)) {
        return true;
      }
    }


    if(clickHandler != null) {
      return clickHandler.apply(handler);
    }

    return icons.containsKey(handler.slot().slot());
  }

  public void addIcon(final Icon icon) {

    icons.put(icon.slot(), icon);
  }

  public Map<Integer, Icon> getIcons() {

    return icons;
  }

  public Consumer<PageOpenCallback> getOpen() {

    return open;
  }

  public void setOpen(final Consumer<PageOpenCallback> open) {

    this.open = open;
  }

  public Function<MenuClickHandler, Boolean> getClickHandler() {

    return clickHandler;
  }

  public void setClickHandler(final Function<MenuClickHandler, Boolean> clickHandler) {

    this.clickHandler = clickHandler;
  }
}