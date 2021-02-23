package com.mazing.logic.command;

import com.mazing.logic.game.Response;
import com.mazing.logic.game.ResponseType;

public class LookCommand extends MainCommand {

  @Override
  public void applyEffect() {
    if (getGame().isCurrentRoomLit()) {
      setResponse(new Response(ResponseType.STATUS, getGame().getFacingWall().getDescription()));
    } else {
      setResponse(new Response(ResponseType.FAILURE, "The room is dark, you can not see anything"));
    }
  }
}
