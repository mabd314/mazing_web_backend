package com.mazing.logic.command;

import com.mazing.Response;
import com.mazing.ResponseType;

public class NoTradingCommand extends TradingCommand {

  @Override
  public void execute() {
    setResponse(new Response(ResponseType.INVALID, "Command not recognized"));
  }
}
