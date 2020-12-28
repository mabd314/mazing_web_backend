package com.mazing.wall;

import com.mazing.game.Game;
import com.mazing.game.Response;
import com.mazing.game.ResponseType;
import com.mazing.item.Item;
import com.mazing.item.Key;
import com.mazing.item.NoKey;
import java.util.List;

public class Chest extends Wall {

  private final List<Item> inside;
  private final Key key;
  private boolean isLocked;

  public Chest(Builder builder) {
    inside = builder.inside;
    key = builder.key;
    isLocked = builder.isLocked;
  }

  public void clearInside() {
    inside.clear();
  }

  public void toggleLocking() {
    isLocked = !isLocked;
  }

  public Key getKey() {
    return key;
  }

  @Override
  public WallType getType() {
    return WallType.CHEST;
  }

  @Override
  public Response wallSpecificCheck(Game game) {
    if (isLocked) {
      return new Response(ResponseType.LOCKED, "Chest is locked, " + key + " is needed to unlock!");
    }
    if (inside.isEmpty()) {
      return new Response(ResponseType.EMPTY, "Chest is empty!");
    }
    game.getCharacter().addItems(inside);
    Response status =
        new Response(ResponseType.SUCCESS, "Chest is unlocked, Items acquired: " + inside);
    clearInside();
    return status;
  }

  @Override
  public Response toggleWithKey(Key key) {
    if (!this.key.equals(key)) {
      return new Response(ResponseType.FAILURE, "Wrong key!");
    }
    toggleLocking();
    return new Response(
        ResponseType.SUCCESS, "com.mazing.wall.Chest is " + (isLocked ? "locked" : "unlocked"));
  }

  @Override
  public String toString() {
    return "a com.mazing.wall.Chest";
  }

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

    public Chest build() {
      return new Chest(this);
    }
  }
}
