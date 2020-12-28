package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public abstract class TradingCommand implements Command {
  private Game game;
  private Response response;
  @Override
  public Game getGame() {
    return game;
  }

  @Override
  public void setGame(Game game) {
    this.game = game;
  }

  @Override
  public void executeNext(){
    game.executeTradeCommand();
  }

  @Override
  public Response getResponse() {
    return response;
  }

  @Override
  public void setResponse(Response response){
    this.response=response;
  }

  public static TradingCommand getCommand(String command,String arg){
    switch(command.toLowerCase()){
      case "buy" -> {return new BuyCommand(arg);}
      case "sell" ->{return new SellCommand(arg);}
      case "end" ->{return new EndTradingCommand();}
      case "list"->{return new ListCommand();}
      default -> {return new NoTradingCommand();}
    }
  }
}
