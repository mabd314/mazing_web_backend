package com.mazing.logic.command;

import com.mazing.logic.game.Room;

public class SwitchLightsCommand extends MainCommand {

  @Override
  public void execute() {
    Room currentRoom= getPlayer().currentRoom();
    setResponse(currentRoom.switchLights());
    currentRoom.getRoomEntity().save();
  }
}
