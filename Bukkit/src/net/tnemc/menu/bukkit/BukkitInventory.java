package net.tnemc.menu.bukkit;

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

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.Page;
import net.tnemc.menu.core.callbacks.page.PageOpenCallback;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import net.tnemc.menu.core.icon.Icon;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

public class BukkitInventory implements PlayerInventory<Inventory> {

  protected final UUID id;
  protected final JavaPlugin plugin;

  public BukkitInventory(final UUID id, final JavaPlugin plugin) {

    this.id = id;
    this.plugin = plugin;
  }

  /**
   * The player associated with this inventory provider.
   *
   * @return The {@link UUID} for the player for this {@link PlayerInventory}
   */
  @Override
  public UUID player() {

    return id;
  }

  /**
   * Builds an inventory object from a menu.
   *
   * @param menu The menu to build.
   * @param page The page to use during the build.
   *
   * @return The built inventory.
   */
  @Override
  public Inventory build(final MenuPlayer player, final Menu menu, final int page) {

    final Inventory inventory = Bukkit.createInventory(null, menu.getRows() * 9, menu.getTitle());

    if(menu.pages.containsKey(page)) {

      final Page pageObj = menu.pages.get(page);
      if(pageObj.getOpen() != null) {
        pageObj.getOpen().accept(new PageOpenCallback(pageObj, player));
      }

      for(final Map.Entry<Integer, Icon> entry : menu.pages.get(page).getIcons().entrySet()) {

        inventory.setItem(entry.getKey(), (ItemStack)entry.getValue().getItem(player).locale());
      }

    }

    return inventory;
  }

  /**
   * Used to open the provided inventory for this player.
   *
   * @param inventory The inventory to open.
   */
  @Override
  public void openInventory(final Inventory inventory) {

    final OfflinePlayer player = Bukkit.getOfflinePlayer(player());
    if(player.getPlayer() != null) {
      Bukkit.getScheduler().runTask(plugin, ()->{
        player.getPlayer().openInventory(inventory);
      });
    }
  }

  /**
   * Used to update the inventory the player is in with a new item for a specific slot.
   *
   * @param slot The slot to update.
   * @param item The item to update the specified slot with.
   */
  @Override
  public void updateInventory(final int slot, final AbstractItemStack<?> item) {

    final OfflinePlayer player = Bukkit.getOfflinePlayer(player());
    if(player.getPlayer() != null) {
      Bukkit.getScheduler().runTask(plugin, ()->player.getPlayer().getOpenInventory().setItem(slot, (ItemStack)item.locale()));
    }
  }

  /**
   * Used to close the player's currently open inventory.
   */
  @Override
  public void close() {

    final OfflinePlayer player = Bukkit.getOfflinePlayer(player());
    if(player.getPlayer() != null) {
      Bukkit.getScheduler().runTask(plugin, ()->player.getPlayer().closeInventory());
    }
  }
}
