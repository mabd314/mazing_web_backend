package com.mazing.logic.command;

import com.mazing.entities.Response;
import com.mazing.logic.game.Game;
import com.mazing.logic.game.Player;

public abstract class TradingCommand implements Command {
  private Game game;
  private Response response;
  private Player player;

  public static TradingCommand getCommand(String command,String arg){
    switch(command.toLowerCase()){
      case "buy":
        return new BuyCommand(arg);
    case "sell":
      return new SellCommand(arg);
      case "end":
        return new EndTradingCommand();
      case "list":
        return new ListCommand();
//      case "help"->{return new TradingHelpCommand();}
      default:
        return new NoTradingCommand();
    }
  }

  @Override
  public Game getGame() {
    return game;
  }

  @Override
  public Player getPlayer(){
    return player;
  }

  @Override
  public void setPlayer(Player player){
    this.player=player;
    game=player.getGame();
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
