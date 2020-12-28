package com.mazing.command;

public class SwitchLightsCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(getGame().getCurrentRoom().switchLights());
  }
}
