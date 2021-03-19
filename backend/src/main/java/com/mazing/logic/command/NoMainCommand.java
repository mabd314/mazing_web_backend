package com.mazing.logic.command;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;

public class NoMainCommand extends MainCommand {

  @Override
  public void execute() {
    setResponse(new Response(ResponseType.INVALID, "Command not recognized"));
  }
}
