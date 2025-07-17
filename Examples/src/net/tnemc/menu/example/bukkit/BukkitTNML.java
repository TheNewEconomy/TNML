package net.tnemc.menu.example.bukkit;
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

import net.kyori.adventure.text.Component;
import net.tnemc.menu.bukkit.BukkitMenuHandler;
import net.tnemc.menu.bukkit.BukkitPlayer;
import net.tnemc.menu.bukkit.listener.BukkitChatListener;
import net.tnemc.menu.bukkit.listener.BukkitInventoryClickListener;
import net.tnemc.menu.bukkit.listener.BukkitInventoryCloseListener;
import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.MenuHandler;
import net.tnemc.menu.core.builder.IconBuilder;
import net.tnemc.menu.core.builder.MenuBuilder;
import net.tnemc.menu.core.builder.PageBuilder;
import net.tnemc.menu.core.icon.action.impl.SwitchMenuAction;
import net.tnemc.menu.core.icon.constraints.IconStringConstraints;
import net.tnemc.menu.core.manager.MenuManager;
import net.tnemc.menu.core.utils.SlotPos;
import net.tnemc.menu.core.viewer.MenuViewer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.Optional;

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

    getCommand("shop").setExecutor(new ShopCommand(this));

    Bukkit.getPluginManager().registerEvents(this, this);

    Bukkit.getPluginManager().registerEvents(new BukkitChatListener(this), this);
    Bukkit.getPluginManager().registerEvents(new BukkitInventoryClickListener(this), this);
    Bukkit.getPluginManager().registerEvents(new BukkitInventoryCloseListener(this), this);

    this.menu = new BukkitMenuHandler(this, true);

    /*final Menu exampleMenu = new Menu();
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

    //StateIconItems
    final AbstractItemStack<?> defaultStack = menu.stackBuilder().display("Default State").of("BLACK_WOOL", 1);
    final AbstractItemStack<?> state1Stack = menu.stackBuilder().display("State 1 State").of("BROWN_WOOL", 1);
    final AbstractItemStack<?> state2Stack = menu.stackBuilder().display("State 2 State").of("BLUE_WOOL", 1);
    final AbstractItemStack<?> state3Stack = menu.stackBuilder().display("State 3 State").of("GREEN_WOOL", 1);

    final Icon icon = new Icon(stack, null);
    icon.setSlot(new SlotPos(2, 3));
    icon.addConstraint(IconStringConstraints.ICON_MESSAGE, "You switched a menu!");
    icon.getActions().add(new SwitchMenuAction("example2"));

    final Icon icon2 = new Icon(stack2, null);
    icon2.setSlot(new SlotPos(2, 6));
    icon2.addConstraint(IconStringConstraints.ICON_MESSAGE, "Please type: hello");
    icon2.getActions().add(new ChatAction((callback)->{

      final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(callback.getPlayer().identifier());
      if(viewer.isPresent()) {
        viewer.get().addData("example-data", callback.getMessage());
      }

      return true;
    }));

    final Icon icon3 = new Icon(stack3, null);
    icon3.setSlot(new SlotPos(2, 5));
    icon3.getActions().add(new SwitchMenuAction("example"));
    icon3.addConstraint(IconStringConstraints.ICON_MESSAGE, "You switched a menu and found the new button!");

    final Icon icon4 = new Icon(stack3, (player)->{
      final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(player.identifier());
      if(viewer.isPresent()) {
        final Optional<Object> display = viewer.get().findData("example-data");
        if(display.isPresent()) {
          return menu.stackBuilder().display((String)display.get()).of("GRASS", 1);
        }
      }
      return stack3;
    });
    icon4.setSlot(new SlotPos(2, 2));

    final StateIcon icon5 = new StateIcon(defaultStack, null, "TEST-STATE", "STATE-0", (currentState)->{
      switch(currentState.toUpperCase(Locale.ROOT)) {

        case "STATE-0":
          return "STATE-1";
        case "STATE-1":
          return "STATE-2";
        case "STATE-2":
          return "STATE-3";
        default:
          return "STATE-0";
      }
    });

    icon5.addState("STATE-1", state1Stack);
    icon5.addState("STATE-2", state2Stack);
    icon5.addState("STATE-3", state3Stack);

    //Pages
    final Page page = new Page(1);
    page.addIcon(icon);
    page.addIcon(icon2);

    exampleMenu.pages.put(1, page);

    //Pages
    final Page page2 = new Page(1);
    page2.addIcon(icon3);
    page2.addIcon(icon4);
    page2.addIcon(icon5);

    exampleMenu2.pages.put(1, page2);

    MenuManager.instance().addMenu(exampleMenu);
    MenuManager.instance().addMenu(exampleMenu2);*/

    // Example Menu
    final Menu exampleMenu = new MenuBuilder("example")
            .withTitle("Example GUI")
            .withRows(5)
            .withPages(
                    new PageBuilder(1)
                            .withIcons(
                                    new IconBuilder(menu.stackBuilder().customName(Component.text("Example Icon")).of("RED_WOOL", 1))
                                            .withSlot(new SlotPos(2, 3))
                                            .withConstraint(IconStringConstraints.ICON_MESSAGE, "You switched a menu!")
                                            .withActions(new SwitchMenuAction("example2"))
                                            .build(),

                                    new IconBuilder(menu.stackBuilder().customName(Component.text("Example Icon2")).of("GREEN_WOOL", 1))
                                            .withSlot(new SlotPos(2, 6))
                                            .withConstraint(IconStringConstraints.ICON_MESSAGE, "Please type: hello")
                                            .build()
                                      ).build()
                      )
            .build();

    // Example Menu2
    final Menu exampleMenu2 = new MenuBuilder("example2")
            .withTitle("Example2 GUI")
            .withRows(3)
            .withPages(
                    new PageBuilder(1)
                            .withIcons(
                                    new IconBuilder(menu.stackBuilder().customName(Component.text("Example2 Icon")).of("STONE", 1))
                                            .withSlot(new SlotPos(2, 5))
                                            .withConstraint(IconStringConstraints.ICON_MESSAGE, "You switched a menu and found the new button!")
                                            .withActions(new SwitchMenuAction("example"))
                                            .build(),
                                    new IconBuilder(menu.stackBuilder().customName(Component.text("Dynamic Input-based Icon")).of("STONE", 1))
                                            .withItemProvider(player->{
                                              final Optional<MenuViewer> viewer = MenuManager.instance().findViewer(player.identifier());
                                              if(viewer.isPresent()) {
                                                final Optional<Object> display = viewer.get().findData("example-data");
                                                if(display.isPresent()) {
                                                  return menu.stackBuilder().customName(Component.text((String)display.get())).of("GRASS", 1);
                                                }
                                              }
                                              return menu.stackBuilder().customName(Component.text("Default Display")).of("STONE", 1);
                                            })
                                            .withSlot(new SlotPos(2, 2))
                                            .build(),

                                    new IconBuilder(menu.stackBuilder().customName(Component.text("Default State")).of("BLACK_WOOL", 1))
                                            .withStateID("TEST-STATE")
                                            .withDefaultState("STATE-0")
                                            .withStateHandler(currentState->{
                                              switch(currentState.toUpperCase(Locale.ROOT)) {
                                                case "STATE-0":
                                                  return "STATE-1";
                                                case "STATE-1":
                                                  return "STATE-2";
                                                case "STATE-2":
                                                  return "STATE-3";
                                                default:
                                                  return "STATE-0";
                                              }
                                            })
                                            .withState("STATE-1", menu.stackBuilder().customName(Component.text("State 1")).of("BROWN_WOOL", 1))
                                            .withState("STATE-2", menu.stackBuilder().customName(Component.text("State 2")).of("BLUE_WOOL", 1))
                                            .withState("STATE-3", menu.stackBuilder().customName(Component.text("State 3")).of("GREEN_WOOL", 1))
                                            .build()
                                      ).build()
                      ).build();

    // Adding menus to the manager
    MenuManager.instance().addMenu(exampleMenu);
    MenuManager.instance().addMenu(exampleMenu2);
  }

  @EventHandler
  public void onJoin(final PlayerJoinEvent event) {

    final BukkitPlayer player = new BukkitPlayer(event.getPlayer(), this);
    MenuManager.instance().open("example", 1, player);
  }
}