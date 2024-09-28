package net.tnemc.menu.core.builder;

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
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.constraints.Constraint;
import net.tnemc.menu.core.handlers.MenuClickHandler;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.icon.action.IconAction;
import net.tnemc.menu.core.icon.impl.StateIcon;
import net.tnemc.menu.core.utils.SlotPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Builder class for creating instances of the {@link Icon} and {@link StateIcon} classes.
 *
 * @author creatorfromhell
 * @see Icon
 * @see StateIcon
 * @since 1.5.0.0
 */
public class IconBuilder {

  private final AbstractItemStack<?> item;
  private Function<MenuPlayer, AbstractItemStack<?>> itemProvider;
  private int slot;
  private Consumer<MenuClickHandler> click;
  private final List<IconAction> actions = new LinkedList<>();

  protected final Map<String, String> constraints = new HashMap<>();
  private final Map<String, AbstractItemStack<?>> states = new HashMap<>();
  private String stateID;
  private String defaultState;
  private Function<String, String> stateHandler;

  /**
   * Constructs an {@code IconBuilder} with the specified item.
   *
   * @param item The {@link AbstractItemStack} to be used in the icon.
   */
  public IconBuilder(@NotNull final AbstractItemStack<?> item) {

    this.item = item;
  }

  /**
   * Sets the item provider function for the icon.
   *
   * @param itemProvider The function providing the item for the icon based on the
   *                     {@link MenuPlayer}.
   *
   * @return This {@code IconBuilder} instance for method chaining.
   */
  public IconBuilder withItemProvider(@Nullable final Function<MenuPlayer, AbstractItemStack<?>> itemProvider) {

    this.itemProvider = itemProvider;
    return this;
  }

  /**
   * Sets the slot for the icon.
   *
   * @param slot The slot position for the icon.
   *
   * @return This {@code IconBuilder} instance for method chaining.
   */
  public IconBuilder withSlot(final int slot) {

    this.slot = slot;
    return this;
  }

  /**
   * Sets the slot for the icon using a {@link SlotPos} object.
   *
   * @param slotPos The {@link SlotPos} representing the slot position for the icon.
   *
   * @return This {@code IconBuilder} instance for method chaining.
   */
  public IconBuilder withSlot(final SlotPos slotPos) {

    this.slot = slotPos.slot();
    return this;
  }

  /**
   * Sets the click handler for the icon.
   *
   * @param click The click handler to be executed when the icon is clicked.
   *
   * @return This {@code IconBuilder} instance for method chaining.
   */
  public IconBuilder withClick(final Consumer<MenuClickHandler> click) {

    this.click = click;
    return this;
  }

  /**
   * Adds a constraint to the icon.
   *
   * @param key   The key of the constraint.
   * @param value The value of the constraint.
   *
   * @return This {@code IconBuilder} instance for method chaining.
   */
  public IconBuilder withConstraint(final Constraint<?> key, final String value) {

    this.constraints.put(key.identifier(), value);
    return this;
  }

  /**
   * Adds actions to the list of actions for the icon.
   *
   * @param actions The {@link IconAction actions} to be added to the icon.
   *
   * @return This {@code IconBuilder} instance for method chaining.
   */
  public IconBuilder withActions(final IconAction... actions) {

    this.actions.addAll(Arrays.asList(actions));
    return this;
  }

  /**
   * Sets the state ID for the StateIcon.
   *
   * @param stateID The state ID for the StateIcon.
   *
   * @return This {@code IconBuilder} instance for method chaining.
   */
  public IconBuilder withStateID(final String stateID) {

    this.stateID = stateID;
    return this;
  }

  /**
   * Sets the default state for the StateIcon.
   *
   * @param defaultState The default state for the StateIcon.
   *
   * @return This {@code IconBuilder} instance for method chaining.
   */
  public IconBuilder withDefaultState(final String defaultState) {

    this.defaultState = defaultState;
    return this;
  }

  /**
   * Sets the state handler for the StateIcon.
   *
   * @param stateHandler The state handler for the StateIcon.
   *
   * @return This {@code IconBuilder} instance for method chaining.
   */
  public IconBuilder withStateHandler(final Function<String, String> stateHandler) {

    this.stateHandler = stateHandler;
    return this;
  }

  /**
   * Adds a state to the StateIcon.
   *
   * @param stateID The state ID.
   * @param item    The item associated with the state.
   *
   * @return This {@code IconBuilder} instance for method chaining.
   */
  public IconBuilder withState(final String stateID, final AbstractItemStack<?> item) {

    this.states.put(stateID, item);
    return this;
  }

  /**
   * Builds and returns the {@link Icon} or {@link StateIcon} instance based on the provided
   * configuration.
   *
   * @return The constructed {@code Icon} or {@code StateIcon} instance.
   */
  public Icon build() {

    final Icon icon = (stateID != null && defaultState != null && stateHandler != null)?
                      new StateIcon(item, itemProvider, stateID, defaultState, stateHandler) :
                      new Icon(item, itemProvider);

    if(icon instanceof StateIcon stateIcon) {
      stateIcon.getStates().putAll(states);
    }

    icon.constraints().putAll(constraints);
    icon.setSlot(slot);
    icon.setClick(click);
    actions.forEach(icon::addAction);
    return icon;
  }
}