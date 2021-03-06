package com.mazing.logic.command;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;
import com.mazing.logic.wall.Wall;
import com.mazing.logic.wall.WallType;

public class CheckCommand extends MainCommand {
  String wallName;

  public CheckCommand(String wallName) {
    this.wallName = wallName;
  }

  @Override
  public void execute() {
    synchronized (CheckCommand.class){
      Wall wall = getPlayer().facingWall();
      if (wallName.equals(" ")) setResponse(new Response(ResponseType.INVALID, "wrong command"));
      else setResponse(wall.check(getPlayer(), wallName));
      if(wall.getType()!= WallType.EMPTY)
        wall.getWallEntity().save();
      getPlayer().getPlayerEntity().save();
    }
  }
}
