package com.mazing.logic.command;

public class ListCommand extends TradingCommand {

  @Override
  public void applyEffect() {
    setResponse(getGame().getFacingWall().list());
  }
}
