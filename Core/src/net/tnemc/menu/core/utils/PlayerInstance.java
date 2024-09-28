package net.tnemc.menu.core.utils;
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

import net.tnemc.menu.core.icon.Icon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * PlayerInstance
 *
 * @author creatorfromhell
 * @since 1.6.0.0
 */
public class PlayerInstance {

  protected final Map<Integer, Icon> icons = new HashMap<>();

  final UUID player;

  public PlayerInstance(final UUID player) {

    this.player = player;
  }

  public Map<Integer, Icon> getIcons() {

    return icons;
  }

  public void addIcon(final Icon icon) {

    icons.put(icon.slot(), icon);
  }
}