package net.tnemc.menu.core.constraints.impl;

/*
 * The New Menu Library
 *
 * Copyright (C) 2022 - 2024 Daniel "creatorfromhell" Vidmar
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

import net.tnemc.menu.core.constraints.Constraint;

/**
 * Represents a {@link Constraint} of the {@link Integer} type.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public interface IntConstraint extends Constraint<Integer> {

  @Override
  default Integer convert(final String value) {

    if(value == null) return defaultValue();
    try {
      return Integer.parseInt(value);
    } catch(Exception ignore) {
      return defaultValue();
    }
  }

  @Override
  default boolean validate(final String value) {

    try {
      Integer.parseInt(value);
      return true;
    } catch(Exception ignore) {
      return false;
    }
  }
}