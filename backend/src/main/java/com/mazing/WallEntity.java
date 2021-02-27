package com.mazing;

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
    private int roomId1;
    private int roomId2;
    @Enumerated(EnumType.STRING)
    private WallType wallType;

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

    public int getRoomId1() {
        return roomId1;
    }

    public void setRoomId1(int roomId1) {
        this.roomId1 = roomId1;
    }

    public int getRoomId2() {
        return roomId2;
    }

    public void setRoomId2(int roomId2) {
        this.roomId2 = roomId2;
    }

    public WallType getWallType() {
        return wallType;
    }

    public void setWallType(WallType wallType) {
        this.wallType = wallType;
    }

    public void save(){
        Repositories.wallRepo.save(this);
    }

}
