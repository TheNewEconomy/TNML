package net.tnemc.menu.core.page.impl;


import net.tnemc.menu.core.Menu;
import net.tnemc.menu.core.callbacks.page.PageSlotClickCallback;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.icon.Icon;
import net.tnemc.menu.core.page.Page;
import net.tnemc.menu.core.utils.CloseType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a {@link Page} inside a {@link Menu}, which is unique for a player.
 *
 * @see Menu
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public abstract class PlayerPage extends Page {

  protected final Map<UUID, PlayerIcons> playerIcons = new HashMap<>();

  public PlayerPage(int id) {
    super(id);
  }

  @Override
  public Map<Integer, Icon> getIcons(MenuPlayer player) {
    if(!playerIcons.containsKey(player.identifier())) {
      playerIcons.put(player.identifier(), new PlayerIcons(player.identifier(), defaultIcons(player)));
    }

    return playerIcons.get(player.identifier()).getIcons();
  }

  /**
   * Used to get the default icons for a given player.
   *
   * @param player The player.
   * @return A map containing the default icons for the given player.
   */
  public abstract Map<Integer, Icon> defaultIcons(MenuPlayer player);

  @Override
  public boolean onClick(ActionType type, MenuPlayer player, Menu menu, int slot) {

    boolean result = true;

    if(playerIcons.containsKey(player.identifier()) &&
        playerIcons.get(player.identifier()).getIcons().containsKey(slot)) {

      result = playerIcons.get(player.identifier()).getIcons().get(slot)
                          .onClick(type, player, menu, this);
    }

    if(result && click != null) {
      click.accept(new PageSlotClickCallback(menu, this, type, player, slot));
    }

    return result;
  }

  @Override
  public void onClose(MenuPlayer player, CloseType type) {
    if(type.equals(CloseType.CLOSE) || type.equals(CloseType.MENU_SWITCH)) {
      playerIcons.remove(player.identifier());
    }
    super.onClose(player, type);
  }
}