package com.mazing.logic.item;

import com.mazing.entities.ItemEntity;
import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;
import com.mazing.logic.game.Player;

import java.util.Objects;

public class FlashLight extends Item {

  public static FlashLight getInstance() {
    return new FlashLight();
  }

  @Override
  public ItemEntity getItemEntity() {
    ItemEntity itemEntity=new ItemEntity();
    itemEntity.setWallId(getWallId());
    itemEntity.setItemType(getType());
    itemEntity.setPrice(getPrice());
    itemEntity.setUserName(getUserName());
    itemEntity.setItemId(getItemId());
    return itemEntity;
  }

  @Override
  public ItemType getType() {
    return ItemType.FLASHLIGHT;
  }

  @Override
  public Response use(Player player) {
    player.setFlashLightOn(!player.isFlashLightOn());
    return new Response(
        ResponseType.SUCCESS,
        "Your flashlight is " + (player.isFlashLightOn() ? "ON" : "OFF"));
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    return true;
  }

  @Override
  public int hashCode() {
      return Objects.hash(this.getClass());
  }
}
