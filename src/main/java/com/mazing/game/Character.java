package com.mazing.game;

import com.mazing.item.Gold;
import com.mazing.item.Item;
import com.mazing.item.ItemType;
import java.util.ArrayList;
import java.util.List;

public class Character {

  private final List<Item> items;
  private final Gold gold;
  private Direction direction;
  private boolean isFlashLightOn;

  public Character(Direction direction) {
    this.direction = direction;
    items = new ArrayList<>();
    gold = new Gold(0);
    isFlashLightOn = false;
  }

  public void addItems(List<? extends Item> items) {
    for (Item item : items) {
      addItem(item);
    }
  }

  public void addItem(Item item) {
    if (itemIsGold(item)) {
      gold.merge((Gold) item);
    } else {
      items.add(item);
    }
  }

  private boolean itemIsGold(Item item){
    return item.getType()==ItemType.GOLD;
  }


  public boolean hasItem(Item item) {
    return items.contains(item);
  }

  public boolean removeItem(Item item) {
    return items.remove(item);
  }

  public List<Item> getItems() {
    return items;
  }

  public Direction getDirection() {
    return direction;
  }

  public Gold getGold() {
    return gold;
  }

  public boolean isFlashLightOn() {
    return isFlashLightOn;
  }

  public void setFlashLightOn(boolean flashLightOn) {
    isFlashLightOn = flashLightOn;
  }

  public void turnLeft() {
    direction = direction.leftDirection();
  }

  public void turnRight() {
    direction = direction.rightDirection();
  }

  public Direction getOppositeDirection() {
    return direction.oppositeDirection();
  }
}
