package com.mazing.logic.command;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;

public class QuitCommand extends MainCommand {

  @Override
  public void execute() {
    setResponse(new Response(ResponseType.LOST, "You quit the game. YOU LOSE."));
    getPlayer().setGameId(0);
    getPlayer().getPlayerEntity().save();
  }
}
