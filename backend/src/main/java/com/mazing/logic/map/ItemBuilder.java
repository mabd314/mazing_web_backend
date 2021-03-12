package com.mazing.logic.map;

import com.mazing.ItemEntity;
import com.mazing.logic.item.*;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

  public static List<Item> buildItemsFromEntity(List<ItemEntity> itemEntities) {
    List<Item> items = new ArrayList<>();
    for (ItemEntity itemEntity : itemEntities) {
      Item item = buildItemFromEntity(itemEntity);
      items.add(item);
    }
    return items;
  }

  public static Item buildItemFromEntity(ItemEntity itemEntity) {
    switch (itemEntity.getItemType()) {
      case KEY->{return buildKeyFromEntity(itemEntity); }
      case FLASHLIGHT->{return buildFlashLightFromEntity(itemEntity); }
      case GOLD->{return buildGoldFromEntity(itemEntity); }
      default -> {return NoItem.getInstance();}
    }
  }

  private static Item buildKeyFromEntity(ItemEntity itemEntity) {
    Item item = new Key(itemEntity.getKeyId());
    item.setPrice(itemEntity.getPrice());
    item.setItemId(itemEntity.getItemId());
    item.setUserName(itemEntity.getUserName());
    item.setWallId(itemEntity.getWallId());
    return item;
  }

  private static Item buildFlashLightFromEntity(ItemEntity itemEntity) {
    Item item = new FlashLight();
    item.setPrice(itemEntity.getPrice());
    item.setItemId(itemEntity.getItemId());
    item.setUserName(itemEntity.getUserName());
    item.setWallId(itemEntity.getWallId());
    return item;
  }

  private static Item buildGoldFromEntity(ItemEntity itemEntity) {
    Item item = new Gold(itemEntity.getGoldCount());
    item.setPrice(itemEntity.getPrice());
    item.setItemId(itemEntity.getItemId());
    item.setUserName(itemEntity.getUserName());
    item.setWallId(itemEntity.getWallId());
    return item;
  }



}
