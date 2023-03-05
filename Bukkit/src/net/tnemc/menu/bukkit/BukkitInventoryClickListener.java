package net.tnemc.menu.bukkit;

import net.tnemc.menu.core.MenuManager;
import net.tnemc.menu.core.compatibility.InventoryClickHandler;
import net.tnemc.menu.core.icon.ActionType;
import net.tnemc.menu.core.viewer.ViewerData;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class BukkitInventoryClickListener implements Listener {

  private final JavaPlugin plugin;

  public BukkitInventoryClickListener(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onClick(final InventoryClickEvent event) {
    final BukkitPlayer player = new BukkitPlayer((OfflinePlayer)event.getWhoClicked());

    final Optional<ViewerData> data = MenuManager.instance().getViewer(player.identifier());
    if(player.inventory().inMenu() && data.isPresent()) {

      final boolean cancel = new InventoryClickHandler().handle(convertClick(event.getClick()),
                                                                player, event.getSlot()
      );

      if(cancel) {
        event.setCancelled(true);
      }
    }
  }

  private ActionType convertClick(ClickType click) {
    switch(click) {
      case SHIFT_LEFT:
        return ActionType.LEFT_SHIFT;
      case RIGHT:
        return ActionType.RIGHT_CLICK;
      case SHIFT_RIGHT:
        return ActionType.RIGHT_SHIFT;
      case MIDDLE:
        return ActionType.SCROLL_CLICK;
      case DOUBLE_CLICK:
        return ActionType.DOUBLE_CLICK;
      case DROP:
        return ActionType.DROP;
      case CONTROL_DROP:
        return ActionType.DROP_CTRL;
      case SWAP_OFFHAND:
        return ActionType.OFFHAND_SWAP;
      case NUMBER_KEY:
        return ActionType.NUMBER;
      default:
        return ActionType.LEFT_CLICK;
    }
  }
}