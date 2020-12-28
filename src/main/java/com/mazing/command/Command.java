package com.mazing.command;

import com.mazing.game.Game;
import com.mazing.game.Response;

public interface Command {
  void setGame(Game game);

  Game getGame();

  void applyEffect();

  Response getResponse();

  void executeNext();

  void setResponse(Response response);

  default void printResponse() {
    System.out.println(getResponse());
  }

  default void execute() {
    applyEffect();
    printResponse();
    executeNext();
  }
}
