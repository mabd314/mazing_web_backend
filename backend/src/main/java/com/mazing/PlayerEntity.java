package com.mazing;

import com.mazing.logic.game.Direction;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class PlayerEntity {
    @Id
    private String userName;
    @Enumerated(EnumType.STRING)
    private Direction direction;
    private boolean isFlashLightOn;
    private int currentRoomId;
    private int goldCount;
    private int gameId;
    private boolean inTradeMode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isFlashLightOn() {
        return isFlashLightOn;
    }

    public void setFlashLightOn(boolean flashLightOn) {
        isFlashLightOn = flashLightOn;
    }

    public int getCurrentRoomId() {
        return currentRoomId;
    }

    public void setCurrentRoomId(int currentRoomId) {
        this.currentRoomId = currentRoomId;
    }

    public int getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isInTradeMode() {
        return inTradeMode;
    }

    public void setInTradeMode(boolean inTradeMode) {
        this.inTradeMode = inTradeMode;
    }

    public void save(){
        Repositories.playerRepo.save(this);
    }
}
