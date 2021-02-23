package com.mazing.logic.command;

import com.mazing.logic.game.Console;
import com.mazing.logic.game.Response;
import com.mazing.logic.game.ResponseType;
import com.mazing.logic.wall.Door;

public class RestartCommand extends MainCommand {

  @Override
  public void applyEffect() {
    getGame().getStopWatch().stopTimer();
    Door.clear();

    setResponse(new Response(ResponseType.LOST, "You restarted the game. YOU LOSE."));
  }

  @Override
  public void executeNext() {
    Console.initializeGame(getGame());
  }
}
