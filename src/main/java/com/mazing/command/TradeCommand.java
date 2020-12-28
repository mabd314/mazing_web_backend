package com.mazing.command;

public class TradeCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(getGame().getFacingWall().list());
  }

  public void executeNext(){
    getGame().executeTradingCommand();
  }
}
