package com.mazing.logic.command;

public class ListCommand extends TradingCommand {

  @Override
  public void execute() {
    setResponse(getPlayer().facingWall().list());
  }
}
