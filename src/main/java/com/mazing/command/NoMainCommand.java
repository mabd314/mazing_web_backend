package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class NoMainCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.INVALID,
        "com.mazing.command.Command not recognized"));
  }

}
