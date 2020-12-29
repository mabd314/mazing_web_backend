package com.mazing.map;
import com.mazing.game.*;
import com.mazing.game.Character;
import com.mazing.item.*;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import static com.mazing.map.ItemBuilder.*;
import static com.mazing.map.WallBuilder.*;

public class JsonMaze implements Maze {

  private final JSONParser jsonParser = new JSONParser();
  private JSONObjectWrapper jsonMap;

  public JsonMaze(String mapFileName) throws IOException {
    parseJsonMap(mapFileName);
  }

  private void parseJsonMap(String mapFileName) throws IOException {
    try (FileReader reader = new FileReader(mapFileName)) {
      jsonMap = new JSONObjectWrapper((JSONObject) jsonParser.parse(reader));
    } catch (ParseException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void setUpRooms(Game game) {
    List<Room> rooms = new ArrayList<>();
    JSONArrayWrapper jsonRooms = jsonMap.getJsonArray("rooms");
    for (JSONObjectWrapper jsonRoom : jsonRooms.getList()) {
      Room room = new Room(jsonRoom.getInt("id"));
      JSONObjectWrapper jsonWall = jsonRoom.getJsonObject("east");
      room.setEast(buildWallFromJson(jsonWall));
      jsonWall = jsonRoom.getJsonObject("west");
      room.setWest(buildWallFromJson(jsonWall));
      jsonWall = jsonRoom.getJsonObject("north");
      room.setNorth(buildWallFromJson(jsonWall));
      jsonWall = jsonRoom.getJsonObject("south");
      room.setSouth(buildWallFromJson(jsonWall));
        if (!jsonRoom.getBoolean("isLit")) {
            room.toggleLight();
        }
        if (!jsonRoom.getBoolean("hasLight")) {
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
    String directionString = jsonMap.getString("direction");
    Direction direction = Direction.valueOf(directionString.toUpperCase());
    int goldCount = jsonMap.getInt("goldCount");
    long secondsNeeded = jsonMap.getInt("secondsNeeded");
    List<Item> items = buildItemsFromJson(jsonMap.getJsonArray("items"));

    character = new Character(direction);
    character.getGold().increment(goldCount);
    character.addItems(items);
    game.setCharacter(character);
    game.setStopWatch(new StopWatch(secondsNeeded));
  }
}
