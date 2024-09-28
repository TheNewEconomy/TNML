package net.tnemc.menu.folia;

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
import net.tnemc.item.paper.PaperHelper;
import net.tnemc.item.paper.PaperItemStack;
import net.tnemc.menu.core.MenuHandler;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.folia.listener.FoliaChatListener;
import net.tnemc.menu.folia.listener.FoliaInventoryClickListener;
import net.tnemc.menu.folia.listener.FoliaInventoryCloseListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * FoliaMenuHandler
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class FoliaMenuHandler implements MenuHandler {

  private final JavaPlugin plugin;

  public FoliaMenuHandler(final JavaPlugin plugin, final boolean registerListeners) {

    this.plugin = plugin;
    MenuManager.instance().setHelper(new PaperHelper());

    if(registerListeners) {
      registerListeners();
    }
  }

  public void registerListeners() {

    Bukkit.getPluginManager().registerEvents(new FoliaChatListener(plugin), plugin);
    Bukkit.getPluginManager().registerEvents(new FoliaInventoryClickListener(plugin), plugin);
    Bukkit.getPluginManager().registerEvents(new FoliaInventoryCloseListener(plugin), plugin);
  }

  @Override
  public AbstractItemStack<?> stackBuilder() {

    return new PaperItemStack();
  }

  public MenuManager getManager() {

    return MenuManager.instance();
  }
}