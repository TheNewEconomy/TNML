package net.tnemc.menu.sponge7;

import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.compatibility.InventoryClickHandler;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.viewer.ViewerData;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.plugin.Plugin;

import java.util.Optional;

public class Sponge7InventoryClickListener {

  private final Plugin plugin;

  public Sponge7InventoryClickListener(Plugin plugin) {
    this.plugin = plugin;
  }

  @Listener
  public void onDouble(ClickInventoryEvent event, @First Player player) {
    final SpongePlayer sPlayer = new SpongePlayer(player, plugin);

    final Optional<ViewerData> data = MenuManager.instance().getViewer(sPlayer.identifier());
    final Optional<Slot> slot = event.getSlot();
    if(slot.isPresent() && sPlayer.inventory().inMenu() && data.isPresent()) {

      final Optional<SlotIndex> property = slot.get().getInventoryProperty(SlotIndex.class);

      if(property.isPresent()) {
        final boolean cancel = new InventoryClickHandler().handle(convertClick(event),
                                                                  sPlayer, property.get().getValue());

        if(cancel) {
          event.setCancelled(true);
        }
      }
    }
  }



  private ActionType convertClick(ClickInventoryEvent event) {
    if (event instanceof ClickInventoryEvent.Shift.Primary) {
      return ActionType.LEFT_SHIFT;
    } else if(event instanceof ClickInventoryEvent.Shift.Secondary) {
      return ActionType.RIGHT_SHIFT;
    } else if(event instanceof ClickInventoryEvent.Secondary) {
      return ActionType.RIGHT_CLICK;
    } else if(event instanceof ClickInventoryEvent.Middle) {
      return ActionType.SCROLL_CLICK;
    } else if(event instanceof ClickInventoryEvent.Double) {
      return ActionType.DOUBLE_CLICK;
    } else if(event instanceof ClickInventoryEvent.Drop.Full) {
      return ActionType.DROP_CTRL;
    } else if(event instanceof ClickInventoryEvent.Drop) {
      return ActionType.DROP;
    } else if(event instanceof ClickInventoryEvent.NumberPress) {
      return ActionType.NUMBER;
    } else {
      return ActionType.LEFT_CLICK;
    }
  }
}