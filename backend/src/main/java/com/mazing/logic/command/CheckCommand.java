package com.mazing.logic.command;

import com.mazing.logic.game.Response;
import com.mazing.logic.game.ResponseType;
import com.mazing.logic.wall.Wall;

public class CheckCommand extends MainCommand {
  String wallName;

  public CheckCommand(String wallName) {
    this.wallName = wallName;
  }

  @Override
  public void applyEffect() {
    Wall wall = getGame().getFacingWall();
    if (wallName.equals(" ")) setResponse(new Response(ResponseType.INVALID, "wrong command"));
    else setResponse(wall.check(getGame(), wallName));
  }
}
