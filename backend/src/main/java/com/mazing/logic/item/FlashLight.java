package com.mazing.logic.item;

import com.mazing.ItemEntity;
import com.mazing.Response;
import com.mazing.ResponseType;
import com.mazing.logic.game.Player;

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
}
