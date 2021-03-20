package com.mazing.logic.command;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;

public class NoTradingCommand extends TradingCommand {

  @Override
  public void execute() {
    setResponse(new Response(ResponseType.INVALID, "Command not recognized\n "+"Remember that you are in a trade, execute the command 'end' to get out"));
  }
}
