package net.tnemc.menu.minestom;

import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.click.ClickType;
import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.compatibility.InventoryClickHandler;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.viewer.ViewerData;

import java.util.Optional;
import java.util.function.Consumer;

public class MinestomClickConsumer implements Consumer<InventoryPreClickEvent> {

  /**
   * Performs this operation on the given argument.
   *
   * @param event the input argument
   */
  @Override
  public void accept(InventoryPreClickEvent event) {
    final MinestomPlayer player = new MinestomPlayer(event.getPlayer());

    final Optional<ViewerData> data = MenuManager.instance().getViewer(player.identifier());
    if(player.inventory().inMenu() && data.isPresent()) {
      final boolean cancel = new InventoryClickHandler().handle(convertClick(event.getClickType()),
                                                                player, event.getSlot());

      if(cancel) {
        event.setCancelled(true);
      }
    }
  }

  private ActionType convertClick(ClickType click) {
    return switch(click) {
      case SHIFT_CLICK -> ActionType.LEFT_SHIFT;
      case RIGHT_CLICK -> ActionType.RIGHT_CLICK;
      case DOUBLE_CLICK -> ActionType.DOUBLE_CLICK;
      case DROP -> ActionType.DROP;
      case CHANGE_HELD -> ActionType.OFFHAND_SWAP;
      default -> ActionType.LEFT_CLICK;
    };
  }
}