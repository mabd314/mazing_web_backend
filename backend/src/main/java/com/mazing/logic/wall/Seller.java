package com.mazing.logic.wall;

import com.mazing.logic.game.Game;
import com.mazing.logic.game.Response;
import com.mazing.logic.game.ResponseType;
import com.mazing.logic.item.Item;
import com.mazing.logic.item.Key;

import java.util.List;

public class Seller extends Wall {

  private final List<Item> items;

  public Seller(List<Item> items) {
    this.items = items;
  }

  @Override
  public Response list() {
    StringBuilder list = new StringBuilder();
    list.append("Seller Items:\n");
    for (Item item : items) {
      list.append(item).append(" : ").append(item.getPrice()).append(" Gold\n");
    }
    return new Response(ResponseType.STATUS, list.toString());
  }

  @Override
  public WallType getType() {
    return WallType.SELLER;
  }

  @Override
  public Response wallSpecificCheck(Game game) {
    return new Response(
        ResponseType.INVALID,
        "You can not check the seller, pay some respect! You can trade though...");
  }

  @Override
  public Response toggleWithKey(Key key) {
    return new Response(
        ResponseType.INVALID, "The seller is really angry! Where are you trying to put this key?");
  }

  @Override
  public List<Item> getTradingList() {
    return items;
  }

  @Override
  public String toString() {
    return "a Seller";
  }
}
