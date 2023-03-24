package net.tnemc.menu.core.page.impl;

import net.tnemc.menu.core.icon.Icon;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerIcons {

  protected final ConcurrentHashMap<Integer, Icon> icons = new ConcurrentHashMap<>();

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