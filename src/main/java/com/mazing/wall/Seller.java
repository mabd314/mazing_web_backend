package com.mazing.wall;
import com.mazing.game.*;
import com.mazing.item.*;
import com.mazing.command.*;
import com.mazing.map.*;

import java.util.List;

public class Seller extends Wall {

  private final List<Item> items;

  public Seller(List<Item> items) {
    this.items = items;
  }

  @Override
  public WallType getType() {
    return WallType.SELLER;
  }

  @Override
  public Response check(Game game) {
    return new Response(ResponseType.INVALID,
        "You can not check the seller, pay some respect! You can trade though...");
  }

  @Override
  public Response toggleWithKey(Key key) {
    return new Response(ResponseType.INVALID,
        "The seller is really angry! Where are you trying to put this key?");
  }

  @Override
  public List<Item> getTradingList() {
    return items;
  }


  @Override
  public String toString() {
    return "a com.mazing.wall.Seller";
  }
}
