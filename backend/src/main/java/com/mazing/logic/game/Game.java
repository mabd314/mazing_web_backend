package com.mazing.logic.game;

import com.mazing.*;

import java.util.Objects;

public class Game {
  private boolean hasEnded;
  private boolean hasStarted;
  private long startTime;
  private int gameId;
  private int secondsNeeded;
  private String winnerName;

  public Game(){}
  public Game(GameEntity gameEntity){
    gameId=gameEntity.getGameId();
    hasEnded =gameEntity.isHasEnded();
    hasStarted=gameEntity.isHasStarted();
    startTime=gameEntity.getStartTime();
    secondsNeeded=gameEntity.getSecondsNeeded();
    winnerName=gameEntity.getWinnerName();
  }

  public int getGameId() {
    return gameId;
  }

  public GameEntity getGameEntity(){
    GameEntity gameEntity=new GameEntity();
    gameEntity.setHasEnded(hasEnded);
    gameEntity.setGameId(gameId);
    gameEntity.setHasStarted(hasStarted);
    gameEntity.setStartTime(startTime);
    gameEntity.setSecondsNeeded(secondsNeeded);
    gameEntity.setWinnerName(winnerName);
    return gameEntity;
  }

  public boolean isTimeOut(){
    System.out.println((System.currentTimeMillis()-startTime)/1000);
    return (((System.currentTimeMillis()-startTime)/1000)>secondsNeeded);
  }

  public void startGame(){
    hasStarted=true;
    hasEnded=false;
    startTime=System.currentTimeMillis();
    getGameEntity().save();
  }

  public void winGame(String userName){
    hasEnded=true;
    winnerName=userName;
    getGameEntity().save();
  }

  public void endGame(){
    hasEnded=true;
    getGameEntity().save();
  }

  public Room getRoomFromNumber(int roomNumber) {
    return new Room(Repositories.roomRepo.getOne(new RoomId(roomNumber,gameId)));
  }

  public boolean isHasStarted() {
    return hasStarted;
  }

  public long getStartTime() {
    return startTime;
  }

  public int getSecondsNeeded() {
    return secondsNeeded;
  }

  public String getWinnerName() {
    return winnerName;
  }

  public boolean isHasEnded() {
    return hasEnded;
  }

  public void setHasEnded(boolean hasEnded) {
    this.hasEnded = hasEnded;
  }

    public String getElapsedSecondsString() {
    return getTimeString(getElapsedSeconds());
  }

  public String getRemainingSecondsString() {
    return getTimeString(getRemainingSeconds());
  }

  private String getTimeString(long seconds) {
    return seconds / 60 + ":" + seconds % 60;
  }

  private long getElapsedSeconds() {
    long now = System.currentTimeMillis();
    return (now - startTime) / 1000;
  }

  private long getRemainingSeconds() {
    return secondsNeeded - getElapsedSeconds();
  }

  public Response checkTime() {
    return new Response(ResponseType.STATUS,"Elapsed: "
        + getElapsedSecondsString()
        + ", Remaining: "
        + getRemainingSecondsString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Game game = (Game) o;
    return gameId == game.gameId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(gameId);
  }
}
