package com.mazing.command;

import com.mazing.game.*;

public class EndTradingCommand extends TradingCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.STATUS,
        "You got out of the trading mode"));
  }

  @Override
  public void executeNext() {
    getGame().executeMainCommand();
  }
}
