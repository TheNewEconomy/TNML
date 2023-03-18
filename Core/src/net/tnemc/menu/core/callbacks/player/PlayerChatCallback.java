package net.tnemc.menu.core.callbacks.player;

import java.util.UUID;

public class PlayerChatCallback {

  private final UUID player;
  private final String message;
  private boolean returnToMenu;

  public PlayerChatCallback(UUID player, String message) {
    this.player = player;
    this.message = message;
  }

  public UUID getPlayer() {
    return player;
  }

  public String getMessage() {
    return message;
  }

  public boolean isReturnToMenu() {
    return returnToMenu;
  }

  public void setReturnToMenu(boolean returnToMenu) {
    this.returnToMenu = returnToMenu;
  }
}