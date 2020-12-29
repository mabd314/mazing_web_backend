package com.mazing.command;

import com.mazing.game.Console;
import com.mazing.game.Game;
import com.mazing.game.Response;

public abstract class TradingCommand implements Command {
  private Game game;
  private Response response;

  public static TradingCommand getCommand(String command,String arg){
    switch(command.toLowerCase()){
      case "buy" -> {return new BuyCommand(arg);}
      case "sell" ->{return new SellCommand(arg);}
      case "end" ->{return new EndTradingCommand();}
      case "list"->{return new ListCommand();}
      case "help"->{return new TradingHelpCommand();}
      default -> {return new NoTradingCommand();}
    }
  }

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
    Console.executeTradingCommand(game);
  }

  @Override
  public Response getResponse() {
    return response;
  }

  @Override
  public void setResponse(Response response){
    this.response=response;
  }
}
