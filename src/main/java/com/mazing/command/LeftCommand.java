package com.mazing.command;

import com.mazing.game.Response;
import com.mazing.game.ResponseType;

public class LeftCommand extends MainCommand {

  @Override
  public void applyEffect() {
    getGame().getCharacter().turnLeft();
    setResponse(new Response(ResponseType.SUCCESS, "You turned left"));
  }
}
