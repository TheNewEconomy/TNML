package net.tnemc.menu.sponge7;

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
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import net.tnemc.menu.core.icon.Icon;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SpongeInventory implements PlayerInventory<Inventory> {

  protected final UUID id;
  protected final Plugin plugin;//because sponge requires this for a lot of useless things.

  public SpongeInventory(UUID id, Plugin plugin) {
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
  public Inventory build(Menu menu, int page) {
    Inventory inventory = Inventory
        .builder().property(InventoryTitle.of(Text.of(menu.getTitle())))
        .property(InventoryDimension.of(9, menu.getSize())).build(plugin);

    for(Map.Entry<Integer, Icon> entry : menu.getPages().get(page).getIcons().entrySet()) {

      final int y = entry.getKey() / 9;
      final int x = (entry.getKey() % 9);

      inventory.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(x, y)))
               .set((ItemStack)entry.getValue().getItem().locale());
    }

    return inventory;
  }

  /**
   * Used to open the provided inventory for this player.
   *
   * @param inventory The inventory to open.
   */
  @Override
  public void openInventory(Inventory inventory) {
    final Optional<Player> player = Sponge.getServer().getPlayer(id);

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
    final Optional<Player> player = Sponge.getServer().getPlayer(id);
    if(player.isPresent()) {

      final int y = slot / 9;
      final int x = (slot % 9);

      player.get().getInventory().query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(x, y)))
          .set((ItemStack)item.locale());
    }
  }
}
