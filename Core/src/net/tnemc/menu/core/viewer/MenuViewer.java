package net.tnemc.menu.core.viewer;
/*
 * The New Menu Library
 * Copyright (C) 2022 - 2023 Daniel "creatorfromhell" Vidmar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.UUID;

/**
 * MenuViewer
 *
 * @author creatorfromhell
 * @since 1.5.0.0
 */
public class MenuViewer {

  private final UUID uuid;

  private String menu;

  private ViewerStatus status = CoreStatus.IN_MENU;

  public MenuViewer(UUID uuid) {
    this.uuid = uuid;
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getMenu() {
    return menu;
  }

  public void setMenu(String menu) {
    this.menu = menu;
  }

  public ViewerStatus getStatus() {
    return status;
  }

  public void setStatus(ViewerStatus status) {
    this.status = status;
  }
}