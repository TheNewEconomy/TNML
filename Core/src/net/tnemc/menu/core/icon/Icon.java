package net.tnemc.menu.core.icon;

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

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.constraints.ConstraintHolder;
import net.tnemc.menu.core.icon.action.IconAction;
import net.tnemc.menu.core.utils.SlotPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Icon
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class Icon implements ConstraintHolder {

  protected final List<IconAction> actions = new LinkedList<>();

  protected final Map<String, String> constraints = new HashMap<>();

  protected final AbstractItemStack<?> item;

  protected int slot;

  protected final Function<MenuPlayer, AbstractItemStack<?>> itemProvider;

  public Icon(@NotNull final AbstractItemStack<?> item, @Nullable Function<MenuPlayer, AbstractItemStack<?>> itemProvider) {
    this.item = item;
    this.itemProvider = itemProvider;
  }

  public List<IconAction> getActions() {
    return actions;
  }

  //TODO: Icon click actions.

  public AbstractItemStack<?> getItem(@Nullable MenuPlayer player) {
    if(player != null && itemProvider != null) return itemProvider.apply(player);
    return item;
  }

  public int slot() {
    return slot;
  }

  public void setSlot(int slot) {
    this.slot = slot;
  }

  public void setSlot(final SlotPos slotPos) {
    this.slot = slotPos.slot();
  }

  @Override
  public Map<String, String> constraints() {
    return constraints;
  }
}