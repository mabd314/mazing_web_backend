package com.mazing.command;
import com.mazing.game.*;

public class TimeCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.STATUS,
        getGame().getStopWatch().checkTime()));
  }
}
