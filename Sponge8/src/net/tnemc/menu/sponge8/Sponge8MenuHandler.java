package net.tnemc.menu.sponge8;
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
import net.tnemc.menu.core.MenuHandler;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.sponge8.listeners.Sponge8ChatListener;
import net.tnemc.menu.sponge8.listeners.Sponge8InteractInventoryListener;
import net.tnemc.menu.sponge8.listeners.Sponge8InventoryClickListener;
import net.tnemc.sponge.SpongeHelper;
import net.tnemc.sponge.SpongeItemStack;
import org.spongepowered.api.Sponge;
import org.spongepowered.plugin.PluginContainer;

/**
 * SpongeMenuHandler
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class Sponge8MenuHandler implements MenuHandler {

  protected final PluginContainer container;
  private final MenuManager manager;

  public Sponge8MenuHandler(final PluginContainer container, final boolean registerListeners) {
    this.container = container;
    this.manager = new MenuManager();
    this.manager.setHelper(new SpongeHelper());

    if(registerListeners) {
      registerListeners();
    }
  }

  public void registerListeners() {
    Sponge.eventManager().registerListeners(container, new Sponge8ChatListener(container));
    Sponge.eventManager().registerListeners(container, new Sponge8InteractInventoryListener(container));
    Sponge.eventManager().registerListeners(container, new Sponge8InventoryClickListener(container));
  }

  @Override
  public AbstractItemStack<?> stackBuilder() {
    return new SpongeItemStack();
  }

  public MenuManager getManager() {
    return manager;
  }
}