package net.tnemc.menu.core.constraints;

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

import java.util.Map;

/**
 * Represents an object that is able to hold {@link Constraint constraints}.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public interface ConstraintHolder {

  Map<String, String> constraints();

  default <T> void addConstraint(final Constraint<T> constraint, final T value) {

    constraints().put(constraint.identifier(), constraint.asString(value));
  }

  default <T> T getConstraint(final Constraint<T> constraint) {

    return constraint.convert(constraints().get(constraint.identifier()));
  }

  default void removeConstraint(final Constraint<?> constraint) {

    constraints().remove(constraint.identifier());
  }
}