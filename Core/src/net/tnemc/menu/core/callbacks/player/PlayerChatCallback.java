package net.tnemc.menu.core.callbacks.player;

import net.tnemc.menu.core.compatibility.MenuPlayer;

public class PlayerChatCallback {

  private final MenuPlayer player;
  private final String message;

  private String menu;
  private int page;

  public PlayerChatCallback(MenuPlayer player, String message, String menu, int page) {
    this.player = player;
    this.message = message;
  }

  public MenuPlayer getPlayer() {
    return player;
  }

  public String getMessage() {
    return message;
  }

  public String getMenu() {
    return menu;
  }

  public void setMenu(String menu) {
    this.menu = menu;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }
}