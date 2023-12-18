package net.tnemc.menu.core.utils;

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

import static net.tnemc.menu.core.manager.MenuManager.ROW_SIZE;

/**
 * Represents a slot as a position in a grid of slots with rows and columns.
 * The class provides methods for creating a position with row and column values, creating a position from a slot number,
 * retrieving the slot number, as well as getting the row and column values.
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class SlotPos {

  /**
   * The row index in the grid.
   */
  private final int row;

  /**
   * The column index in the grid.
   */
  private final int column;

  /**
   * Constructs a {@link SlotPos} with specified row and column values.
   *
   * @param row The row index.
   * @param column The column index.
   */
  public SlotPos(final int row, final int column) {
    this.row = row;
    this.column = column;
  }

  /**
   * Constructs a {@link SlotPos} from a slot number.
   *
   * @param slot The slot number.
   */
  public SlotPos(final int slot) {
    this.row = slot / ROW_SIZE + 1;
    this.column = (slot % ROW_SIZE) + 1;
  }

  /**
   * Calculates and returns the slot number based on the current row and column values.
   *
   * @return The slot number.
   */
  public int slot() {
    return (row - 1) * ROW_SIZE + (column - 1);
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  @Override
  public String toString() {
    return "SlotPos{" +
            "row=" + row +
            ", column=" + column +
            '}';
  }
}