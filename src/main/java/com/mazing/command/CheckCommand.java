package com.mazing.command;

import com.mazing.wall.*;

public class CheckCommand extends MainCommand {
  String wallName;

  public CheckCommand(String wallName) {
    this.wallName=wallName;
  }

  @Override
  public void applyEffect() {
    Wall wall = getGame().getFacingWall();
    setResponse(wall.check(getGame(),wallName));
  }
}
