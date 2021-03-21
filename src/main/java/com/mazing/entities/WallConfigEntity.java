package com.mazing.entities;

import com.mazing.logic.game.Direction;
import com.mazing.logic.wall.WallType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class WallConfigEntity {
    private WallType wallType;
    private boolean locked;
    private int lockingKeyId;
    private int hiddenKeyId;
    private int room1Number;
    private int room2Number;
    private int roomNumber;
    private Direction wallDirection;
    private int gameId;

    public WallType getWallType() {
        return wallType;
    }

    public void setWallType(WallType wallType) {
        this.wallType = wallType;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getLockingKeyId() {
        return lockingKeyId;
    }

    public void setLockingKeyId(int lockingKeyId) {
        this.lockingKeyId = lockingKeyId;
    }

    public int getHiddenKeyId() {
        return hiddenKeyId;
    }

    public void setHiddenKeyId(int hiddenKeyId) {
        this.hiddenKeyId = hiddenKeyId;
    }

    public int getRoom1Number() {
        return room1Number;
    }

    public void setRoom1Number(int room1Number) {
        this.room1Number = room1Number;
    }

    public int getRoom2Number() {
        return room2Number;
    }

    public void setRoom2Number(int room2Number) {
        this.room2Number = room2Number;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Direction getWallDirection() {
        return wallDirection;
    }

    public void setWallDirection(Direction wallDirection) {
        this.wallDirection = wallDirection;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
