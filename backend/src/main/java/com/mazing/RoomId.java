package com.mazing;

import com.mazing.logic.game.Direction;

import java.io.Serializable;
import java.util.Objects;

public class RoomId implements Serializable {
    private int roomNumber;

    private int gameId ;

    public RoomId(){
        roomNumber=0;
        gameId=0;
    }

    public RoomId(int roomNumber, int gameId) {
        this.roomNumber=roomNumber;
        this.gameId=gameId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getGameId() {
        return gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomId roomId = (RoomId) o;
        return roomNumber == roomId.roomNumber && gameId == roomId.gameId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, gameId);
    }
}
