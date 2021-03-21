package com.mazing.entities;

import com.mazing.logic.game.Direction;

import javax.persistence.*;

@Entity
public class GameSettingsEntity {
    @Id
    private int gameId;
    @Enumerated(EnumType.STRING)
    private Direction startingDirection;
    private boolean startingFlashLightOn;
    private int startingCurrentRoomNumber;
    private int startingGoldCount;

    public GameSettingsEntity() {
    }

    public GameSettingsEntity(int gameId, Direction startingDirection, boolean startingFlashLightOn, int startingCurrentRoomNumber, int startingGoldCount) {
        this.gameId = gameId;
        this.startingDirection = startingDirection;
        this.startingFlashLightOn = startingFlashLightOn;
        this.startingCurrentRoomNumber = startingCurrentRoomNumber;
        this.startingGoldCount = startingGoldCount;
    }

    public Direction getStartingDirection() {
        return startingDirection;
    }

    public void setStartingDirection(Direction startingDirection) {
        this.startingDirection = startingDirection;
    }

    public boolean isStartingFlashLightOn() {
        return startingFlashLightOn;
    }

    public void setStartingFlashLightOn(boolean startingFlashLightOn) {
        this.startingFlashLightOn = startingFlashLightOn;
    }

    public int getStartingCurrentRoomNumber() {
        return startingCurrentRoomNumber;
    }

    public void setStartingCurrentRoomNumber(int startingCurrentRoomNumber) {
        this.startingCurrentRoomNumber = startingCurrentRoomNumber;
    }

    public int getStartingGoldCount() {
        return startingGoldCount;
    }

    public void setStartingGoldCount(int startingGoldCount) {
        this.startingGoldCount = startingGoldCount;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
