package net.tnemc.menu.bukkit;

import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class BukkitPlayer implements MenuPlayer {

  private OfflinePlayer player;

  public BukkitPlayer(OfflinePlayer player) {
    this.player = player;
  }

  /**
   * The {@link UUID unique identifier} for this player.
   *
   * @return The {@link UUID} for the player.
   */
  @Override
  public UUID identifier() {
    return player.getUniqueId();
  }

  /**
   * The {@link PlayerInventory} for this player.
   *
   * @return The {@link PlayerInventory} for this player.
   */
  @Override
  public PlayerInventory<?> inventory() {
    return new BukkitInventory(identifier());
  }

  /**
   * Used to determine if this player has the specified permission node.
   *
   * @param permission The node to check for.
   *
   * @return True if the player has the permission, otherwise false.
   */
  @Override
  public boolean hasPermission(String permission) {
    if(player.getPlayer() != null) {
      return player.getPlayer().hasPermission(permission);
    }
    return false;
  }

  /**
   * Used to send a message to this player.
   *
   * @param message The message.
   */
  @Override
  public void message(String message) {
    if(player.getPlayer() != null) {
      player.getPlayer().sendMessage(message);
    }
  }
}