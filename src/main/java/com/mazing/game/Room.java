package com.mazing.game;

import com.mazing.item.FlashLight;
import com.mazing.wall.Empty;
import com.mazing.wall.Wall;
import java.util.Objects;

public class Room {
    private final int id;
    private Wall east;
    private Wall west;
    private Wall north;
    private Wall south;
    private boolean isThereLight;
    private boolean isLightOn;

    public Room(int id){
        this.id=id;
        east= Empty.getInstance();
        west=Empty.getInstance();
        north= Empty.getInstance();
        south=Empty.getInstance();
        isThereLight=true;
        isLightOn=true;
    }


    public void setEast(Wall east) {
        this.east = east;
    }

    public void setWest(Wall west) {
        this.west = west;
    }

    public void setNorth(Wall north) {
        this.north = north;
    }

    public void setSouth(Wall south) {
        this.south = south;
    }

    public void removeLight(){
        isThereLight=false;
    }

    public void toggleLight(){
        isLightOn=!isLightOn;
    }

    public boolean isLightOn() {
        return isLightOn;
    }

    public int getId() {
        return id;
    }

    public boolean isLit(Game game) {
        return isLightOn||
                (game.getCharacter().isFlashLightOn()
                        &&game.getCharacter().hasItem(FlashLight.getInstance()));
    }

    public Wall getWall (Direction direction){
        switch(direction){
            case EAST->{return east; }
            case WEST->{return west;}
            case NORTH->{return north;}
            default->{return south;}
        }
    }

    public Response look(Game game){
        if (isLit(game)) {
            return new Response(ResponseType.STATUS,
                game.getFacingWallDescription());
        }
        else{
            return new Response(ResponseType.FAILURE,
                "The room is dark, you can not see anything");
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

    public boolean checkWin(){
        return id==-1;
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
