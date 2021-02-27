package com.mazing.logic.command;

import com.mazing.Response;
import com.mazing.ResponseType;
import com.mazing.logic.wall.Wall;

public class CheckCommand extends MainCommand {
  String wallName;

  public CheckCommand(String wallName) {
    this.wallName = wallName;
  }

  @Override
  public void execute() {
    Wall wall = getPlayer().facingWall();
    if (wallName.equals(" ")) setResponse(new Response(ResponseType.INVALID, "wrong command"));
    else setResponse(wall.check(getPlayer(), wallName));
    wall.getWallEntity().save();
    getPlayer().getPlayerEntity().save();
  }
}
