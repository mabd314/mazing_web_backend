package com.mazing.logic.item;

import com.mazing.entities.ItemEntity;
import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;
import com.mazing.logic.game.Player;

public class NoItem extends Item {

  private static NoItem instance;

  private NoItem() {}

  @Override
  public ItemEntity getItemEntity() {
    ItemEntity itemEntity=new ItemEntity();
    itemEntity.setItemType(ItemType.NOITEM);
    return itemEntity;
  }

  public static synchronized NoItem getInstance() {
    if (instance == null) {
      instance = new NoItem();
    }
    return instance;
  }

  @Override
  public Response use(Player player) {
    return new Response(
        ResponseType.INVALID, "How come you want to use an item that you do not have?");
  }

  @Override
  public Response buy(Player player) {
    return new Response(ResponseType.INVALID, "The seller does not have what you desire");
  }

  @Override
  public Response sell(Player player) {
    return new Response(ResponseType.INVALID, "The seller does not want to buy this item");
  }

  @Override
  public ItemType getType() {
    return ItemType.NOITEM;
  }
}
