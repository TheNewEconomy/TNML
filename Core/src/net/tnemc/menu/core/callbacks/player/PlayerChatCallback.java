package net.tnemc.menu.core.callbacks.player;

import net.tnemc.menu.core.compatibility.MenuPlayer;

import java.util.UUID;

public class PlayerChatCallback {

  private final MenuPlayer player;
  private final String message;
  private boolean returnToMenu;

  public PlayerChatCallback(MenuPlayer player, String message) {
    this.player = player;
    this.message = message;
  }

  public MenuPlayer getPlayer() {
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