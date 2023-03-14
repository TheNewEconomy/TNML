package net.tnemc.menu.core.callbacks.page;

/*
 * The New Menu Library
 *
 * Copyright (C) 2022 - 2023 Daniel "creatorfromhell" Vidmar
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

import net.tnemc.menu.core.page.Page;
import net.tnemc.menu.core.compatibility.MenuPlayer;

/**
 * Represents a callback, which is called when a page is opened in a menu. This could be due to a
 * switch, or due to the menu opening.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class PageOpenCallback extends PageCallback {

  protected final MenuPlayer player;

  public PageOpenCallback(Page page, MenuPlayer player) {
    super(page);
    this.player = player;
  }

  public MenuPlayer getPlayer() {
    return player;
  }
}