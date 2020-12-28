package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class LeftCommand extends MainCommand {

  @Override
  public void applyEffect() {
    getGame().getCharacter().turnLeft();
    setResponse(new Response(ResponseType.SUCCESS,
        "You turned left"));
  }

}
