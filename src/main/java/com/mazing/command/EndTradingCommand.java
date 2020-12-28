package com.mazing.command;

import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

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
