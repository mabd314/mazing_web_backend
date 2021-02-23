package com.mazing.logic.item;

public class Gold extends Item {

  private int count;

  public Gold(int count) {
    this.count = count;
  }

  public void merge(Gold gold) {
    increment(gold.count);
  }

  public void getPaidForItem(Item item) {
    increment(item.getPrice());
  }

  private void increment(int inc) {
    count += inc;
  }

  public boolean payForItem(Item item) {
    return decrement(item.getPrice());
  }

  private boolean decrement(int dec) {
    if (count - dec >= 0) {
      count -= dec;
      return true;
    }
    return false;
  }

  @Override
  public ItemType getType() {
    return ItemType.GOLD;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + count;
  }
}
