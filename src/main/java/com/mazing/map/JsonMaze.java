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
  private JSONObject jsonMap;

  public JsonMaze(String mapFileName) throws IOException {
    parseJsonMap(mapFileName);
  }

  public static int getIntFromObject(Object obj) {
    return (int) (long) obj;
  }

  private void parseJsonMap(String mapFileName) throws IOException {
    try (FileReader reader = new FileReader(mapFileName)) {
      jsonMap = (JSONObject) jsonParser.parse(reader);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void setUpRooms(Game game) {
    List<Room> rooms = new ArrayList<>();
    JSONArray jsonRooms = (JSONArray) jsonMap.get("rooms");
    for (Object roomObj : jsonRooms) {
      JSONObject jsonRoom = (JSONObject) roomObj;
      Room room = new Room(getIntFromObject(jsonRoom.get("id")));
      JSONObject jsonWall = (JSONObject) jsonRoom.get("east");
        if (jsonWall != null) {
            room.setEast(buildWallFromJson(jsonWall));
        }
      jsonWall = (JSONObject) jsonRoom.get("west");
        if (jsonWall != null) {
            room.setWest(buildWallFromJson(jsonWall));
        }
      jsonWall = (JSONObject) jsonRoom.get("north");
        if (jsonWall != null) {
            room.setNorth(buildWallFromJson(jsonWall));
        }
      jsonWall = (JSONObject) jsonRoom.get("south");
        if (jsonWall != null) {
            room.setSouth(buildWallFromJson(jsonWall));
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
