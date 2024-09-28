package net.tnemc.menu.core.handlers;

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
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.icon.action.ActionType;
import net.tnemc.menu.core.utils.SlotPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * MenuClickProvider
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class MenuClickHandler {

  private final SlotPos slot;

  private final MenuPlayer player;

  private final Menu menu;

  private final Integer page;

  private final ActionType actionType;


  public MenuClickHandler(@NotNull final SlotPos slot, @NotNull final MenuPlayer player, @NotNull final Menu menu,
                          @Nullable final Integer page, @NotNull final ActionType actionType) {

    this.slot = slot;
    this.player = player;
    this.menu = menu;
    this.page = page;
    this.actionType = actionType;
  }

  public SlotPos slot() {

    return slot;
  }

  public MenuPlayer player() {

    return player;
  }

  public Menu menu() {

    return menu;
  }

  public Integer page() {

    return page;
  }

  public ActionType action() {

    return actionType;
  }

  @Override
  public String toString() {

    return "MenuClickHandler{" +
           "slot=" + slot +
           ", menu=" + menu.getName() +
           ", page=" + page +
           ", actionType=" + actionType.name() +
           '}';
  }
}