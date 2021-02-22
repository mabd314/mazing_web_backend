package com.mazing.command;

import com.mazing.game.Console;
import com.mazing.game.ResponseType;

public class TradeCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(getGame().getFacingWall().list());
  }

  public void executeNext() {
    if (isResponseInvalid()) Console.executeMainCommand(getGame());
    else Console.executeTradingCommand(getGame());
  }

  private boolean isResponseInvalid() {
    return getResponse().getType() == ResponseType.INVALID;
  }
}
