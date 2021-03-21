package com.mazing.logic.item;

public enum ItemType {
  KEY,
  GOLD,
  FLASHLIGHT,
  NOITEM;

  public static ItemType getItemType(String itemName) {
    for (ItemType type : ItemType.values()) {
      if (itemName.equalsIgnoreCase(type.toString())) {
        return type;
      }
    }
    return ItemType.NOITEM;
  }
}
