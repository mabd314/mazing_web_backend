package com.mazing.logic.command;

import com.mazing.logic.item.Item;

public class UseCommand extends MainCommand {
  String itemName;

  public UseCommand(String itemName) {
    this.itemName = itemName;
  }

  @Override
  public void execute() {
    Item item = Item.getItemFromList(itemName, getPlayer().getItems());
    setResponse(item.use(getPlayer()));
    getPlayer().getPlayerEntity().save();
  }
}
