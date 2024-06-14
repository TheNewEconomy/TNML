package net.tnemc.menu.sponge7;
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
import net.tnemc.menu.core.MenuHandler;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.sponge7.listeners.Sponge7ChatListener;
import net.tnemc.menu.sponge7.listeners.Sponge7InteractInventoryListener;
import net.tnemc.menu.sponge7.listeners.Sponge7InventoryClickListener;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.plugin.Plugin;

/**
 * SpongeMenuHandler
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class Sponge7MenuHandler implements MenuHandler {

  protected final Plugin plugin;
  private final MenuManager manager;

  public Sponge7MenuHandler(final Plugin plugin, final boolean registerListeners) {
    this.plugin = plugin;
    this.manager = new MenuManager();
    //TODO: TNIL for sponge 7
    //this.manager.setHelper(new SpongeHelper());

    if(registerListeners) {
      registerListeners();
    }
  }

  public void registerListeners() {
    Sponge.getEventManager().registerListeners(plugin, new Sponge7ChatListener(plugin));
    Sponge.getEventManager().registerListeners(plugin, new Sponge7InteractInventoryListener(plugin));
    Sponge.getEventManager().registerListeners(plugin, new Sponge7InventoryClickListener(plugin));
  }

  @Override
  public AbstractItemStack<?> stackBuilder() {
    //TODO: TNIL for sponge 7
    return null;
  }

  public MenuManager getManager() {
    return manager;
  }
}