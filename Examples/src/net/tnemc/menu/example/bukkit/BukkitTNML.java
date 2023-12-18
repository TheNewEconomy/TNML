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

import net.tnemc.item.bukkit.BukkitItemStack;
import net.tnemc.menu.bukkit.BukkitMenuHandler;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.MenuHandler;
import net.tnemc.menu.core.Page;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.utils.SlotPos;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * BukkitTNML
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class BukkitTNML extends JavaPlugin implements Listener {

  private MenuHandler menu;

  @Override
  public void onEnable() {

    Bukkit.getPluginManager().registerEvents(this, this);

    this.menu = new BukkitMenuHandler(this, true);

    Menu exampleMenu = new Menu();
    exampleMenu.setName("example");
    exampleMenu.setTitle("Example GUI");
    exampleMenu.setRows(5);

    //Icons
    final BukkitItemStack stack = new BukkitItemStack().display("Example Icon").of("RED_WOOL", 1);
    final BukkitItemStack stack2 = new BukkitItemStack().display("Example Icon2").of("GREEN_WOOL", 1);

    final Icon icon = new Icon(stack, null);
    icon.setSlot(new SlotPos(2, 3));

    final Icon icon2 = new Icon(stack2, null);
    icon2.setSlot(new SlotPos(2, 6));

    //Pages
    final Page page = new Page();
    page.addIcon(icon);
    page.addIcon(icon2);

    exampleMenu.pages.put(1, page);
  }

  @EventHandler
  public void onJoin(final PlayerJoinEvent event) {
    MenuManager.instance().open(event.getPlayer().getUniqueId());
  }
}