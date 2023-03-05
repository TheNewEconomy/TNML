package net.tnemc.menu.sponge8;

import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.compatibility.InventoryClickHandler;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.viewer.ViewerData;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.container.ClickContainerEvent;
import org.spongepowered.api.item.inventory.Slot;

import java.util.Optional;

public class Sponge8InventoryClickListener {

  @Listener
  public void onDouble(ClickContainerEvent event, @First User user) {
    final SpongePlayer player = new SpongePlayer(user);

    final Optional<ViewerData> data = MenuManager.instance().getViewer(player.identifier());
    final Optional<Slot> slot = event.slot();
    if(slot.isPresent() && player.inventory().inMenu() && data.isPresent()) {

    }
  }



  private ActionType convertClick(ClickContainerEvent event) {
    if (event instanceof ClickContainerEvent.Shift.Primary) {
      return ActionType.LEFT_SHIFT;
    } else if(event instanceof ClickContainerEvent.Shift.Secondary) {
      return ActionType.RIGHT_SHIFT;
    } else if(event instanceof ClickContainerEvent.Secondary) {
      return ActionType.RIGHT_CLICK;
    } else if(event instanceof ClickContainerEvent.Middle) {
      return ActionType.SCROLL_CLICK;
    } else if(event instanceof ClickContainerEvent.Double) {
      return ActionType.DOUBLE_CLICK;
    } else if(event instanceof ClickContainerEvent.Drop.Full) {
      return ActionType.DROP_CTRL;
    } else if(event instanceof ClickContainerEvent.Drop) {
      return ActionType.DROP;
    } else if(event instanceof ClickContainerEvent.NumberPress) {
      return ActionType.NUMBER;
    } else {
      return ActionType.LEFT_CLICK;
    }
  }
}