package com.mazing.logic.command;

import com.mazing.logic.game.Response;
import com.mazing.logic.game.ResponseType;

public class LeftCommand extends MainCommand {

  @Override
  public void applyEffect() {
    getGame().getCharacter().turnLeft();
    setResponse(new Response(ResponseType.SUCCESS, "You turned left"));
  }
}
