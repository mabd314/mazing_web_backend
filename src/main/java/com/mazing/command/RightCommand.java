package com.mazing.command;

import com.mazing.game.Response;
import com.mazing.game.ResponseType;

public class RightCommand extends MainCommand {

  @Override
  public void applyEffect() {
    getGame().getCharacter().turnRight();
    setResponse(new Response(ResponseType.SUCCESS, "You turned right"));
  }
}
