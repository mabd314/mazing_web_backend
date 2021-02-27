//package com.mazing.logic.map;
//
//import com.mazing.Repositories;
//import com.mazing.logic.game.Player;
//import com.mazing.logic.game.*;
//import com.mazing.logic.item.Gold;
//import com.mazing.logic.item.Item;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.mazing.logic.map.ItemBuilder.buildItemsFromEntity;
//
//public class JsonGameMap implements GameMap {
//
//  private final JSONParser jsonParser = new JSONParser();
//  private JSONObjectWrapper jsonMap;
//
//  public JsonGameMap(String mapFileName) throws IOException {
//    parseJsonMap(mapFileName);
//  }
//
//  private void parseJsonMap(String mapFileName) throws IOException {
//    try (FileReader reader = new FileReader(mapFileName)) {
//      jsonMap = new JSONObjectWrapper((JSONObject) jsonParser.parse(reader));
//    } catch (ParseException e) {
//      System.out.println(e.getMessage());
//    }
//  }
//
//  @Override
//  public void setUpRooms(Game game) {
//    List<Room> rooms = new ArrayList<>();
//    JSONArrayWrapper jsonRooms = jsonMap.getJsonArray("rooms");
//    for (JSONObjectWrapper jsonRoom : jsonRooms.getList()) {
//      Room room = new Room(jsonRoom.getInt("id"));
//      JSONObjectWrapper jsonWall = jsonRoom.getJsonObject("east");
//      room.setEast(WallBuilder.buildWallFromWallEntity(jsonWall));
//      jsonWall = jsonRoom.getJsonObject("west");
//      room.setWest(WallBuilder.buildWallFromWallEntity(jsonWall));
//      jsonWall = jsonRoom.getJsonObject("north");
//      room.setNorth(WallBuilder.buildWallFromWallEntity(jsonWall));
//      jsonWall = jsonRoom.getJsonObject("south");
//      room.setSouth(WallBuilder.buildWallFromWallEntity(jsonWall));
//      if (!jsonRoom.getBoolean("isLit")) {
//        room.toggleLight();
//      }
//      if (!jsonRoom.getBoolean("hasLight")) {
//        room.removeLight();
//      }
//      rooms.add(room);
//    }
//    game.setRooms(rooms);
//  }
//
//  @Override
//  public void setUpPlayers(Game game) {
//    Player player;
//    JSONArrayWrapper jsonPlayers=jsonMap.getJsonArray("players");
//    for(JSONObjectWrapper jsonPlayer:jsonPlayers.getList()){
//      String directionString = jsonPlayer.getString("direction");
//      Direction direction = Direction.valueOf(directionString.toUpperCase());
//      int goldCount = jsonPlayer.getInt("goldCount");
//      List<Item> items = buildItemsFromEntity(jsonPlayer.getJsonArray("items"));
//      String playerName=jsonPlayer.getString("playerName");
//      int currentRoomId= jsonPlayer.getInt("currentRoomId");
//      player = new Player(direction,playerName,game);
//      player.getGold().merge(new Gold(goldCount));
//      player.setCurrentRoomId(currentRoomId);
//      player.addItems(items);
//      game.addPlayer(player);
//      Repositories.playerRepo.save(player.getPlayerEntity());
//    }
//  }
//
//  @Override
//  public void setUpStopWatch(Game game) {
//    long secondsNeeded = jsonMap.getInt("secondsNeeded");
//    game.setStopWatch(new StopWatch(secondsNeeded));
//  }
//}
