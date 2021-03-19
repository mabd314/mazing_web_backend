package com.mazing.logic.command;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;

public class EndTradingCommand extends TradingCommand {

  @Override
  public void execute() {
    setResponse(new Response(ResponseType.STATUS, "You got out of the trading mode"));
    getPlayer().setInTradeMode(false);
    getPlayer().getPlayerEntity().save();
  }
}
