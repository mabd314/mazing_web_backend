package com.mazing.item;
import com.mazing.game.*;
import com.mazing.map.*;
import com.mazing.command.*;
import com.mazing.wall.*;

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
  public ItemType getType() {
    return ItemType.NOITEM;
  }
}
