package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class PlayerStatusCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.STATUS,
        "Player is facing: " + getGame().getDirection() + "\n" +
            getGame().getGold() + "\n" +
            "Items: " + getGame().getCharacterItems()));
  }
}
