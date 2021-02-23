package com.mazing.logic.command;

import com.mazing.logic.game.Response;
import com.mazing.logic.game.ResponseType;

public class TimeCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.STATUS, getGame().getStopWatch().checkTime()));
  }
}
