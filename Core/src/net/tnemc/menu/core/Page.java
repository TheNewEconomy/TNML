package net.tnemc.menu.core;

/*
 * The New Economy
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

import net.tnemc.menu.core.callbacks.page.PageSlotClickCallback;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.icon.Icon;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Represents a page inside a {@link Menu}.
 *
 * @see Menu
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class Page {

  protected final ConcurrentHashMap<Integer, Icon> icons = new ConcurrentHashMap<>();

  protected Consumer<PageSlotClickCallback> click;

  private int id;

  public Page(int id) {
    this.id = id;
  }

  public ConcurrentHashMap<Integer, Icon> getIcons() {
    return icons;
  }

  public boolean onClick(ActionType type, MenuPlayer player, Menu menu, int slot) {
    boolean result = true;

    if(icons.containsKey(slot)) {
      result = icons.get(slot).onClick(type, player, menu, this);
    }

    if(result && click != null) {
      click.accept(new PageSlotClickCallback(menu, this, type, player, slot));
    }

    return result;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Consumer<PageSlotClickCallback> getClick() {
    return click;
  }

  public void setClick(Consumer<PageSlotClickCallback> click) {
    this.click = click;
  }
}