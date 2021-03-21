package com.mazing.logic.command;

import com.mazing.entities.Response;
import com.mazing.logic.game.Game;
import com.mazing.logic.game.Player;

public abstract class MainCommand implements Command  {
  private Game game;
  private Player player;
  private Response response;

  public static MainCommand getCommand(String command,String arg){
    switch(command.toLowerCase()){
      case "look":
        return new LookCommand();
      case "check":
        return new CheckCommand(arg);
      case "status" :
        return new PlayerStatusCommand();
      case "time":
        return new TimeCommand();
      case "use":
        return new UseCommand(arg);
      case "trade":
        return new TradeCommand();
      case "quit":
        return new QuitCommand();
      case "switchlights":
        return new SwitchLightsCommand();
      case "left":
        return new LeftCommand();
      case "right":
        return new RightCommand();
      case "forward":
        return new ForwardCommand();
      case "backward":
        return new BackwardCommand();
//      case "help"->{return new MainHelpCommand();}
      default:
        return new NoMainCommand();
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
