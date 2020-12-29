package com.mazing.command;

import com.mazing.game.Console;
import com.mazing.game.Response;
import com.mazing.game.ResponseType;

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
