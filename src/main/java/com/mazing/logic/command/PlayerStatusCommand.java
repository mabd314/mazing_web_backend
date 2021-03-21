package com.mazing.logic.command;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;

public class PlayerStatusCommand extends MainCommand {

  @Override
  public void execute() {
    setResponse(
            new Response(
                    ResponseType.STATUS,
                    "Player is facing: "
                            + getPlayer().getDirection()
                            + "\n"
                            + getPlayer().getGold()
                            + "\n"
                            + "Items: "
                            + getPlayer().getItems()));
  }
}
