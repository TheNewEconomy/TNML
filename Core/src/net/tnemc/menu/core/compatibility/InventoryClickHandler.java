package net.tnemc.menu.core.compatibility;

import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.viewer.ViewerData;

import java.util.Optional;

/**
 * InventoryClickHandler represents a click event that happens within an inventory when a player is
 * inside a menu.
 *
 * @author creatorfromhell
 * @since 1.0.0.0
 */
public class InventoryClickHandler {

  /**
   * Used to handle a click event from an inventory in the platform implementation.
   *
   * @param type The {@link ActionType} performed during the event.
   * @param player The player performing the click action.
   * @param slot The slot that was interacted with.
   * @return True if the corresponding click event should be cancelled, otherwise false.
   */
  public boolean handle(ActionType type, MenuPlayer player, int slot) {


    final Optional<ViewerData> data = MenuManager.instance().getViewer(player.identifier());
    if(data.isPresent()) {
      MenuManager.instance().onClick(data.get().getMenu(), type, player, data.get().getPage(), slot);

      return true;
    }
    return false;
  }
}