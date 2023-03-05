package net.tnemc.menu.core.icon.constraints;

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

import net.tnemc.menu.core.constraints.ConstraintHolder;
import net.tnemc.menu.core.constraints.impl.StringConstraint;
import net.tnemc.menu.core.icon.Icon;

/**
 * Represents the String constraints for an {@link Icon}
 *
 * @see ConstraintHolder
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public enum IconStringConstraints implements StringConstraint {

  ICON_PERMISSION {
    @Override
    public String identifier() {
      return "ICON_PERMISSION";
    }

    @Override
    public String defaultValue() {
      return "";
    }
  },
  ICON_MESSAGE {

    @Override
    public String identifier() {
      return "ICON_MESSAGE";
    }

    @Override
    public String defaultValue() {
      return "";
    }
  }
}