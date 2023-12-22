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

import net.tnemc.item.AbstractItemStack;
import net.tnemc.menu.bukkit.BukkitMenuHandler;
import net.tnemc.menu.bukkit.BukkitPlayer;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.MenuHandler;
import net.tnemc.menu.core.Page;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.icon.action.impl.ChatAction;
import net.tnemc.menu.core.icon.action.impl.SwitchMenuAction;
import net.tnemc.menu.core.icon.constraints.IconStringConstraints;
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

    final Menu exampleMenu = new Menu();
    exampleMenu.setName("example");
    exampleMenu.setTitle("Example GUI");
    exampleMenu.setRows(5);

    final Menu exampleMenu2 = new Menu();
    exampleMenu2.setName("example2");
    exampleMenu2.setTitle("Example2 GUI");
    exampleMenu2.setRows(3);

    //Icons
    final AbstractItemStack<?> stack = menu.stackBuilder().display("Example Icon").of("RED_WOOL", 1);
    final AbstractItemStack<?> stack2 = menu.stackBuilder().display("Example Icon2").of("GREEN_WOOL", 1);
    final AbstractItemStack<?> stack3 = menu.stackBuilder().display("Example2 Icon").of("STONE", 1);

    final Icon icon = new Icon(stack, null);
    icon.setSlot(new SlotPos(2, 3));
    icon.addConstraint(IconStringConstraints.ICON_MESSAGE, "You switched a menu!");
    icon.getActions().add(new SwitchMenuAction("example2"));

    final Icon icon2 = new Icon(stack2, null);
    icon2.setSlot(new SlotPos(2, 6));
    icon2.addConstraint(IconStringConstraints.ICON_MESSAGE, "Please type: hello");
    icon2.getActions().add(new ChatAction((callback)->{

      if(!callback.getMessage().equalsIgnoreCase("hello")) {
        callback.getPlayer().message("Invalid Input! Type: hello");
        return false;
      }

      System.out.println("Chat Input: " + callback.getMessage());

      return true;
    }));

    final Icon icon3 = new Icon(stack3, null);
    icon3.setSlot(new SlotPos(2, 5));
    icon3.getActions().add(new SwitchMenuAction("example"));
    icon3.addConstraint(IconStringConstraints.ICON_MESSAGE, "You switched a menu and found the new button!");

    //Pages
    final Page page = new Page();
    page.addIcon(icon);
    page.addIcon(icon2);

    exampleMenu.pages.put(1, page);

    //Pages
    final Page page2 = new Page();
    page2.addIcon(icon3);

    exampleMenu2.pages.put(1, page2);

    MenuManager.instance().addMenu(exampleMenu);
    MenuManager.instance().addMenu(exampleMenu2);
  }

  @EventHandler
  public void onJoin(final PlayerJoinEvent event) {

    final BukkitPlayer player = new BukkitPlayer(event.getPlayer(), this);
    MenuManager.instance().open("example", 1, player);
  }
}