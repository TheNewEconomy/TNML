package net.tnemc.menu.sponge8;

import net.kyori.adventure.text.Component;
import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import org.spongepowered.api.entity.living.player.User;

import java.util.UUID;

public class SpongePlayer implements MenuPlayer {

  final User user;

  public SpongePlayer(User user) {
    this.user = user;
  }

  /**
   * The {@link UUID unique identifier} for this player.
   *
   * @return The {@link UUID} for the player.
   */
  @Override
  public UUID identifier() {
    return user.uniqueId();
  }

  /**
   * The {@link PlayerInventory} for this player.
   *
   * @return The {@link PlayerInventory} for this player.
   */
  @Override
  public SpongeInventory inventory() {
    return new SpongeInventory(user.uniqueId());
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
    return user.hasPermission(permission);
  }

  /**
   * Used to send a message to this player.
   *
   * @param message The message.
   */
  @Override
  public void message(String message) {
    if(user.player().isPresent()) {
      user.player().get().sendMessage(Component.text(message));
    }
  }
}