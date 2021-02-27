package com.mazing.logic.command;

import com.mazing.Response;
import com.mazing.ResponseType;

public class LeftCommand extends MainCommand {

  @Override
  public void execute() {
    getPlayer().turnLeft();
    setResponse(new Response(ResponseType.SUCCESS, "You turned left"));
    getPlayer().getPlayerEntity().save();
  }
}
