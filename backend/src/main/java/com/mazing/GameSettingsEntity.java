package com.mazing;

import com.mazing.logic.game.Direction;

import javax.persistence.*;

@Entity
public class GameSettingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameSettingId;
    @Enumerated(EnumType.STRING)
    private Direction startingDirection;
    private boolean startingFlashLightOn;
    private int startingCurrentRoomId;
    private int startingGoldCount;
    private int gameId;


    public int getGameSettingId() {
        return gameSettingId;
    }

    public void setGameSettingId(int gameSettingId) {
        this.gameSettingId = gameSettingId;
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

    public int getStartingCurrentRoomId() {
        return startingCurrentRoomId;
    }

    public void setStartingCurrentRoomId(int startingCurrentRoomId) {
        this.startingCurrentRoomId = startingCurrentRoomId;
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
