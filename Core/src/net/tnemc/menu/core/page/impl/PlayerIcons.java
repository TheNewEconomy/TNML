package net.tnemc.menu.core.page.impl;

import net.tnemc.menu.core.icon.Icon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerIcons {

  protected final Map<Integer, Icon> icons = new HashMap<>();

  private final UUID playerID;

  public PlayerIcons(UUID playerID) {
    this.playerID = playerID;
  }

  public PlayerIcons(UUID playerID, Map<Integer, Icon> icons) {
    this.playerID = playerID;
    this.icons.putAll(icons);
  }

  public Map<Integer, Icon> getIcons() {
    return icons;
  }

  public UUID getPlayerID() {
    return playerID;
  }
}