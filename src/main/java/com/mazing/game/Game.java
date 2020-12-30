package com.mazing.game;

import com.mazing.item.FlashLight;
import com.mazing.item.Gold;
import com.mazing.item.Item;
import com.mazing.wall.Wall;
import com.mazing.wall.WallType;
import java.util.List;

public class Game {

  private List<Room> rooms;
  private int currentRoomId;
  private Character character;
  private StopWatch stopWatch;
  private boolean isWon;

  public WallType getFacingWallType() {
    return getFacingWall().getType();
  }

  public List<Item> getFacingWallTradingList() {
    return getFacingWall().getTradingList();
  }

  public Wall getFacingWall() {
    return getCurrentRoom().getWallAtDirection(getCharacter().getDirection());
  }

  public Response getThroughWallAtDirection(Direction direction) {
    Wall wall = getCurrentRoom().getWallAtDirection(direction);
    return wall.getThrough(this);
  }

  public Room getCurrentRoom() {
    return getRoomFromId(currentRoomId);
  }

  private Room getRoomFromId(int id) {
    for (Room room : rooms) {
      if (room.getId() == id) {
        return room;
      }
    }
    throw new IllegalArgumentException("No room with such id");
  }

  public void setCurrentRoomId(int currentRoomId) {
    this.currentRoomId = currentRoomId;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

  public boolean isCurrentRoomLit() {
    return getCurrentRoom().isLightOn()
        || (character.isFlashLightOn() && character.hasItem(FlashLight.getInstance()));
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public Gold getGold() {
    return getCharacter().getGold();
  }

  public List<Item> getCharacterItems() {
    return character.getItems();
  }

  public Direction getDirection() {
    return character.getDirection();
  }

  public Direction getOppositeDirection() {
    return character.getOppositeDirection();
  }

  public boolean isWon() {
    return isWon;
  }

  public void setWon(boolean won) {
    isWon = won;
  }

  public StopWatch getStopWatch() {
    return stopWatch;
  }

  public void setStopWatch(StopWatch stopWatch) {
    this.stopWatch = stopWatch;
  }

  public String getElapsedSecondsString() {
    return stopWatch.getElapsedSecondsString();
  }

  public String getRemainingSecondsString() {
    return stopWatch.getRemainingSecondsString();
  }
}
