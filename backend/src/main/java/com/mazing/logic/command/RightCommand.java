package com.mazing.logic.command;

import com.mazing.Response;
import com.mazing.ResponseType;

public class RightCommand extends MainCommand {

  @Override
  public void execute() {
    getPlayer().turnRight();
    setResponse(new Response(ResponseType.SUCCESS, "You turned right"));
    getPlayer().getPlayerEntity().save();
  }
}
