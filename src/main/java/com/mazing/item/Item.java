package com.mazing.item;

import com.mazing.game.Game;
import com.mazing.game.Response;
import com.mazing.game.ResponseType;
import java.util.List;

public abstract class Item {

  private int price;

  public Item() {
    this.price = 0;
  }

  public static Item getItemFromList(String itemName, List<Item> items) {
    for (Item item : items) {
      if (item.toString().equalsIgnoreCase(itemName)) {
        return item;
      }
    }
    return NoItem.getInstance();
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Response use(Game game) {
    return new Response(ResponseType.INVALID, "This item is unusable");
  }

  public Response buy(Game game) {
    if (game.getGold().payForItem(this)) {
      game.getCharacter().addItem(this);
      return new Response(ResponseType.SUCCESS, "You bought an item: " + this);
    }
    return new Response(ResponseType.FAILURE, "You do not have enough gold to buy this item");
  }

  public Response sell(Game game) {
    if (game.getCharacter().removeItem(this)) {
      game.getGold().getPaidForItem(this);
      return new Response(
          ResponseType.SUCCESS, "You Sold an item: " + this + " for " + this.getPrice() + " gold");
    }
    return new Response(
        ResponseType.INVALID, "How come you want to sell an item that you do not have?");
  }

  public abstract ItemType getType();

  @Override
  public String toString() {
    return getType().toString();
  }
}
