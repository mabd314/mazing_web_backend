package com.mazing.map;

import com.mazing.item.Item;
import com.mazing.item.Key;
import com.mazing.item.NoKey;
import com.mazing.wall.Chest;
import com.mazing.wall.Door;
import com.mazing.wall.Empty;
import com.mazing.wall.Mirror;
import com.mazing.wall.Painting;
import com.mazing.wall.Seller;
import com.mazing.wall.Wall;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static com.mazing.map.JsonMaze.*;
public class WallBuilder {


  public static Wall buildWallFromJson(JSONObject jsonWall) {
    switch ((String) jsonWall.get("type")) {
      case "door":
        return buildDoorFromJson(jsonWall);
      case "seller":
        return buildSellerFromJson(jsonWall);
      case "chest":
        return buildChestFromJson(jsonWall);
      case "painting":
        return buildPaintingFromJson(jsonWall);
      case "mirror":
        return buildMirrorFromJson(jsonWall);
    }
    return Empty.getInstance();
  }

  private static Wall buildMirrorFromJson(JSONObject jsonMirror) {
    if (jsonMirror.get("behind") == null) {
      return new Mirror(NoKey.getInstance());
    }
    Key key = new Key(getIntFromObject(jsonMirror.get("behind")));
    return new Mirror(key);
  }

  private static Wall buildPaintingFromJson(JSONObject jsonPainting) {
    if (jsonPainting.get("behind") == null) {
      return new Painting(NoKey.getInstance());
    }
    Key key = new Key(getIntFromObject(jsonPainting.get("behind")));
    return new Painting(key);
  }

  private static Wall buildChestFromJson(JSONObject jsonChest) {
    JSONArray jsonItems = (JSONArray) jsonChest.get("inside");
    List<Item> items = ItemBuilder.buildItemsFromJson(jsonItems);
    if ((Boolean) jsonChest.get("locked")) {
      return new Chest.Builder(items)
          .lockedWithKey(getIntFromObject(jsonChest.get("keyId")))
          .build();
    }

    return new Chest.Builder(items)
        .build();
  }

  private static Wall buildSellerFromJson(JSONObject jsonSeller) {
    JSONArray jsonItems = (JSONArray) jsonSeller.get("items");
    List<Item> items = ItemBuilder.buildItemsFromJson(jsonItems);
    return new Seller(items);
  }

  private static Wall buildDoorFromJson(JSONObject jsonDoor) {
    if ((Boolean) jsonDoor.get("locked")) {
      return new Door.Builder(getIntFromObject(jsonDoor.get("from")),
          getIntFromObject(jsonDoor.get("to")))
          .lockedWithKey(getIntFromObject(jsonDoor.get("keyId")))
          .build();
    }

    return new Door.Builder(getIntFromObject(jsonDoor.get("from")),
        getIntFromObject(jsonDoor.get("to")))
        .build();
  }

}
