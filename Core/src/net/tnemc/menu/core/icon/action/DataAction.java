package net.tnemc.menu.core.icon.action;

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

import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.icon.IconAction;
import net.tnemc.menu.core.page.Page;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DataAction represents an {@link IconAction}, which is used to log specific data to a player's
 * {@link net.tnemc.menu.core.viewer.ViewerData} file.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class DataAction extends IconAction {

  protected final Map<String, Object> data = new ConcurrentHashMap<>();

  public DataAction() {
    super(ActionType.ANY);
  }

  public DataAction(final String key, final Object data) {
    super(ActionType.ANY);
    this.data.put(key, data);
  }

  public DataAction(Map<String, Object> data) {
    super(ActionType.ANY);
    this.data.putAll(data);
  }

  public DataAction(final String key, final Object data, final ActionType type) {
    super(type);
    this.data.put(key, data);
  }

  public DataAction(Map<String, Object> data, final ActionType type) {
    super(type);
    this.data.putAll(data);
  }

  /**
   * Determines if any other icon actions should be performed after this action is performed.
   *
   * @return True if other actions should be performed, otherwise false.
   */
  @Override
  public boolean continueOther() {
    return true;
  }

  /**
   * This method is called when the action happens.
   *
   * @param menu   The menu that the action happened in.
   * @param page   The page of the menu that the action happened in.
   * @param player The player that performed the action.
   * @param icon   The icon clicked in the action.
   */
  @Override
  public void onPerform(Menu menu, Page page, MenuPlayer player, Icon icon) {
    MenuManager.instance().appendViewerData(player.identifier(), data);
  }

  public Map<String, Object> getData() {
    return data;
  }
}