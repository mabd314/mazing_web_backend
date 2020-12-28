package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public abstract class MainCommand implements Command  {
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
    game.executeMainCommand();
  }

  @Override
  public Response getResponse() {
    return response;
  }

  @Override
  public void setResponse(Response response){
    this.response=response;
  }

  public static MainCommand getCommand(String command,String arg){
    switch(command.toLowerCase()){
      case "look" -> {return new LookCommand();}
      case "check" ->{return new CheckCommand(arg);}
      case "status" ->{return new PlayerStatusCommand();}
      case "time"->{return new TimeCommand();}
      case "use" ->{return new UseCommand(arg);}
      case "trade"->{return new TradeCommand();}
      case "quit" ->{return new QuitCommand();}
      case "restart"->{return new RestartCommand();}
      case "switchlights"->{return new SwitchLightsCommand();}
      case "left"->{return new LeftCommand();}
      case "right"->{return new RightCommand();}
      case "forward"->{return new ForwardCommand();}
      case "backward"->{return new BackwardCommand();}
      default -> {return new NoMainCommand();}
    }
  }
}
