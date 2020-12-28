package com.mazing.command;

public class ListCommand extends TradingCommand {


  @Override
  public void applyEffect() {
    setResponse(getGame().getFacingWall().list());
  }

}
