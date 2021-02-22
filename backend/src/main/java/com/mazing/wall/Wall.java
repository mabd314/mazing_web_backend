package com.mazing.wall;

import com.mazing.game.Game;
import com.mazing.game.Response;
import com.mazing.game.ResponseType;
import com.mazing.item.Item;
import com.mazing.item.Key;
import java.util.ArrayList;
import java.util.List;

public abstract class Wall {

  public String getDescription() {
    return getType().getDescription();
  }

  public abstract WallType getType();

  public Response getThrough(Game game) {
    return new Response(ResponseType.INVALID, "You can only move through doors");
  }

  public final Response check(Game game, String wallName) {
    WallType wallType = WallType.getWallType(wallName);
    if (wallType == WallType.NONE) {
      return new Response(ResponseType.INVALID, "There is no " + wallName + " in this game");
    }
    if (wallType != game.getFacingWallType()) {
      return new Response(ResponseType.INVALID, "There is no " + wallName + " in front of you");
    }
    return wallSpecificCheck(game);
  }

  public abstract Response wallSpecificCheck(Game game);

  public Response list() {
    return new Response(ResponseType.INVALID, "You can only trade with a seller");
  }

  public Response toggleWithKey(Key key) {
    return new Response(ResponseType.INVALID, "You can not use your key on " + this);
  }

  public List<Item> getTradingList() {
    return new ArrayList<>();
  }
}
