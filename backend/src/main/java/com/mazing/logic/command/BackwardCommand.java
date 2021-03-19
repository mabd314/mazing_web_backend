package com.mazing.logic.command;

public class BackwardCommand extends MainCommand {

  @Override
  public void execute() {
    synchronized (BackwardCommand.this){
      setResponse(getPlayer().throughWallAtDirection(getPlayer().oppositeDirection()));
      getPlayer().getPlayerEntity().save();
    }
  }
}
