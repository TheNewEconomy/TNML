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

import net.tnemc.menu.core.PlayerInstancePage;
import net.tnemc.menu.core.callbacks.page.PageOpenCallback;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.utils.SlotPos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.tnemc.menu.core.manager.MenuManager.ROW_SIZE;

/**
 * Builder class for creating instances of the {@link PlayerInstancePage} class.
 *
 * @author creatorfromhell
 * @see PlayerInstancePage
 * @since 1.6.0.0
 */
public class PlayerPageBuilder {

  private final Map<UUID, Map<Integer, Icon>> playerIcons = new HashMap<>();
  private final int pageNumber;
  protected Consumer<PageOpenCallback> open;
  private Function<MenuClickHandler, Boolean> clickHandler;

  public PlayerPageBuilder(final int pageNumber) {

    this.pageNumber = pageNumber;
  }

  /**
   * Adds the specified {@link IconBuilder} to the specified row for a player.
   *
   * @param player      The UUID of the player.
   * @param row         The row index where the icons will be set. Rows are indexed starting from
   *                    1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified row.
   */
  public PlayerPageBuilder withRow(final UUID player, final int row, final IconBuilder iconBuilder) {

    return withRow(player, row, 1, ROW_SIZE, iconBuilder);
  }

  /**
   * Adds the specified {@link IconBuilder} to the specified row for a player.
   *
   * @param player         The UUID of the player.
   * @param row            The row index where the icons will be set. Rows are indexed starting from
   *                       1.
   * @param startingColumn The column to start applying the icons on, inclusive.
   * @param iconBuilder    The {@link IconBuilder} used to construct the icons for the specified
   *                       row.
   */
  public PlayerPageBuilder withRow(final UUID player, final int row, final int startingColumn, final IconBuilder iconBuilder) {

    return withRow(player, row, startingColumn, ROW_SIZE, iconBuilder);
  }

  /**
   * Adds the specified {@link IconBuilder} to the specified row for a player.
   *
   * @param player         The UUID of the player.
   * @param row            The row index where the icons will be set. Rows are indexed starting from
   *                       1.
   * @param startingColumn The column to start applying the icons on, inclusive.
   * @param endingColumn   The column to stop applying the icons on, inclusive.
   * @param iconBuilder    The {@link IconBuilder} used to construct the icons for the specified
   *                       row.
   */
  public PlayerPageBuilder withRow(final UUID player, final int row, final int startingColumn, final int endingColumn, final IconBuilder iconBuilder) {

    for(int i = startingColumn; i <= endingColumn; i++) {
      final Icon icon = iconBuilder.withSlot(new SlotPos(row, i)).build();
      this.getPlayerIcons(player).put(icon.slot(), icon);
    }
    return this;
  }

  /**
   * Adds the specified {@link IconBuilder} to the specified column for a player.
   *
   * @param player      The UUID of the player.
   * @param menuRows    The number of rows that the menu has.
   * @param column      The column index where the icons will be set. Columns are indexed starting
   *                    from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified
   *                    column.
   */
  public PlayerPageBuilder withColumn(final UUID player, final int menuRows, final int column, final IconBuilder iconBuilder) {

    return withColumn(player, menuRows, 1, menuRows, column, iconBuilder);
  }

  /**
   * Adds the specified {@link IconBuilder} to the specified column for a player.
   *
   * @param player      The UUID of the player.
   * @param menuRows    The number of rows that the menu has.
   * @param startingRow The row that the icons should start at, inclusive.
   * @param column      The column index where the icons will be set. Columns are indexed starting
   *                    from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified
   *                    column.
   */
  public PlayerPageBuilder withColumn(final UUID player, final int menuRows, final int startingRow, final int column, final IconBuilder iconBuilder) {

    return withColumn(player, menuRows, startingRow, menuRows, column, iconBuilder);
  }

  /**
   * Adds the specified {@link IconBuilder} to the specified column for a player.
   *
   * @param player      The UUID of the player.
   * @param menuRows    The number of rows that the menu has.
   * @param startingRow The row that the icons should start at, inclusive.
   * @param endingRow   The row that the icons should end at, inclusive.
   * @param column      The column index where the icons will be set. Columns are indexed starting
   *                    from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified
   *                    column.
   */
  public PlayerPageBuilder withColumn(final UUID player, final int menuRows, final int startingRow, final int endingRow, final int column, final IconBuilder iconBuilder) {

    for(int i = startingRow; i <= endingRow; i++) {
      final Icon icon = iconBuilder.withSlot(new SlotPos(i, column)).build();
      this.getPlayerIcons(player).put(icon.slot(), icon);
    }
    return this;
  }

  /**
   * Adds icons to the page for a player.
   *
   * @param player The UUID of the player.
   * @param icons  The {@link Icon icons} to be added to the page.
   *
   * @return This {@code PlayerPageBuilder} instance for method chaining.
   */
  public PlayerPageBuilder withIcons(final UUID player, final Icon... icons) {

    for(final Icon icon : icons) {
      this.getPlayerIcons(player).put(icon.slot(), icon);
    }
    return this;
  }

  /**
   * Sets the click handler for the page.
   *
   * @param clickHandler The click handler to be set for the page.
   *
   * @return This {@code PlayerPageBuilder} instance for method chaining.
   */
  public PlayerPageBuilder withClickHandler(final Function<MenuClickHandler, Boolean> clickHandler) {

    this.clickHandler = clickHandler;
    return this;
  }

  /**
   * Sets the open handler for the page.
   *
   * @param open The open handler to be set for the page.
   *
   * @return This {@code PlayerPageBuilder} instance for method chaining.
   */
  public PlayerPageBuilder withOpenHandler(final Consumer<PageOpenCallback> open) {

    this.open = open;
    return this;
  }

  /**
   * Builds and returns the {@link PlayerInstancePage} instance based on the provided
   * configuration.
   *
   * @return The constructed {@code PlayerInstancePage} instance.
   */
  public PlayerInstancePage build() {

    final PlayerInstancePage page = new PlayerInstancePage(pageNumber);

    for(final UUID player : playerIcons.keySet()) {
      page.getIcons(player).putAll(playerIcons.get(player));
    }
    page.setClickHandler(clickHandler);
    page.setOpen(open);

    return page;
  }

  /**
   * Retrieves the icons for a given player.
   *
   * @param player The UUID of the player.
   *
   * @return A map of icon slots to Icon objects.
   */
  private Map<Integer, Icon> getPlayerIcons(final UUID player) {

    if(playerIcons.containsKey(player)) {
      return playerIcons.get(player);
    }
    return playerIcons.computeIfAbsent(player, k->new HashMap<>());
  }
}