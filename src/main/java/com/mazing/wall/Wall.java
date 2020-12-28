package com.mazing.wall;
import com.mazing.game.*;
import com.mazing.item.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Wall {

  public abstract WallType getType();

  public String getDescription(){
    return getType().getDescription();
  }
  public Response getThrough(Game game) {
    return new Response(ResponseType.INVALID,
        "You can only move through doors");
  }

  public abstract Response wallSpecificCheck(Game game);

  public Response list(){
    return new Response(ResponseType.INVALID,
        "You can only trade with a seller");
  }

  public final Response check(Game game,String wallName) {
    WallType wallType=WallType.getWallType(wallName);
    if (wallType == WallType.NONE) {
      return new Response(ResponseType.INVALID,
          "There is no " + wallName + " in this game");
    }
    if (wallType != game.getFacingWallType()) {
      return new Response(ResponseType.INVALID,
          "There is no " + wallName + " in front of you");
    }
    return wallSpecificCheck(game);
  }

  public Response toggleWithKey(Key key) {
    return new Response(ResponseType.INVALID,
        "You can not use your key on " + this);
  }

  public List<Item> getTradingList() {
    return new ArrayList<>();
  }
}
