package net.tnemc.menu.sponge7;

import net.tnemc.menu.core.compatibility.MenuPlayer;
import net.tnemc.menu.core.compatibility.PlayerInventory;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.util.UUID;

public class SpongePlayer implements MenuPlayer {

  final User user;
  final Plugin plugin;//because sponge requires this for a lot of useless things.

  public SpongePlayer(User user, Plugin plugin) {
    this.user = user;
    this.plugin = plugin;
  }

  /**
   * The {@link UUID unique identifier} for this player.
   *
   * @return The {@link UUID} for the player.
   */
  @Override
  public UUID identifier() {
    return user.getUniqueId();
  }

  /**
   * The {@link PlayerInventory} for this player.
   *
   * @return The {@link PlayerInventory} for this player.
   */
  @Override
  public SpongeInventory inventory() {
    return new SpongeInventory(user.getUniqueId(), plugin);
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
    if(user.getPlayer().isPresent()) {
      user.getPlayer().get().sendMessage(Text.of(message));
    }
  }
}