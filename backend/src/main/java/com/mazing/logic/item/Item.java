package com.mazing.logic.item;

import com.mazing.ItemEntity;
import com.mazing.Response;
import com.mazing.ResponseType;
import com.mazing.logic.game.Player;

import java.util.List;
import java.util.Objects;

public abstract class Item {

  private int price;
  private int itemId;
  private int wallId;
  private String userName;


  public Item() {
    price = 0;
  }

  public abstract ItemEntity getItemEntity();

  public int getWallId() {
    return wallId;
  }

  public void setWallId(int wallId) {
    this.wallId = wallId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
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

  public Response use(Player player) {
    return new Response(ResponseType.INVALID, "This item is unusable");
  }

  public Response buy(Player player) {
    if (player.getGold().payForItem(this)) {
      player.addItem(this);
      wallId=0;
      userName =player.getUserName();
      return new Response(ResponseType.SUCCESS, "You bought an item: " + this);
    }
    return new Response(ResponseType.FAILURE, "You do not have enough gold to buy this item");
  }

  public Response sell(Player player) {
    if (player.removeItem(this)) {
      player.getGold().getPaidForItem(this);
      userName ="";
      wallId=player.facingWall().getWallId();
      return new Response(
          ResponseType.SUCCESS, "You Sold an item: " + this + " for " + this.getPrice() + " gold");
    }
    return new Response(
        ResponseType.INVALID, "How come you want to sell an item that you do not have?");
  }

  public abstract ItemType getType();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return itemId == item.itemId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId);
  }

  @Override
  public String toString() {
    return getType().toString();
  }
}
