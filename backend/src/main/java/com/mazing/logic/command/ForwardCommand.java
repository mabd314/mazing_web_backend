package com.mazing.logic.command;

public class ForwardCommand extends MainCommand {

  @Override
  public void execute() {
    setResponse(getPlayer().throughWallAtDirection(getPlayer().getDirection()));
    getPlayer().getPlayerEntity().save();
  }
}
