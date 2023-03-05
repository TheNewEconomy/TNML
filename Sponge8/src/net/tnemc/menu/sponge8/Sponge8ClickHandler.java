package net.tnemc.menu.sponge8;

import net.tnemc.menu.core.icon.ActionType;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.event.Cause;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.menu.ClickType;
import org.spongepowered.api.item.inventory.menu.ClickTypes;
import org.spongepowered.api.item.inventory.menu.handler.ClickHandler;

import java.util.Optional;

public class Sponge8ClickHandler implements ClickHandler {

  @Override
  public boolean handle(Cause cause, Container container, ClickType<?> clickType) {
    Optional<User> user = cause.first(User.class);

    if(user.isPresent()) {
    }
    return true;
  }
}