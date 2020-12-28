import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonMap implements Map {

  private final JSONParser jsonParser = new JSONParser();
  private final String mapFileName;
  private JSONObject jsonMap;

  public JsonMap(String mapFileName) {
    this.mapFileName = mapFileName;
  }

  private int getIntFromObject(Object obj) {
    return (int) (long) obj;
  }

  private Wall getWallFromJson(JSONObject jsonWall) {
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

  private Wall buildMirrorFromJson(JSONObject jsonMirror) {
      if (jsonMirror.get("behind") == null) {
          return new Mirror(NoKey.getInstance());
      }
    Key key = new Key(getIntFromObject(jsonMirror.get("behind")));
    return new Mirror(key);
  }

  private Wall buildPaintingFromJson(JSONObject jsonPainting) {
      if (jsonPainting.get("behind") == null) {
          return new Painting(NoKey.getInstance());
      }
    Key key = new Key(getIntFromObject(jsonPainting.get("behind")));
    return new Painting(key);
  }

  private List<Item> buildItemsFromJson(JSONArray jsonItems) {
    List<Item> items = new ArrayList<>();
    for (Object objectItem : jsonItems) {
      JSONObject jsonItem = (JSONObject) objectItem;
      Item item = buildItemFromJson(jsonItem);
      items.add(item);
    }
    return items;
  }

  private Item buildItemFromJson(JSONObject jsonItem) {
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

  private Item buildGoldFromJson(JSONObject jsonGold) {
    int count = getIntFromObject(jsonGold.get("count"));
    Item item = new Gold(count);
      if (jsonGold.get("price") != null) {
          item.setPrice(getIntFromObject(jsonGold.get("price")));
      }
    return item;
  }

  private Item buildFlashLightFromJson(JSONObject jsonFlashLight) {
    Item item = FlashLight.getInstance();
      if (jsonFlashLight.get("price") != null) {
          item.setPrice(getIntFromObject(jsonFlashLight.get("price")));
      }
    return item;
  }

  private Item buildKeyFromJson(JSONObject jsonKey) {
    Item item = new Key(getIntFromObject(jsonKey.get("keyId")));
      if (jsonKey.get("price") != null) {
          item.setPrice(getIntFromObject(jsonKey.get("price")));
      }
    return item;
  }


  private Wall buildChestFromJson(JSONObject jsonChest) {
    JSONArray jsonItems = (JSONArray) jsonChest.get("inside");
    List<Item> items = buildItemsFromJson(jsonItems);
      if ((Boolean) jsonChest.get("locked")) {
          return new Chest.Builder(items)
              .lockedWithKey(getIntFromObject(jsonChest.get("keyId")))
              .build();
      }

    return new Chest.Builder(items)
        .build();
  }

  private Wall buildSellerFromJson(JSONObject jsonSeller) {
    JSONArray jsonItems = (JSONArray) jsonSeller.get("items");
    List<Item> items = buildItemsFromJson(jsonItems);
    return new Seller(items);
  }

  private Wall buildDoorFromJson(JSONObject jsonDoor) {
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

  private void parseJsonMap() throws FileNotFoundException {
    try (FileReader reader = new FileReader(mapFileName)) {
      jsonMap = (JSONObject) jsonParser.parse(reader);
    } catch (FileNotFoundException e) {
      throw e;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void setUpRooms(Game game) throws FileNotFoundException {
    List<Room> rooms = new ArrayList<>();
    parseJsonMap();
    JSONArray jsonRooms = (JSONArray) jsonMap.get("rooms");
    for (Object roomObj : jsonRooms) {
      JSONObject jsonRoom = (JSONObject) roomObj;
      Room room = new Room(getIntFromObject(jsonRoom.get("id")));
      JSONObject jsonWall = (JSONObject) jsonRoom.get("east");
        if (jsonWall != null) {
            room.setEast(getWallFromJson(jsonWall));
        }
      jsonWall = (JSONObject) jsonRoom.get("west");
        if (jsonWall != null) {
            room.setWest(getWallFromJson(jsonWall));
        }
      jsonWall = (JSONObject) jsonRoom.get("north");
        if (jsonWall != null) {
            room.setNorth(getWallFromJson(jsonWall));
        }
      jsonWall = (JSONObject) jsonRoom.get("south");
        if (jsonWall != null) {
            room.setSouth(getWallFromJson(jsonWall));
        }

        if (!((Boolean) jsonRoom.get("isLit"))) {
            room.toggleLight();
        }
        if (!((Boolean) jsonRoom.get("hasLight"))) {
            room.removeLight();
        }
      rooms.add(room);
    }
    game.setCurrentRoomId(1);
    game.setRooms(rooms);
  }

  @Override
  public void setUpCharacter(Game game) {
    Character character;
    String directionString = (String) jsonMap.get("direction");
    Direction direction = Direction.valueOf(directionString.toUpperCase());
    int goldCount = getIntFromObject(jsonMap.get("goldCount"));
    long secondsNeeded = (long) jsonMap.get("secondsNeeded");
    List<Item> items = buildItemsFromJson((JSONArray) jsonMap.get("items"));

    character = new Character(direction);
    character.getGold().increment(goldCount);
    character.addItems(items);
    game.setCharacter(character);
    game.setStopWatch(new StopWatch(secondsNeeded));
  }
}
