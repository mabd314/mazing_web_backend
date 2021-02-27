package com.mazing.logic.wall;

import com.mazing.Response;
import com.mazing.ResponseType;
import com.mazing.WallEntity;
import com.mazing.logic.game.Player;
import com.mazing.logic.item.Item;
import com.mazing.logic.item.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Wall {

  private int wallId;

  public Wall(){}
  public abstract WallEntity getWallEntity();

  public String getDescription() {
    return getType().getDescription();
  }

  public int getWallId() {
    return wallId;
  }

  public void setWallId(int wallId) {
    this.wallId = wallId;
  }

  public abstract WallType getType();

  public Response getThrough(Player player) {
    return new Response(ResponseType.INVALID, "You can only move through doors");
  }

  public final Response check(Player player, String wallName) {
    WallType wallType = WallType.getWallType(wallName);
    if (wallType == WallType.NONE) {
      return new Response(ResponseType.INVALID, "There is no " + wallName + " in this game");
    }
    if (wallType != player.facingWallType()) {
      return new Response(ResponseType.INVALID, "There is no " + wallName + " in front of you");
    }
    return wallSpecificCheck(player);
  }

  public abstract Response wallSpecificCheck(Player player);

  public Response list() {
    return new Response(ResponseType.INVALID, "You can only trade with a seller");
  }

  public Response toggleWithKey(Key key) {
    return new Response(ResponseType.INVALID, "You can not use your key on " + this);
  }

  public List<Item> getTradingList() {
    return new ArrayList<>();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Wall wall = (Wall) o;
    return wallId == wall.wallId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(wallId);
  }
}
