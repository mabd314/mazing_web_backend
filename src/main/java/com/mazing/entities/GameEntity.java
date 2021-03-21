package com.mazing.entities;

import com.mazing.Repositories;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;
    private boolean hasEnded;
    private boolean hasStarted;
    private long startTime;
    private int secondsNeeded;
    private String winnerName;
    private String creator;

    public GameEntity() {
    }

    public GameEntity(boolean hasEnded, boolean hasStarted, long startTime, int secondsNeeded, String winnerName,String creator) {
        this.hasEnded = hasEnded;
        this.hasStarted = hasStarted;
        this.startTime = startTime;
        this.secondsNeeded = secondsNeeded;
        this.winnerName = winnerName;
        this.creator=creator;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public void save(){
        Repositories.gameRepo.save(this);
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getSecondsNeeded() {
        return secondsNeeded;
    }

    public void setSecondsNeeded(int secondsNeeded) {
        this.secondsNeeded = secondsNeeded;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
