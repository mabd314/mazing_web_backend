package com.mazing.wall;

import com.mazing.game.Game;
import com.mazing.game.Response;
import com.mazing.game.ResponseType;
import com.mazing.item.Key;
import com.mazing.item.NoKey;

public class Mirror extends Wall {

  private Key hidden;

  public Mirror(Key key) {
    this.hidden = key;
  }

  public void clear() {
    this.hidden = NoKey.getInstance();
  }

  @Override
  public WallType getType() {
    return WallType.MIRROR;
  }

  @Override
  public Response wallSpecificCheck(Game game) {
    if (hidden == NoKey.getInstance()) {
      return new Response(ResponseType.EMPTY,
          "There is nothing behind this mirror");
    }
    game.getCharacter().addItem(hidden);
    Response status =
        new Response(ResponseType.SUCCESS,
            "You found " + hidden + " behind this mirror");
    clear();
    return status;
  }

  @Override
  public String toString() {
    return "a Mirror";
  }
}
