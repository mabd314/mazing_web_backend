package com.mazing;

public class RoomConfigEntity {
    private int roomNumber;
    private boolean thereLight;
    private boolean lightOn;
    private boolean endRoom;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isThereLight() {
        return thereLight;
    }

    public void setThereLight(boolean thereLight) {
        this.thereLight = thereLight;
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
}
