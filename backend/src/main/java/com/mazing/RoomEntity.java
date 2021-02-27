package com.mazing;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoomEntity {
    @Id
    private int id;
    private int eastWallId;
    private int westWallId;
    private int northWallId;
    private int southWallId;
    private boolean isThereLight;
    private boolean isLightOn;
    private int gameId;
    private boolean endRoom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEastWallId() {
        return eastWallId;
    }

    public void setEastWallId(int eastWallId) {
        this.eastWallId = eastWallId;
    }

    public int getWestWallId() {
        return westWallId;
    }

    public void setWestWallId(int westWallId) {
        this.westWallId = westWallId;
    }

    public int getNorthWallId() {
        return northWallId;
    }

    public void setNorthWallId(int northWallId) {
        this.northWallId = northWallId;
    }

    public int getSouthWallId() {
        return southWallId;
    }

    public void setSouthWallId(int southWallId) {
        this.southWallId = southWallId;
    }

    public boolean isThereLight() {
        return isThereLight;
    }

    public void setThereLight(boolean thereLight) {
        isThereLight = thereLight;
    }

    public boolean isLightOn() {
        return isLightOn;
    }

    public void setLightOn(boolean lightOn) {
        isLightOn = lightOn;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isEndRoom() {
        return endRoom;
    }

    public void setEndRoom(boolean endRoom) {
        this.endRoom = endRoom;
    }

    public void save(){
        Repositories.roomRepo.save(this);
    }

}
