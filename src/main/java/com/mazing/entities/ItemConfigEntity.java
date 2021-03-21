package com.mazing.entities;

import com.mazing.logic.game.Direction;
import com.mazing.logic.item.ItemType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ItemConfigEntity {
    private ItemType itemType;
    private Direction wallDirection;
    private int roomNumber;
    private int goldCount;
    private int keyId;
    private int price;

    public ItemType getItemType() {
        return itemType;
    }


    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Direction getWallDirection() {
        return wallDirection;
    }

    public void setWallDirection(Direction wallDirection) {
        this.wallDirection = wallDirection;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
