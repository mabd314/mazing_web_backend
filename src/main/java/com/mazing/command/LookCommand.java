package com.mazing.command;


public class LookCommand extends MainCommand {

  @Override
  public void applyEffect() {
    setResponse(getGame().getCurrentRoom().look(getGame()));
  }

}
