package com.mazing.logic.wall;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;
import com.mazing.entities.WallEntity;
import com.mazing.logic.game.Player;
import com.mazing.logic.item.Key;
import com.mazing.logic.item.NoKey;

public class Painting extends Wall {

  private Key hidden;

  public Painting(Key key) {
    this.hidden = key;
  }

  public void clear() {
    this.hidden = NoKey.getInstance();
  }

  @Override
  public WallEntity getWallEntity() {
      WallEntity wallEntity=new WallEntity();
      wallEntity.setWallType(WallType.PAINTING);
      wallEntity.setWallId(getWallId());
      wallEntity.setHiddenKeyId(hidden.getKeyId());
      wallEntity.setGameId(getGameId());
      return wallEntity;
  }

  @Override
  public WallType getType() {
    return WallType.PAINTING;
  }

  @Override
  public Response getThrough(Player player) {
    return new Response(ResponseType.INVALID, "You can only move through doors");
  }

  @Override
  public Response wallSpecificCheck(Player player) {
//    synchronized (Painting.this){
      if (hidden.equals(NoKey.getInstance())) {
        return new Response(ResponseType.EMPTY, "There is nothing behind this painting");
      }
      player.addItem(hidden);
      hidden.setUserName(player.getUserName());
      hidden.setWallId(0);
      hidden.getItemEntity().save();
      Response status =
              new Response(ResponseType.SUCCESS, "You found " + hidden + " behind this painting");
      clear();
      return status;
//    }
  }

  @Override
  public String toString() {
    return "a Painting";
  }
}
