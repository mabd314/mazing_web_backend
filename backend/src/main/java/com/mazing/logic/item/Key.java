package com.mazing.logic.item;

import com.mazing.ItemEntity;
import com.mazing.Response;
import com.mazing.logic.game.Player;
import com.mazing.logic.wall.Wall;

import java.util.Objects;

public class Key extends Item {

  private final int keyId;

  public Key(int keyId) {
    this.keyId = keyId;
  }

  public int getKeyId() {
    return keyId;
  }

  @Override
  public ItemType getType() {
    return ItemType.KEY;
  }

  @Override
  public ItemEntity getItemEntity() {
    ItemEntity itemEntity=new ItemEntity();
    itemEntity.setWallId(getWallId());
    itemEntity.setItemType(getType());
    itemEntity.setPrice(getPrice());
    itemEntity.setUserName(getUserName());
    itemEntity.setItemId(getItemId());
    itemEntity.setKeyId(keyId);
    return itemEntity;

  }

  @Override
  public Response use(Player player) {
    Wall facing = player.facingWall();
    return facing.toggleWithKey(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Key key = (Key) o;
    return keyId == key.keyId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyId);
  }

  @Override
  public String toString() {
    return super.toString() + keyId;
  }
}
