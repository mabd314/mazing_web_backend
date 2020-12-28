package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class RestartCommand extends MainCommand {

  @Override
  public void applyEffect() {
    getGame().getStopWatch().stopTimer();
    Door.clear();
    setResponse(new Response(ResponseType.LOST,
        "You restarted the game. YOU LOSE."));
  }

  @Override
  public void executeNext(){
    getGame().start();
  }
}
