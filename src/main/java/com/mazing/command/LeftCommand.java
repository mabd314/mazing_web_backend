package com.mazing.command;
import com.mazing.game.*;

public class LeftCommand extends MainCommand {

  @Override
  public void applyEffect() {
    getGame().getCharacter().turnLeft();
    setResponse(new Response(ResponseType.SUCCESS,
        "You turned left"));
  }

}
