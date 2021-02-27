package com.mazing.logic.game;

import com.mazing.*;
import com.mazing.logic.map.WallBuilder;
import com.mazing.logic.wall.Wall;
import com.mazing.logic.wall.WallType;

import java.util.Objects;

public class Room {
    private int id;
    private Wall east;
    private Wall west;
    private Wall north;
    private Wall south;
    private boolean isThereLight;
    private boolean isLightOn;
    private int gameId;
    private boolean endRoom;

    public Room(RoomEntity roomEntity){
        id=roomEntity.getId();
        isThereLight=roomEntity.isThereLight();
        isLightOn=roomEntity.isLightOn();
        endRoom =roomEntity.isEndRoom();
        gameId=roomEntity.getGameId();
        WallEntity empty=new WallEntity();
        empty.setWallType(WallType.EMPTY);
        WallEntity eastEntity= Repositories.wallRepo.findById(roomEntity.getEastWallId()).orElse(empty);
        east= WallBuilder.buildWallFromWallEntity(eastEntity);
        WallEntity westEntity= Repositories.wallRepo.findById(roomEntity.getWestWallId()).orElse(empty);
        west= WallBuilder.buildWallFromWallEntity(westEntity);
        WallEntity northEntity= Repositories.wallRepo.findById(roomEntity.getNorthWallId()).orElse(empty);
        north= WallBuilder.buildWallFromWallEntity(northEntity);
        WallEntity southEntity= Repositories.wallRepo.findById(roomEntity.getSouthWallId()).orElse(empty);
        south= WallBuilder.buildWallFromWallEntity(southEntity);
    }

    public RoomEntity getRoomEntity(){
        RoomEntity roomEntity=new RoomEntity();
        roomEntity.setId(id);
        roomEntity.setLightOn(isLightOn);
        roomEntity.setThereLight(isThereLight);
        roomEntity.setEndRoom(endRoom);
        roomEntity.setGameId(gameId);
        roomEntity.setEastWallId(east.getWallId());
        roomEntity.setWestWallId(west.getWallId());
        roomEntity.setSouthWallId(south.getWallId());
        roomEntity.setNorthWallId(north.getWallId());
        return roomEntity;
    }

    public int getGameId() {
        return gameId;
    }

    public int getId() {
        return id;
    }

    public boolean isEndRoom() {
        return endRoom;
    }

    public void toggleLight(){
        isLightOn=!isLightOn;
    }

    public boolean isLightOn() {
        return isLightOn;
    }

    public Wall getWallAtDirection(Direction direction){
        switch(direction){
            case EAST->{return east; }
            case WEST->{return west;}
            case NORTH->{return north;}
            default->{return south;}
        }
    }

    public Response switchLights(){
        if (isThereLight) {
            toggleLight();
            return new Response(ResponseType.SUCCESS,
                "Lights are " +
                    (isLightOn() ? "ON" : "OFF"));
        }
        return new Response(ResponseType.FAILURE,
            "There are no lights in this room");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Room";
    }

}
