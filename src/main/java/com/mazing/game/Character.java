package com.mazing.game;

import com.mazing.item.*;
import java.util.*;


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

  public void addItem(Item item) {
    if (item.getType() != ItemType.GOLD) {
      items.add(item);
    } else {
      gold.merge((Gold) item);
    }
  }

  public void addItems(List<Item> items) {
    for (Item item : items) {
      addItem(item);
    }
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
    direction=direction.leftDirection();
  }

  public void turnRight() {
    direction=direction.rightDirection();
  }

  public Direction getOppositeDirection() {
    return direction.oppositeDirection();
  }


}
