package com.mazing.logic.item;

import com.mazing.entities.ItemEntity;

public class Gold extends Item {

  private int goldCount;

  public Gold(int goldCount) {
    this.goldCount = goldCount;
  }

  public void merge(Gold gold) {
    increment(gold.goldCount);
  }

  public void getPaidForItem(Item item) {
    increment(item.getPrice());
  }

  private void increment(int inc) {
    goldCount += inc;
  }

  public boolean payForItem(Item item) {
    return decrement(item.getPrice());
  }

  public int getGoldCount() {
    return goldCount;
  }

  private boolean decrement(int dec) {
    if (goldCount - dec >= 0) {
      goldCount -= dec;
      return true;
    }
    return false;
  }

  @Override
  public ItemEntity getItemEntity() {
    ItemEntity itemEntity=new ItemEntity();
    itemEntity.setWallId(getWallId());
    itemEntity.setItemType(getType());
    itemEntity.setPrice(getPrice());
    itemEntity.setUserName(getUserName());
    itemEntity.setItemId(getItemId());
    itemEntity.setGoldCount(goldCount);
    return itemEntity;
  }

  @Override
  public ItemType getType() {
    return ItemType.GOLD;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + goldCount;
  }
}
