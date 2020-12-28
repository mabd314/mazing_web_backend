package com.mazing.command;

import com.mazing.game.*;

public class NoMainCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.INVALID,
        "Command not recognized"));
  }

}
