package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class RightCommand extends MainCommand{

  @Override
  public void applyEffect() {
    getGame().getCharacter().turnRight();
    setResponse(new Response(ResponseType.SUCCESS,
        "You turned right"));
  }

}
