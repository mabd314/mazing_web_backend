package com.mazing.logic.wall;

import com.mazing.entities.Response;
import com.mazing.entities.ResponseType;
import com.mazing.entities.WallEntity;
import com.mazing.logic.game.Game;
import com.mazing.logic.game.Player;
import com.mazing.logic.item.Key;
import com.mazing.logic.item.NoKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Door extends Wall {
  private final Key key;
  private int[] connectingRoomsNumbers;
  private boolean isLocked;
  private int gameId;

  public static class Builder {

    int[] connectingRoomsNumbers;
    private boolean isLocked;
    private Key key;
    private int gameId;

    public Builder(int from, int to, int gameId) {
      connectingRoomsNumbers = new int[] {from, to};
      this.gameId=gameId;
      isLocked = false;
      key = NoKey.getInstance();
    }

    public Builder lockedWithKey(int keyId) {
      isLocked = true;
      this.key = new Key(keyId);
      return this;
    }

    public Builder unlockedWithKey(int keyId){
      this.key=new Key(keyId);
      return this;
    }

    public Door build() {
      return new Door(this);
    }
  }

  public Door(Builder builder) {
    connectingRoomsNumbers = builder.connectingRoomsNumbers;
    gameId=builder.gameId;
    isLocked = builder.isLocked;
    key = builder.key;
  }

  public void toggleLocking() {
    isLocked = !isLocked;
  }

  public Key getKey() {
    return key;
  }

  public int getRoomNumberAcross(Player player) {
    return connectingRoomsNumbers[0] == (player.currentRoom().getRoomNumber())
        ? connectingRoomsNumbers[1]
        : connectingRoomsNumbers[0];
  }

  public Response getWiningResponse(Game game) {
    String minutesElapsed = game.getElapsedSecondsString();
    return new Response(
        ResponseType.WON,
        "Congratulation, You won the game!\n"
            + "it took you "
            + minutesElapsed
            + " minutes to win this level\n"
            + "You can still walk around and explore however you want!\n"
            + "Have Fun!");
  }

  @Override
  public WallEntity getWallEntity() {
    WallEntity wallEntity = new WallEntity();
    wallEntity.setWallId(getWallId());
    wallEntity.setLockingKeyId(key.getKeyId());
    wallEntity.setLocked(isLocked);
    wallEntity.setRoom1Number(connectingRoomsNumbers[0]);
    wallEntity.setRoom2Number(connectingRoomsNumbers[1]);
    wallEntity.setGameId(gameId);
    wallEntity.setWallType(WallType.DOOR);
    return wallEntity;
  }

  @Override
  public WallType getType() {
    return WallType.DOOR;
  }

  @Override
  public Response getThrough(Player player) {
//    synchronized (Door.class){
      if (isLocked) {
        return new Response(ResponseType.FAILURE, "The door is locked");
      }
      if(player.getGame().isHasEnded()){
        return new Response(ResponseType.FAILURE, "Game Ended");
      }
      player.setCurrentRoomNumber(getRoomNumberAcross(player));
      if(player.getGame().getRoomFromNumber(player.getCurrentRoomNumber()).isEndRoom()){
        player.getGame().winGame(player.getUserName());
        return getWiningResponse(player.getGame());
      }
      return new Response(ResponseType.SUCCESS, "You moved through the door");
//    }
  }

  @Override
  public Response wallSpecificCheck(Player player) {
    if (!isLocked) {
      return new Response(ResponseType.UNLOCKED, "Door is unlocked");
    }
    return new Response(ResponseType.LOCKED, "Door is locked, " + key + " is needed to unlock");
  }

  @Override
  public Response toggleWithKey(Key key) {
    if (!this.key.equals(key)) {
      return new Response(ResponseType.FAILURE, "Wrong Key!");
    }
    toggleLocking();
    getWallEntity().save();
    return new Response(ResponseType.SUCCESS, "Door is " + (isLocked ? "locked" : "unlocked"));
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
    Arrays.sort(door.connectingRoomsNumbers);
    Arrays.sort(connectingRoomsNumbers);
    return Arrays.equals(connectingRoomsNumbers, door.connectingRoomsNumbers);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(connectingRoomsNumbers);
  }

}
