package net.tnemc.menu.core.constraints.impl;
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

import net.tnemc.menu.core.constraints.Constraint;

/**
 * Represents a {@link Constraint} of the {@link Double} type.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public interface DoubleConstraint extends Constraint<Double> {

  @Override
  default Double convert(final String value) {
    if(value == null) return defaultValue();
    try {
      return Double.parseDouble(value);
    } catch(Exception ignore) {
      return defaultValue();
    }
  }

  @Override
  default boolean validate(final String value) {
    try {
      Double.parseDouble(value);
      return true;
    } catch(Exception ignore) {
      return false;
    }
  }
}
