package net.tnemc.menu.minestom;

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

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import net.tnemc.menu.core.icon.Icon;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class MinestomInventory implements PlayerInventory<Inventory> {

  private final UUID id;

  public MinestomInventory(UUID id) {
    this.id = id;
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
  public Inventory build(Menu menu, int page) {
    Inventory inventory = new Inventory(typeFromSize(menu.getSize()), menu.getTitle());

    for(Map.Entry<Integer, Icon> entry : menu.getPages().get(page).getIcons().entrySet()) {

      inventory.setItemStack(entry.getKey(), (ItemStack)entry.getValue().getItem().locale());
    }
    return null;
  }

  /**
   * Used to open the provided inventory for this player.
   *
   * @param inventory The inventory to open.
   */
  @Override
  public void openInventory(Inventory inventory) {
    Optional<Player> player = Optional.ofNullable(MinecraftServer.getConnectionManager().getPlayer(id));

    player.ifPresent(value->value.openInventory(inventory));
  }

  /**
   * Used to update the menu the player is in with a new item for a specific slot.
   *
   * @param slot The slot to update.
   * @param item The item to update the specified slot with.
   */
  @Override
  public void updateInventory(int slot, AbstractItemStack<?> item) {
    Optional<Player> player = Optional.ofNullable(MinecraftServer.getConnectionManager().getPlayer(id));

    player.ifPresent(value->value.getInventory().setItemStack(slot, (ItemStack)item.locale()));

  }

  private InventoryType typeFromSize(final int size) {
    return switch(size) {
      case 1 -> InventoryType.CHEST_1_ROW;
      case 2 -> InventoryType.CHEST_2_ROW;
      case 3 -> InventoryType.CHEST_3_ROW;
      case 4 -> InventoryType.CHEST_4_ROW;
      case 5 -> InventoryType.CHEST_5_ROW;
      default -> InventoryType.CHEST_6_ROW;
    };
  }
}