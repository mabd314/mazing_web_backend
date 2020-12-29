package com.mazing.command;

import com.mazing.item.Item;

public class UseCommand extends MainCommand {
  String itemName;

  public UseCommand(String itemName) {
    this.itemName = itemName;
  }

  @Override
  public void applyEffect() {
    Item item = Item.getItemFromList(itemName, getGame().getCharacterItems());
    setResponse(item.use(getGame()));
  }
}
