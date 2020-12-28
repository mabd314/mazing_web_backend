package com.mazing.command;

import com.mazing.item.*;

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
