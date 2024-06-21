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

import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class BukkitPlayer implements MenuPlayer {

  protected OfflinePlayer player;
  protected final JavaPlugin plugin;

  public BukkitPlayer(OfflinePlayer player, JavaPlugin plugin) {
    this.player = player;
    this.plugin = plugin;
  }

  /**
   * The {@link UUID unique identifier} for this player.
   *
   * @return The {@link UUID} for the player.
   */
  @Override
  public UUID identifier() {
    return player.getUniqueId();
  }

  /**
   * The {@link PlayerInventory} for this player.
   *
   * @return The {@link PlayerInventory} for this player.
   */
  @Override
  public BukkitInventory inventory() {
    return new BukkitInventory(identifier(), plugin);
  }

  /**
   * Used to determine if this player has the specified permission node.
   *
   * @param permission The node to check for.
   *
   * @return True if the player has the permission, otherwise false.
   */
  @Override
  public boolean hasPermission(String permission) {
    if(player.getPlayer() != null) {
      return player.getPlayer().hasPermission(permission);
    }
    return false;
  }

  /**
   * Used to send a message to this player.
   *
   * @param message The message.
   */
  @Override
  public void message(String message) {
    if(player.getPlayer() != null) {
      player.getPlayer().sendMessage(message);
    }
  }
}