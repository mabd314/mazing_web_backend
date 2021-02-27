package com.mazing.logic.command;

public class SwitchLightsCommand extends MainCommand {

  @Override
  public void execute() {
    setResponse(getPlayer().currentRoom().switchLights());
    getPlayer().currentRoom().getRoomEntity().save();
  }
}
