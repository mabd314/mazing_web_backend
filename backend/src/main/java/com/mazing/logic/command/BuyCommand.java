package com.mazing.logic.command;

import com.mazing.logic.item.Item;

public class BuyCommand extends TradingCommand {
  String itemName;

  public BuyCommand(String itemName) {
    this.itemName = itemName;
  }

  @Override
  public void applyEffect() {
    Item item = Item.getItemFromList(itemName, getGame().getFacingWallTradingList());
    setResponse(item.buy(getGame()));
  }
}
