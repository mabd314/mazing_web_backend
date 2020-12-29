package com.mazing.command;

import com.mazing.game.Console;
import com.mazing.game.Response;
import com.mazing.game.ResponseType;
import com.mazing.wall.Door;

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
