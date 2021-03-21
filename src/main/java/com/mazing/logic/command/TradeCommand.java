package com.mazing.logic.command;

import com.mazing.entities.ResponseType;

public class TradeCommand extends MainCommand {

  private boolean isResponseInvalid() {
    return getResponse().getType() == ResponseType.INVALID;
  }

  @Override
  public void execute() {
    setResponse(getPlayer().facingWall().list());
    getPlayer().setInTradeMode(!isResponseInvalid());
    getPlayer().getPlayerEntity().save();
  }
}
