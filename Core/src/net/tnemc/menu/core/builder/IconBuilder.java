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

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.callbacks.icon.IconClickCallback;
import net.tnemc.menu.core.constraints.Constraint;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.icon.IconAction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class IconBuilder {

  private final Map<String, String> constraints = new HashMap<>();

  private final List<IconAction> actions = new LinkedList<>();

  private int slot;
  private AbstractItemStack<?> item;

  //Callbacks
  protected Consumer<IconClickCallback> click;

  public static IconBuilder of(AbstractItemStack<?> item) {
    return new IconBuilder(item);
  }

  public IconBuilder(AbstractItemStack<?> item) {
    this.item = item;
  }

  public IconBuilder withSlot(int slot) {
    this.slot = slot;
    return this;
  }

  public IconBuilder withItem(AbstractItemStack<?> item) {
    this.item = item;
    return this;
  }

  public IconBuilder click(Consumer<IconClickCallback> callback) {
    this.click = callback;
    return this;
  }

  public <TYPE> IconBuilder withConstraint(Constraint<TYPE> constraint, TYPE value) {
    this.constraints.put(constraint.identifier(), constraint.asString(value));
    return this;
  }

  public IconBuilder withAction(IconAction action) {
    this.actions.add(action);
    return this;
  }

  public Icon create() {
    Icon icon = new Icon(slot, item);
    icon.constraints().putAll(constraints);
    icon.getActions().addAll(actions);
    icon.setClick(click);
    return icon;
  }
}