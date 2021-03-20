package com.mazing.logic.wall;

import com.mazing.Repositories;
import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;
import com.mazing.entities.WallEntity;
import com.mazing.logic.game.Player;
import com.mazing.logic.item.Item;
import com.mazing.logic.item.ItemType;
import com.mazing.logic.item.Key;
import com.mazing.logic.item.NoKey;

import java.util.List;

public class Chest extends Wall {

  private final List<Item> inside;
  private final Key key;
  private boolean isLocked;

  public static class Builder {

    private final List<Item> inside;
    private boolean isLocked;
    private Key key;

    public Builder(List<Item> inside) {
      this.inside = inside;
      isLocked = false;
      key = NoKey.getInstance();
    }

    public Builder lockedWithKey(int keyId) {
      this.isLocked = true;
      this.key = new Key(keyId);
      return this;
    }

    public Builder unlockedWithKey(int keyId){
      this.key=new Key(keyId);
      return this;
    }

    public Chest build() {
      return new Chest(this);
    }
  }

  public Chest(Builder builder) {
    inside = builder.inside;
    key = builder.key;
    isLocked = builder.isLocked;
  }

  @Override
  public WallEntity getWallEntity() {
    WallEntity wallEntity=new WallEntity();
    wallEntity.setWallId(getWallId());
    wallEntity.setWallType(WallType.CHEST);
    wallEntity.setLocked(isLocked);
    wallEntity.setLockingKeyId(key.getKeyId());
    wallEntity.setGameId(getGameId());
    return wallEntity;
  }

  public void clearInside() {
    inside.clear();
  }

  public void toggleLocking() {
//    synchronized (Chest.class){
      isLocked = !isLocked;
//    }
  }

  public Key getKey() {
    return key;
  }

  @Override
  public WallType getType() {
    return WallType.CHEST;
  }

  @Override
  public Response wallSpecificCheck(Player player) {
//    synchronized (Chest.class){
      if (isLocked) {
        return new Response(ResponseType.LOCKED, "Chest is locked, " + key + " is needed to unlock!");
      }
      if (inside.isEmpty()) {
        return new Response(ResponseType.EMPTY, "Chest is empty!");
      }
      player.addItems(inside);
      for(Item item:inside){
        item.setUserName(player.getUserName());
        item.setWallId(0);
        item.getItemEntity().save();
        if(item.getType()==ItemType.GOLD)
          Repositories.itemRepo.delete(item.getItemEntity());
      }
      Response status =
              new Response(ResponseType.SUCCESS, "Chest is unlocked, Items acquired: " + inside);
      clearInside();
      return status;
//    }
  }

  @Override
  public Response toggleWithKey(Key key) {
    if (!this.key.equals(key)) {
      return new Response(ResponseType.FAILURE, "Wrong key!");
    }
    toggleLocking();
    getWallEntity().save();
    return new Response(ResponseType.SUCCESS, "Chest is " + (isLocked ? "locked" : "unlocked"));
  }

  @Override
  public String toString() {
    return "a Chest";
  }

}
