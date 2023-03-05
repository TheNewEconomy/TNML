package net.tnemc.menu.core.icon;

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

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.Page;
import net.tnemc.menu.core.callbacks.icon.IconClickCallback;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.constraints.ConstraintHolder;
import net.tnemc.menu.core.icon.constraints.IconStringConstraints;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Represents an Icon, which is a singular item in an inventory-based {@link Menu}.
 *
 * @see Menu
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class Icon implements ConstraintHolder {

  protected final Map<String, String> constraints = new HashMap<>();

  protected final List<IconAction> actions = new LinkedList<>();

  protected int slot;
  protected AbstractItemStack<?> item;

  //Callbacks
  protected Consumer<IconClickCallback> click;

  public Icon(int slot, AbstractItemStack<?> item) {
    this.slot = slot;
    this.item = item;
  }

  public int getSlot() {
    return slot;
  }

  public void setSlot(int slot) {
    this.slot = slot;
  }

  public AbstractItemStack<?> getItem() {
    return item;
  }

  public void setItem(AbstractItemStack<?> item) {
    this.item = item;
  }

  public Consumer<IconClickCallback> getClick() {
    return click;
  }

  public void setClick(Consumer<IconClickCallback> click) {
    this.click = click;
  }

  public List<IconAction> getActions() {
    return actions;
  }

  @Override
  public Map<String, String> constraints() {
    return constraints;
  }


  public boolean onClick(ActionType type, MenuPlayer player, Menu menu, Page page) {

    final String permission = getConstraint(IconStringConstraints.ICON_PERMISSION);
    if(!permission.equalsIgnoreCase("") && !player.hasPermission(permission)) {
      return true;
    }

    for(IconAction action : actions) {

      if(action.type().equals(ActionType.ANY) || action.type().name().equalsIgnoreCase(type.name())) {
        action.onPerform(menu, page, player, this);

        if(!action.continueOther()) {
          break;
        }
      }
    }

    if(click != null) {
      click.accept(new IconClickCallback(type, menu, page, player, this));
    }

    final String message = getConstraint(IconStringConstraints.ICON_MESSAGE);
    if(!message.equalsIgnoreCase("")) {
      player.message(message);
    }

    return true;
  }
}