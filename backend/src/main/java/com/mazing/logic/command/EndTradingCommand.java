package com.mazing.logic.command;

import com.mazing.logic.game.Console;
import com.mazing.logic.game.Response;
import com.mazing.logic.game.ResponseType;

public class EndTradingCommand extends TradingCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.STATUS, "You got out of the trading mode"));
  }

  @Override
  public void executeNext() {
    Console.executeMainCommand(getGame());
  }
}
