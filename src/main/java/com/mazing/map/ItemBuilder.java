package com.mazing.map;

import com.mazing.item.FlashLight;
import com.mazing.item.Gold;
import com.mazing.item.Item;
import com.mazing.item.Key;
import com.mazing.item.NoItem;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static com.mazing.map.JsonMaze.*;

public class ItemBuilder {

  public static List<Item> buildItemsFromJson(JSONArray jsonItems) {
    List<Item> items = new ArrayList<>();
    for (Object objectItem : jsonItems) {
      JSONObject jsonItem = (JSONObject) objectItem;
      Item item = buildItemFromJson(jsonItem);
      items.add(item);
    }
    return items;
  }

  public static Item buildItemFromJson(JSONObject jsonItem) {
    switch ((String) jsonItem.get("type")) {
      case "key":
        return buildKeyFromJson(jsonItem);
      case "flashlight":
        return buildFlashLightFromJson(jsonItem);
      case "gold":
        return buildGoldFromJson(jsonItem);
    }
    return NoItem.getInstance();
  }

  private static Item buildGoldFromJson(JSONObject jsonGold) {
    int count = getIntFromObject(jsonGold.get("count"));
    Item item = new Gold(count);
    if (jsonGold.get("price") != null) {
      item.setPrice(getIntFromObject(jsonGold.get("price")));
    }
    return item;
  }

  private static Item buildFlashLightFromJson(JSONObject jsonFlashLight) {
    Item item = FlashLight.getInstance();
    if (jsonFlashLight.get("price") != null) {
      item.setPrice(getIntFromObject(jsonFlashLight.get("price")));
    }
    return item;
  }

  private static Item buildKeyFromJson(JSONObject jsonKey) {
    Item item = new Key(getIntFromObject(jsonKey.get("keyId")));
    if (jsonKey.get("price") != null) {
      item.setPrice(getIntFromObject(jsonKey.get("price")));
    }
    return item;
  }

}
