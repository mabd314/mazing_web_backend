package com.mazing.item;
import com.mazing.game.*;
import com.mazing.map.*;
import com.mazing.command.*;
import com.mazing.wall.*;

public enum ItemType {
  KEY,
  GOLD,
  FLASHLIGHT,
  NOITEM;

  public static ItemType getItemType(String itemName) {
    for (ItemType type : ItemType.values()) {
      if (itemName.toUpperCase().equals(type.toString())) {
        return type;
      }
    }
    return ItemType.NOITEM;
  }

}
