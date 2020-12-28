package com.mazing.command;

import com.mazing.game.*;

public interface Command {
  void setGame(Game game);
  Game getGame();
  void applyEffect();
  default void printResponse(){
    System.out.println(getResponse());
  }
  Response getResponse();
  void executeNext();
  void setResponse(Response response);

  default void execute(){
    applyEffect();
    printResponse();
    executeNext();
  }
}
