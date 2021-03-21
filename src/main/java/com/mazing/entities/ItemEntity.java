package com.mazing.entities;

import com.mazing.Repositories;
import com.mazing.logic.item.ItemType;

import javax.persistence.*;

@Entity
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private int price;
    private int keyId;
    private int goldCount;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
    private int wallId;
    private String userName;

    public ItemEntity() {
    }

    public ItemEntity(int price, int keyId, int goldCount, ItemType itemType, int wallId, String userName) {
        this.price = price;
        this.keyId = keyId;
        this.goldCount = goldCount;
        this.itemType = itemType;
        this.wallId = wallId;
        this.userName = userName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public int getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getWallId() {
        return wallId;
    }

    public void setWallId(int wallId) {
        this.wallId = wallId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void save(){
        Repositories.itemRepo.save(this);
    }

}
