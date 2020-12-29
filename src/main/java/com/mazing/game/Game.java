package com.mazing.game;

import com.mazing.item.Gold;
import com.mazing.item.Item;
import com.mazing.wall.Wall;
import com.mazing.wall.WallType;
import java.util.List;

public class Game {

  private Character character;
  private int currentRoomId;
  private StopWatch stopWatch;
  private List<Room> rooms;
  private boolean isWon;

  public Room getRoomFromId(int id) {
    for (Room room : rooms) {
      if (room.getId() == id) {
        return room;
      }
    }
    throw new IllegalArgumentException("No room with such id");
  }

  public boolean isWon() {
    return isWon;
  }

  public void setWon(boolean won) {
    isWon = won;
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public String getFacingWallDescription() {
    return getFacingWall().getDescription();
  }

  public List<Item> getCharacterItems() {
    return character.getItems();
  }

  public Room getCurrentRoom() {
    return getRoomFromId(currentRoomId);
  }

  public Direction getDirection() {
    return character.getDirection();
  }

  public Direction getOppositeDirection() {
    return character.getOppositeDirection();
  }

  public List<Item> getFacingWallTradingList() {
    return getFacingWall().getTradingList();
  }

  public Gold getGold() {
    return getCharacter().getGold();
  }

  public WallType getFacingWallType() {
    return getFacingWall().getType();
  }

  public void setCurrentRoomId(int currentRoomId) {
    this.currentRoomId = currentRoomId;
  }

  public StopWatch getStopWatch() {
    return stopWatch;
  }

  public void setStopWatch(StopWatch stopWatch) {
    this.stopWatch = stopWatch;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

  public String getElapsedSecondsString(){
    return stopWatch.getElapsedSecondsString();
  }
  public String getRemainingSecondsString(){
    return stopWatch.getRemainingSecondsString();
  }
  public void start() {
    GameController.initializeGame(this);
    executeMainCommand();
  }

  public Wall getFacingWall() {
    return getCurrentRoom().getWall(getCharacter().getDirection());
  }

  public Response getThroughWallAtDirection(Direction direction) {
    Wall wall = getCurrentRoom().getWall(direction);
    return wall.getThrough(this);
  }

  public void executeMainCommand() {
    GameController.executeMainCommand(this);
  }

  public void executeTradingCommand() {
    GameController.executeTradingCommand(this);
  }
}
