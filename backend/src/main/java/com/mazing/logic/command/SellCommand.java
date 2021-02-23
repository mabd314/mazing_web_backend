package com.mazing.logic.command;

import com.mazing.logic.item.Item;

public class SellCommand extends TradingCommand {
  String itemName;

  public SellCommand(String itemName) {
    this.itemName = itemName;
  }

  @Override
  public void applyEffect() {
    Item item = Item.getItemFromList(itemName, getGame().getFacingWallTradingList());
    setResponse(item.sell(getGame()));
  }
}
