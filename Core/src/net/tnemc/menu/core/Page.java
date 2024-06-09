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

import net.tnemc.menu.core.callbacks.page.PageOpenCallback;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.Icon;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Page
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class Page {

  private final int pageNumber;

  protected final Map<Integer, Icon> icons = new HashMap<>();

  protected Function<MenuClickHandler, Boolean> clickHandler;

  protected Consumer<PageOpenCallback> open;

  public Page(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int number() {
    return pageNumber;
  }

  /**
   * Handles a click action for a specific viewer identified by its UUID.
   *
   * @param handler The {@link  MenuClickHandler} for the click.
   * @return {@code true} if the click action is blocked, indicating that it should be prevented,
   *         {@code false} if the click action is allowed to proceed.
   */
  public boolean onClick(final MenuClickHandler handler) {
    if(icons.containsKey(handler.slot().slot())) {
      if(!icons.get(handler.slot().slot()).onClick(handler)) {
        return true;
      }
    }


    if(clickHandler != null) {
      return clickHandler.apply(handler);
    }

    return icons.containsKey(handler.slot().slot());
  }

  public void addIcon(final Icon icon) {
    icons.put(icon.slot(), icon);
  }

  public Map<Integer, Icon> getIcons() {
    return icons;
  }

  public Consumer<PageOpenCallback> getOpen() {
    return open;
  }

  public void setOpen(Consumer<PageOpenCallback> open) {
    this.open = open;
  }

  public Function<MenuClickHandler, Boolean> getClickHandler() {
    return clickHandler;
  }

  public void setClickHandler(Function<MenuClickHandler, Boolean> clickHandler) {
    this.clickHandler = clickHandler;
  }
}