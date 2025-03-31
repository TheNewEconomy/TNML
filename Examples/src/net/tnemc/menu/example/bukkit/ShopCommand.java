package net.tnemc.menu.example.bukkit;
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

import net.tnemc.menu.bukkit.BukkitPlayer;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.viewer.MenuViewer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * ShopCommand
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class ShopCommand implements CommandExecutor {

  private final BukkitTNML plugin;

  public ShopCommand(final BukkitTNML plugin) {

    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only players can use this command.");
      return true;
    }

    final Player player = (Player) sender;

    final MenuViewer viewer = new MenuViewer(player.getUniqueId());
    viewer.setMenu("example");
    MenuManager.instance().addViewer(viewer);

    final BukkitPlayer bukkitPlayer = new BukkitPlayer(player, plugin);
    MenuManager.instance().open("example", 1, bukkitPlayer);
    player.sendMessage("§aWelcome to the shop!");
    // You can open a GUI here later
    return true;
  }
}
