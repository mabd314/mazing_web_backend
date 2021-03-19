package com.mazing.logic.command;

public class ForwardCommand extends MainCommand {

  @Override
  public void execute() {
    synchronized (ForwardCommand.this){
      setResponse(getPlayer().throughWallAtDirection(getPlayer().getDirection()));
      getPlayer().getPlayerEntity().save();
    }
  }
}
