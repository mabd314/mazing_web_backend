package com.mazing.wall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.mazing.game.*;
import com.mazing.item.*;

public class Door extends Wall {

  public static List<Door> doors = new ArrayList<>();
  private final Key key;
  int[] connectingRoomsId;
  private boolean isLocked;

  public Door(Builder builder) {
    connectingRoomsId = builder.connectingRoomsId;
    isLocked = builder.isLocked;
    key = builder.key;
  }

  public static void clear() {
    doors.clear();
  }

  public void toggleLocking() {
    isLocked = !isLocked;
  }

  public Key getKey() {
    return key;
  }

  public int getRoomIdAcross(Game game) {
    return connectingRoomsId[0] == (game.getCurrentRoom().getId()) ? connectingRoomsId[1]
        : connectingRoomsId[0];
  }

  public Response getWiningResponse(Game game){
    String minutesElapsed = game.getElapsedSecondsString();
    game.getStopWatch().stopTimer();
    return new Response(ResponseType.WON, "Congratulation, You won the game!\n" +
        "it took you " + minutesElapsed + " minutes to win this level\n" +
        "You can still walk around and explore however you want!\n" +
        "Have Fun!");
  }

  @Override
  public WallType getType() {
    return WallType.DOOR;
  }

  @Override
  public Response getThrough(Game game) {
    if (isLocked) {
        return new Response(ResponseType.FAILURE,
            "The door is locked");
    }
    game.setCurrentRoomId(getRoomIdAcross(game));
    if (game.getCurrentRoom().checkWin()) {
      return getWiningResponse(game);
    }
    return new Response(ResponseType.SUCCESS,
        "You moved through the door");
  }

  @Override
  public Response wallSpecificCheck(Game game) {
    if (!isLocked) {
          return new Response(ResponseType.UNLOCKED,
              "Door is unlocked");
      }
    return new Response(ResponseType.LOCKED,
        "Door is locked, " + key + " is needed to unlock");
  }

  @Override
  public Response toggleWithKey(Key key) {
      if (!this.key.equals(key)) {
          return new Response(ResponseType.FAILURE,
              "Wrong Key!");
      }
    toggleLocking();
    return new Response(ResponseType.SUCCESS,
        "Door is " +
            (isLocked ? "locked" : "unlocked"));
  }

  @Override
  public String toString() {
    return "Door";
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Door door = (Door) o;
    Arrays.sort(door.connectingRoomsId);
    Arrays.sort(connectingRoomsId);
    return Arrays.equals(connectingRoomsId, door.connectingRoomsId);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(connectingRoomsId);
  }

  public static class Builder {

    int[] connectingRoomsId;
    private boolean isLocked;
    private Key key;

    public Builder(int from, int to) {
      connectingRoomsId = new int[]{from, to};
      isLocked = false;
      key = NoKey.getInstance();
    }

    public Builder lockedWithKey(int keyId) {
      isLocked = true;
      this.key = new Key(keyId);
      return this;
    }

    public Door build() {
      Door newDoor = new Door(this);
      int index = doors.indexOf(newDoor);
        if (index != -1) {
            return doors.get(index);
        }
      doors.add(newDoor);
      return newDoor;
    }
  }
}
