package com.mazing.entities;

import com.mazing.Repositories;
import com.mazing.logic.wall.WallType;

import javax.persistence.*;

@Entity
public class WallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wallId;
    private int lockingKeyId;
    private int hiddenKeyId;
    private boolean isLocked;
    private int room1Number;
    private int room2Number;
    private int gameId;
    @Enumerated(EnumType.STRING)
    private WallType wallType;

    public WallEntity() {
    }

    public WallEntity(int lockingKeyId, int hiddenKeyId, boolean isLocked, int room1Number, int room2Number, int gameId, WallType wallType) {
        this.lockingKeyId = lockingKeyId;
        this.hiddenKeyId = hiddenKeyId;
        this.isLocked = isLocked;
        this.room1Number = room1Number;
        this.room2Number = room2Number;
        this.gameId = gameId;
        this.wallType = wallType;
    }

    public int getWallId() {
        return wallId;
    }

    public void setWallId(int wallId) {
        this.wallId = wallId;
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

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public WallType getWallType() {
        return wallType;
    }

    public void setWallType(WallType wallType) {
        this.wallType = wallType;
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }



    public void save(){
        Repositories.wallRepo.save(this);
    }



}
