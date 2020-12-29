package com.mazing.command;

import com.mazing.game.Response;
import com.mazing.game.ResponseType;

public class TimeCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.STATUS, getGame().getStopWatch().checkTime()));
  }
}
