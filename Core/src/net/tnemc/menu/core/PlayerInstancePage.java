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

import net.tnemc.menu.core.builder.IconBuilder;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.utils.PlayerInstance;
import net.tnemc.menu.core.utils.SlotPos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static net.tnemc.menu.core.manager.MenuManager.ROW_SIZE;

/**
 * PlayerInstancePage
 *
 * @author creatorfromhell
 * @since 1.6.0.0
 */
public class PlayerInstancePage extends Page {

  protected final Map<UUID, PlayerInstance> players = new HashMap<>();

  public PlayerInstancePage(final int pageNumber) {

    super(pageNumber);
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified row.
   *
   * @param player      The UUID of the player.
   * @param row         The row index where the icons will be set. Rows are indexed starting from 1,
   *                    through {@link MenuManager#ROW_SIZE}.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified row.
   */
  public void setRow(final UUID player, final int row, final IconBuilder iconBuilder) {

    setRow(player, row, 1, ROW_SIZE, iconBuilder);
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified row.
   *
   * @param player         The UUID of the player.
   * @param row            The row index where the icons will be set. Rows are indexed starting from
   *                       1, through {@link MenuManager#ROW_SIZE}.
   * @param startingColumn The column to start applying the icons on, inclusive.
   * @param iconBuilder    The {@link IconBuilder} used to construct the icons for the specified
   *                       row.
   */
  public void setRow(final UUID player, final int row, final int startingColumn, final IconBuilder iconBuilder) {

    setRow(player, row, startingColumn, ROW_SIZE, iconBuilder);
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified row.
   *
   * @param player         The UUID of the player.
   * @param row            The row index where the icons will be set. Rows are indexed starting from
   *                       1, through {@link MenuManager#ROW_SIZE}.
   * @param startingColumn The column to start applying the icons on, inclusive.
   * @param endingColumn   The column to stop applying the icons on, inclusive.
   * @param iconBuilder    The {@link IconBuilder} used to construct the icons for the specified
   *                       row.
   */
  public void setRow(final UUID player, final int row, final int startingColumn, final int endingColumn, final IconBuilder iconBuilder) {

    for(int i = startingColumn; i <= endingColumn; i++) {
      addIcon(player, iconBuilder.withSlot(new SlotPos(row, i)).build());
    }
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified column.
   *
   * @param player      The UUID of the player.
   * @param menuRows    The number of rows that the menu has.
   * @param column      The column index where the icons will be set. Columns are indexed starting
   *                    from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified
   *                    column.
   */
  public void setColumn(final UUID player, final int menuRows, final int column, final IconBuilder iconBuilder) {

    setColumn(player, menuRows, 1, menuRows, column, iconBuilder);
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified column.
   *
   * @param player      The UUID of the player.
   * @param menuRows    The number of rows that the menu has.
   * @param startingRow The row that the icons should start at, inclusive.
   * @param column      The column index where the icons will be set. Columns are indexed starting
   *                    from 1.
   * @param iconBuilder The {@link IconBuilder} used to construct the icons for the specified
   *                    column.
   */
  public void setColumn(final UUID player, final int menuRows, final int startingRow, final int column, final IconBuilder iconBuilder) {

    setColumn(player, menuRows, startingRow, menuRows, column, iconBuilder);
  }

  /**
   * Sets the specified {@link IconBuilder} to the specified column.
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
  public void setColumn(final UUID player, final int menuRows, final int startingRow, final int endingRow, final int column, final IconBuilder iconBuilder) {

    for(int i = startingRow; i <= endingRow; i++) {
      addIcon(player, iconBuilder.withSlot(new SlotPos(i, column)).build());
    }
  }

  /**
   * Handles a click action for a specific viewer identified by its UUID.
   *
   * @param player  The UUID of the player.
   * @param handler The {@link  MenuClickHandler} for the click.
   *
   * @return {@code true} if the click action is blocked, indicating that it should be prevented,
   * {@code false} if the click action is allowed to proceed.
   */
  public boolean onClick(final UUID player, final MenuClickHandler handler) {

    if(getIcons(player).containsKey(handler.slot().slot())) {
      if(!getIcons(player).get(handler.slot().slot()).onClick(handler)) {
        return true;
      }
    }


    if(clickHandler != null) {
      return clickHandler.apply(handler);
    }
    return getIcons(player).containsKey(handler.slot().slot());
  }

  /**
   * Adds an icon to the player's instance.
   *
   * @param player The UUID of the player.
   * @param icon   The {@link Icon} to be added.
   */
  public void addIcon(final UUID player, final Icon icon) {

    if(players.containsKey(player)) {
      players.get(player).addIcon(icon);
    }

    final PlayerInstance instance = new PlayerInstance(player);
    instance.addIcon(icon);

    players.put(player, instance);
  }

  /**
   * Retrieves the icons for a given player.
   *
   * @param player The UUID of the player.
   *
   * @return A map of icon slots to Icon objects.
   */
  public Map<Integer, Icon> getIcons(final UUID player) {

    if(players.containsKey(player)) {
      return players.get(player).getIcons();
    }
    return icons;
  }

  /**
   * Removes an instance of PlayerInstance identified by UUID from the players map.
   *
   * @param uuid The UUID of the player instance to be removed.
   */
  public void removeInstance(final UUID uuid) {

    players.remove(uuid);
  }
}