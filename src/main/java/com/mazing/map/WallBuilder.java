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
public class WallBuilder {


  public static Wall buildWallFromJson(JSONObjectWrapper jsonWall) {
    switch (jsonWall.getString("type")) {
      case "door"->{return buildDoorFromJson(jsonWall);}
      case "seller"->{return buildSellerFromJson(jsonWall);}
      case "chest"->{return buildChestFromJson(jsonWall);}
      case "painting"->{return buildPaintingFromJson(jsonWall);}
      case "mirror"->{return buildMirrorFromJson(jsonWall);}
      default -> {return Empty.getInstance(); }
    }
  }

  private static Wall buildMirrorFromJson(JSONObjectWrapper jsonMirror) {
    if (jsonMirror.getInt("behind") == 0) {
      return new Mirror(NoKey.getInstance());
    }
    Key key = new Key(jsonMirror.getInt("behind"));
    return new Mirror(key);
  }

  private static Wall buildPaintingFromJson(JSONObjectWrapper jsonPainting) {
    if (jsonPainting.getInt("behind") == 0) {
      return new Painting(NoKey.getInstance());
    }
    Key key = new Key(jsonPainting.getInt("behind"));
    return new Painting(key);
  }

  private static Wall buildChestFromJson(JSONObjectWrapper jsonChest) {
    JSONArrayWrapper jsonItems = jsonChest.getJsonArray("inside");
    List<Item> items = ItemBuilder.buildItemsFromJson(jsonItems);
    if (jsonChest.getBoolean("locked")) {
      return new Chest.Builder(items)
          .lockedWithKey(jsonChest.getInt("keyId"))
          .build();
    }

    return new Chest.Builder(items)
        .build();
  }

  private static Wall buildSellerFromJson(JSONObjectWrapper jsonSeller) {
    JSONArrayWrapper jsonItems = jsonSeller.getJsonArray("items");
    List<Item> items = ItemBuilder.buildItemsFromJson(jsonItems);
    return new Seller(items);
  }

  private static Wall buildDoorFromJson(JSONObjectWrapper jsonDoor) {
    if (jsonDoor.getBoolean("locked")) {
      return new Door.Builder(jsonDoor.getInt("from"),jsonDoor.getInt("to"))
          .lockedWithKey(jsonDoor.getInt("keyId"))
          .build();
    }
    return new Door.Builder(jsonDoor.getInt("from"),jsonDoor.getInt("to"))
        .build();
  }

}
