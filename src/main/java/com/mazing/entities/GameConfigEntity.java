package com.mazing.entities;

import java.util.List;

public class GameConfigEntity {
    private GameSettingsConfigEntity gameSettings;
    private List<ItemConfigEntity> items;
    private List<RoomConfigEntity> rooms;
    private List<WallConfigEntity> walls;

    public GameSettingsConfigEntity getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(GameSettingsConfigEntity gameSettings) {
        this.gameSettings = gameSettings;
    }

    public List<ItemConfigEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemConfigEntity> items) {
        this.items = items;
    }

    public List<RoomConfigEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomConfigEntity> rooms) {
        this.rooms = rooms;
    }

    public List<WallConfigEntity> getWalls() {
        return walls;
    }

    public void setWalls(List<WallConfigEntity> walls) {
        this.walls = walls;
    }

}
