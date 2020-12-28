package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class ForwardCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(getGame().getThrough(getGame().getDirection()));
  }

}
