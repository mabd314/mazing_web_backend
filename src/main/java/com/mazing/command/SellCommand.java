package com.mazing.command;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class SellCommand extends TradingCommand {
  String itemName;

  public SellCommand(String itemName) {
    this.itemName = itemName;
  }

  @Override
  public void applyEffect() {
    Item item = Item.getItemFromList(itemName, getGame().getFacingWallTradingList());
    if (item == NoItem.getInstance()) {
      setResponse(new Response(ResponseType.INVALID, "The seller does not want to buy this item"));
    } else if (getGame().getCharacter().removeItem(item)) {
      setResponse(
          new Response(
              ResponseType.SUCCESS,
              "You Sold an item: " + item + " for " + item.getPrice() + " gold"));
    } else {
      getGame().getGold().getPaidForItem(item);
      setResponse(
          new Response(
              ResponseType.INVALID, "How come you want to sell an item that you do not have?"));
    }
  }
}
