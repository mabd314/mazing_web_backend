package com.mazing.command;

public class BackwardCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(getGame().getThrough(getGame().getOppositeDirection()));
  }
}
