package net.tnemc.menu.core.icon.impl;

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

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.viewer.MenuViewer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * The {@link StateIcon} class represents an icon with a dynamic state that is determined by a state handler.
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 * @see Icon
 */
public class StateIcon extends Icon {

  private final Map<String, AbstractItemStack<?>> states = new HashMap<>();

  private final String stateID;

  private final String defaultState;

  /**
   * The function responsible for determining the state of the icon based on its current state.
   */
  protected final Function<String, String> stateHandler;

  public StateIcon(@NotNull AbstractItemStack<?> item, @Nullable Function<MenuPlayer,
          AbstractItemStack<?>> itemProvider, String stateID, String defaultState,
                   @NotNull Function<String, String> stateHandler) {
    super(item, itemProvider);
    this.stateID = stateID;
    this.defaultState = defaultState;
    this.stateHandler = stateHandler;
  }

  public Map<String, AbstractItemStack<?>> getStates() {
    return states;
  }

  public void addState(final String stateID, final AbstractItemStack<?> item) {
    states.put(stateID, item);
  }

  @Override
  public boolean onClick(MenuClickHandler handler) {
    if(super.onClick(handler)) {

      final Optional<MenuViewer> viewer = handler.player().viewer();
      if(viewer.isPresent()) {

        final String currentState = (String)viewer.get().dataOrDefault(stateID, defaultState);

        viewer.get().addData(stateID, stateHandler.apply(currentState));
        handler.player().inventory().updateInventory(slot(), getItem(handler.player()));
      }
    }
    return false;
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

    if(player != null) {

      final Optional<MenuViewer> viewer = player.viewer();
      if(viewer.isPresent()) {

        return states.getOrDefault((String)viewer.get().dataOrDefault(stateID, defaultState), item);
      }
    }
    return item;
  }
}