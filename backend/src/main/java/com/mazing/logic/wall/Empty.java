package com.mazing.logic.wall;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;
import com.mazing.entities.WallEntity;
import com.mazing.logic.game.Player;

public class Empty extends Wall {

  private static Empty instance;

  private Empty() {}

  @Override
  public WallEntity getWallEntity() {
    return new WallEntity();
  }

  public static synchronized Empty getInstance() {
    if (instance == null) {
      instance = new Empty();
    }
    return instance;
  }

  @Override
  public WallType getType() {
    return WallType.EMPTY;
  }

  @Override
  public Response getThrough(Player player) {
    return new Response(
        ResponseType.INVALID,
        "You can not move through an empty wall. Unfortunately you are not a ghost");
  }

  @Override
  public Response wallSpecificCheck(Player player) {
    return new Response(
        ResponseType.INVALID, "Empty walls are just empty walls, do not try to be a genius");
  }

  @Override
  public String toString() {
    return "an Empty Wall";
  }
}
