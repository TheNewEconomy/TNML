package net.tnemc.menu.core.callbacks.menu;

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

import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.Page;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.utils.CloseType;

/**
 * MenuCloseCallback
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class MenuCloseCallback extends MenuCallback {

  protected final Page page;

  protected final MenuPlayer player;

  protected final CloseType type;

  public MenuCloseCallback(Menu menu, Page page, MenuPlayer player, CloseType type) {
    super(menu);
    this.page = page;
    this.player = player;
    this.type = type;
  }

  public Page getPage() {
    return page;
  }

  public MenuPlayer getPlayer() {
    return player;
  }

  public CloseType getType() {
    return type;
  }
}