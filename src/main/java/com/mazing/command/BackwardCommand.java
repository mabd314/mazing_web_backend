package com.mazing.command;

public class BackwardCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(getGame().getThroughWallAtDirection(getGame().getOppositeDirection()));
  }
}
