package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class TradeCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.SUCCESS,
        "You entered the trading mode"));
  }

  public void executeNext(){
    getGame().executeTradeCommand();
  }
}
