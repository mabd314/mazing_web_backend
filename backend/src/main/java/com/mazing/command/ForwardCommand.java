package com.mazing.command;

public class ForwardCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(getGame().getThroughWallAtDirection(getGame().getDirection()));
  }
}
