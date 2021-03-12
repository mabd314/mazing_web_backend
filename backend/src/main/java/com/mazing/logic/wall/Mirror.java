package com.mazing.logic.wall;

import com.mazing.Response;
import com.mazing.ResponseType;
import com.mazing.WallEntity;
import com.mazing.logic.game.Player;
import com.mazing.logic.item.Key;
import com.mazing.logic.item.NoKey;

public class Mirror extends Wall {

  private Key hidden;

  public Mirror(Key key) {
    this.hidden = key;
  }

  public void clear() {
    this.hidden = NoKey.getInstance();
  }

  @Override
  public WallEntity getWallEntity() {
    WallEntity wallEntity=new WallEntity();
    wallEntity.setWallType(WallType.MIRROR);
    wallEntity.setWallId(getWallId());
    wallEntity.setHiddenKeyId(hidden.getKeyId());
    return wallEntity;
  }

  @Override
  public WallType getType() {
    return WallType.MIRROR;
  }

  @Override
  public Response wallSpecificCheck(Player player) {
    if (hidden.equals(NoKey.getInstance())) {
      return new Response(ResponseType.EMPTY, "There is nothing behind this mirror");
    }
    player.addItem(hidden);
    hidden.setUserName(player.getUserName());
    hidden.setWallId(0);
    hidden.getItemEntity().save();
    Response status =
        new Response(ResponseType.SUCCESS, "You found " + hidden + " behind this mirror");
    clear();
    return status;
  }

  @Override
  public String toString() {
    return "a Mirror";
  }
}
