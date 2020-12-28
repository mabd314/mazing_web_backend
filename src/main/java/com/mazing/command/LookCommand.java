package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class LookCommand extends MainCommand {

  @Override
  public void applyEffect() {
    if (getGame().isCurrentRoomLit()) {
      setResponse(new Response(ResponseType.STATUS,
          getGame().getFacingWallDescription()));
    }
    else{
      setResponse(new Response(ResponseType.FAILURE,
          "The room is dark, you can not see anything"));
    }
  }
}
