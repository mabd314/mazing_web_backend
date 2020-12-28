package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class TimeCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.STATUS,
        getGame().getStopWatch().checkTime()));
  }
}
