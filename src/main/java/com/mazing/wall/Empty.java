package com.mazing.wall;
import com.mazing.game.*;
import com.mazing.item.*;
import com.mazing.command.*;
import com.mazing.map.*;

public class Empty extends Wall {

  private static Empty instance;

  private Empty() {
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
  public Response getThrough(Game game) {
    return new Response(ResponseType.INVALID,
        "You can not move through an empty wall. Unfortunately you are not a ghost");
  }

  @Override
  public Response check(Game game) {
    return new Response(ResponseType.INVALID,
        "com.mazing.wall.Empty walls are just empty walls, do not try to be a genius");
  }

  @Override
  public String toString() {
    return "an com.mazing.wall.Empty com.mazing.wall.Wall";
  }
}
