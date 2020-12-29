package com.mazing.map;

import com.mazing.item.FlashLight;
import com.mazing.item.Gold;
import com.mazing.item.Item;
import com.mazing.item.Key;
import com.mazing.item.NoItem;
import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

  public static List<Item> buildItemsFromJson(JSONArrayWrapper jsonItems) {
    List<Item> items = new ArrayList<>();
    for (JSONObjectWrapper jsonItem : jsonItems.getList()) {
      Item item = buildItemFromJson(jsonItem);
      items.add(item);
    }
    return items;
  }

  public static Item buildItemFromJson(JSONObjectWrapper jsonItem) {
    switch (jsonItem.getString("type")) {
      case "key"->{return buildKeyFromJson(jsonItem); }
      case "flashlight"->{return buildFlashLightFromJson(jsonItem); }
      case "gold"->{return buildGoldFromJson(jsonItem); }
      default -> {return NoItem.getInstance();}
    }
  }

  private static Item buildKeyFromJson(JSONObjectWrapper jsonKey) {
    Item item = new Key(jsonKey.getInt("keyId"));
    item.setPrice(jsonKey.getInt("price"));
    return item;
  }

  private static Item buildFlashLightFromJson(JSONObjectWrapper jsonFlashLight) {
    Item item = FlashLight.getInstance();
    item.setPrice(jsonFlashLight.getInt("price"));
    return item;
  }

  private static Item buildGoldFromJson(JSONObjectWrapper jsonGold) {
    Item item = new Gold(jsonGold.getInt("count"));
    item.setPrice(jsonGold.getInt("price"));
    return item;
  }



}
