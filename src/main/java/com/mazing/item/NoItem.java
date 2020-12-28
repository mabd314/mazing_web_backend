package com.mazing.item;
import com.mazing.game.*;

public class NoItem extends Item {

  private static NoItem instance;

  private NoItem() {
  }

  public static synchronized NoItem getInstance() {
    if (instance == null) {
      instance = new NoItem();
    }
    return instance;
  }
  @Override
  public Response use(Game game){
    return new Response(ResponseType.INVALID,
        "How come you want to use an item that you do not have?");
  }

  @Override
  public Response buy(Game game){
    return new Response(ResponseType.INVALID,
        "The seller does not have what you desire");
  }

  @Override
  public Response sell(Game game){
    return new Response(ResponseType.INVALID,
        "The seller does not want to buy this item");
  }

  @Override
  public ItemType getType() {
    return ItemType.NOITEM;
  }
}
