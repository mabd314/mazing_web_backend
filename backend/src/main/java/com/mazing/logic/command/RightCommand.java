package com.mazing.logic.command;

import com.mazing.logic.game.Response;
import com.mazing.logic.game.ResponseType;

public class RightCommand extends MainCommand {

  @Override
  public void applyEffect() {
    getGame().getCharacter().turnRight();
    setResponse(new Response(ResponseType.SUCCESS, "You turned right"));
  }
}
