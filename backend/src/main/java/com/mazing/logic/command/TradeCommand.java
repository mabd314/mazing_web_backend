package com.mazing.logic.command;

import com.mazing.logic.game.Console;
import com.mazing.logic.game.ResponseType;

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
