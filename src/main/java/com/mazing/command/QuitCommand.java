package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class QuitCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(new Response(ResponseType.LOST,
        "You quit the game. YOU LOSE."));
  }

  public void executeNext(){
    System.exit(1);
  }

}
