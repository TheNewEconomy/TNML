package net.tnemc.menu.core.compatibility;

import java.util.UUID;

/**
 * Represents a player object for a platform. This holds all methods that need to be converted to
 * each individual platform.
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface MenuPlayer {

  /**
   * The {@link UUID unique identifier} for this player.
   * @return The {@link UUID} for the player.
   */
  UUID identifier();

  /**
   * The {@link PlayerInventory} for this player.
   * @return The {@link PlayerInventory} for this player.
   */
  PlayerInventory<?> inventory();

  /**
   * Used to determine if this player has the specified permission node.
   *
   * @param permission The node to check for.
   * @return True if the player has the permission, otherwise false.
   */
  boolean hasPermission(String permission);

  /**
   * Used to send a message to this player.
   * @param message The message.
   */
  void message(final String message);
}