package com.mazing.logic.command;

import com.mazing.Response;
import com.mazing.ResponseType;

public class LookCommand extends MainCommand {

  @Override
  public void execute() {
    if (getPlayer().currentRoomIsLit()) {
      setResponse(new Response(ResponseType.STATUS,getPlayer().facingWall().getDescription()));
    } else {
      setResponse(new Response(ResponseType.FAILURE, "The room is dark, you can not see anything"));
    }
  }
}
