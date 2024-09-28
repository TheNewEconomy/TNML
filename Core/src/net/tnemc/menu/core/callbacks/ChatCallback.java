package net.tnemc.menu.core.callbacks;

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

import net.tnemc.menu.core.compatibility.MenuPlayer;

/**
 * ChatCallback
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class ChatCallback {

  private final MenuPlayer player;
  private final String message;

  private String menu;
  private int page;

  public ChatCallback(final MenuPlayer player, final String message, final String menu, final int page) {

    this.player = player;
    this.message = message;
    this.menu = menu;
    this.page = page;
  }

  public MenuPlayer getPlayer() {

    return player;
  }

  public String getMessage() {

    return message;
  }

  public String getMenu() {

    return menu;
  }

  public void setMenu(final String menu) {

    this.menu = menu;
  }

  public int getPage() {

    return page;
  }

  public void setPage(final int page) {

    this.page = page;
  }
}