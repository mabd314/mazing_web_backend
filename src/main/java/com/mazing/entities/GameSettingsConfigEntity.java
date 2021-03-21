package com.mazing.entities;

import com.mazing.logic.game.Direction;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class GameSettingsConfigEntity {
    private Direction startingDirection;
    private boolean startingFlashLightOn;
    private int startingCurrentRoomNumber;
    private int startingGoldCount;
    private int secondsNeeded;

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

    public int getSecondsNeeded() {
        return secondsNeeded;
    }

    public void setSecondsNeeded(int secondsNeeded) {
        this.secondsNeeded = secondsNeeded;
    }
}
