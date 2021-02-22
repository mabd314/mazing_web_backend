package com.mazing.command;

import com.mazing.game.Response;
import com.mazing.game.ResponseType;

public class QuitCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.LOST, "You quit the game. YOU LOSE."));
  }

  public void executeNext() {
    System.exit(1);
  }
}
