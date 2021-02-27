package com.mazing.logic.command;

import com.mazing.logic.item.Item;

public class BuyCommand extends TradingCommand {
  String itemName;

  public BuyCommand(String itemName) {
    this.itemName = itemName;
  }

  @Override
  public void execute() {
    Item item = Item.getItemFromList(itemName, getPlayer().facingWallTradingList());
    setResponse(item.buy(getPlayer()));
    getPlayer().getPlayerEntity().save();
    item.getItemEntity().save();
  }
}
