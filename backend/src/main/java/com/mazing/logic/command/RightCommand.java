package com.mazing.logic.command;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;

public class RightCommand extends MainCommand {

  @Override
  public void execute() {
    getPlayer().turnRight();
    setResponse(new Response(ResponseType.SUCCESS, "You turned right"));
    getPlayer().getPlayerEntity().save();
  }
}
