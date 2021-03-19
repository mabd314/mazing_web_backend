package com.mazing.entities;

import com.mazing.Repositories;

import javax.persistence.*;

@Entity
@IdClass(RoomId.class)
public class RoomEntity {
    @Id
    private int roomNumber;
    @Id
    private int gameId;
    private int eastWallId;
    private int westWallId;
    private int northWallId;
    private int southWallId;
    private boolean isThereLight;
    private boolean lightOn;
    private boolean endRoom;

    public RoomEntity() {
    }

    public RoomEntity(int roomNumber, int gameId, int eastWallId, int westWallId, int northWallId, int southWallId, boolean isThereLight, boolean lightOn, boolean endRoom) {
        this.roomNumber = roomNumber;
        this.gameId = gameId;
        this.eastWallId = eastWallId;
        this.westWallId = westWallId;
        this.northWallId = northWallId;
        this.southWallId = southWallId;
        this.isThereLight = isThereLight;
        this.lightOn = lightOn;
        this.endRoom = endRoom;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
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
        return lightOn;
    }

    public void setLightOn(boolean lightOn) {
        this.lightOn = lightOn;
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
