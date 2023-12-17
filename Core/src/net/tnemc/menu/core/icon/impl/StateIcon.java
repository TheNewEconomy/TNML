package net.tnemc.menu.core.icon.impl;
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

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.icon.Icon;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * The {@link StateIcon} class represents an icon with a dynamic state that is determined by a state handler.
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 * @see Icon
 */
public class StateIcon extends Icon {

  /**
   * The function responsible for determining the state of the icon based on its current state.
   */
  protected final Function<StateIcon, AbstractItemStack<?>> stateHandler;

  /**
   * Constructs a {@link StateIcon} with an initial state and a state handler.
   *
   * @param initial The initial state of the icon.
   * @param stateHandler The function to handle state transitions and determine the current state of the icon.
   */
  public StateIcon(AbstractItemStack<?> initial, @Nullable Function<MenuPlayer, AbstractItemStack<?>> itemProvider,
                   Function<StateIcon, AbstractItemStack<?>> stateHandler) {
    super(initial, itemProvider);
    this.stateHandler = stateHandler;
  }

  /**
   * Retrieves the current {@link AbstractItemStack item} of the icon by applying the state handler.
   *
   * @param player The player that is viewing the menu.
   *
   * @return The {@link AbstractItemStack item} representing the current state of the icon.
   */
  @Override
  public AbstractItemStack<?> getItem(@Nullable MenuPlayer player) {
    return stateHandler.apply(this);
  }
}