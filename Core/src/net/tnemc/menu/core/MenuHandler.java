package net.tnemc.menu.core;

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

/**
 * MenuHandler
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public interface MenuHandler {

  /**
   * Used to register the listeners automatically without having to manually register each TNML
   * Listener.
   */
  void registerListeners();

  /**
   * This method is used to get an empty {@link AbstractItemStack item stack} object for the
   * platform to help build a new item stack for an icon.
   *
   * @return an instance of AbstractItemStack representing an in-progress item builder for the
   * platform.
   */
  AbstractItemStack<?> stackBuilder();
}