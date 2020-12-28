package com.mazing.command;
import com.mazing.game.*;

public class NoTradingCommand extends TradingCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.INVALID,
        "Command not recognized"));
  }
}
