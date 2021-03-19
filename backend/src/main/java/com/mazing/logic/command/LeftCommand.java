package com.mazing.logic.command;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;

public class LeftCommand extends MainCommand {

  @Override
  public void execute() {
    getPlayer().turnLeft();
    setResponse(new Response(ResponseType.SUCCESS, "You turned left"));
    getPlayer().getPlayerEntity().save();
  }
}
