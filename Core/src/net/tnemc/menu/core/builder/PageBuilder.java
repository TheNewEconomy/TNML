package net.tnemc.menu.core.builder;

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
import net.tnemc.menu.core.callbacks.page.PageSlotClickCallback;
import net.tnemc.menu.core.icon.Icon;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class PageBuilder {

  private final ConcurrentHashMap<Integer, Icon> icons = new ConcurrentHashMap<>();

  private Consumer<PageSlotClickCallback> click;

  private int id;

  public static PageBuilder of(final int id) {
    return new PageBuilder(id);
  }

  public PageBuilder(int id) {
    this.id = id;
  }

  public PageBuilder withID(int id) {
    this.id = id;
    return this;
  }

  public PageBuilder click(Consumer<PageSlotClickCallback> callback) {
    this.click = callback;
    return this;
  }

  public PageBuilder withIcon(final Icon icon) {
    this.icons.put(icon.getSlot(), icon);
    return this;
  }

  public Page create() {
    Page page = new Page(id);
    page.setClick(click);
    page.getIcons().putAll(icons);

    return page;
  }
}