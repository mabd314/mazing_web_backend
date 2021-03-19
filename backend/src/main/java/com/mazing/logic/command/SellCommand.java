package com.mazing.logic.command;

import com.mazing.logic.item.Item;

public class SellCommand extends TradingCommand {
  String itemName;

  public SellCommand(String itemName) {
    this.itemName = itemName;
  }

  @Override
  public void execute() {
    Item item = Item.getItemFromList(itemName, getPlayer().facingWallTradingList());
    setResponse(item.sell(getPlayer()));
    getPlayer().getPlayerEntity().save();
  }
}
