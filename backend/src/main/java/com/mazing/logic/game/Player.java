package com.mazing.logic.game;

import com.mazing.*;
import com.mazing.logic.item.FlashLight;
import com.mazing.logic.item.Gold;
import com.mazing.logic.item.Item;
import com.mazing.logic.item.ItemType;
import com.mazing.logic.map.ItemBuilder;
import com.mazing.logic.wall.Wall;
import com.mazing.logic.wall.WallType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class Player {
  private final String userName;
  private Direction direction;
  private boolean isFlashLightOn;
  private int currentRoomNumber;
  private final Gold gold;
  private final Game game;
  private List<Item> items;
  private int gameId;
  private boolean inTradeMode;

  public Player(PlayerEntity playerEntity){
    userName =playerEntity.getUserName();
    direction=playerEntity.getDirection();
    isFlashLightOn=playerEntity.isFlashLightOn();
    currentRoomNumber=playerEntity.getCurrentRoomNumber();
    gold=new Gold(playerEntity.getGoldCount());
    GameEntity gameEntity= Repositories.gameRepo.getOne(playerEntity.getGameId());
    game=new Game(gameEntity);
    List<ItemEntity> itemEntities=Repositories.itemRepo.findByUserName(playerEntity.getUserName());
    items= ItemBuilder.buildItemsFromEntity(itemEntities);
    gameId=playerEntity.getGameId();
    inTradeMode=playerEntity.isInTradeMode();
  }

  public PlayerEntity getPlayerEntity(){
    PlayerEntity playerEntity=new PlayerEntity();
    playerEntity.setUserName(userName);
    playerEntity.setDirection(direction);
    playerEntity.setGameId(gameId);
    playerEntity.setCurrentRoomNumber(currentRoomNumber);
    playerEntity.setFlashLightOn(isFlashLightOn);
    playerEntity.setGoldCount(gold.getGoldCount());
    playerEntity.setInTradeMode(inTradeMode);
    return playerEntity;
  }

  public void addItems(List<? extends Item> items) {
    for (Item item : items) {
      addItem(item);
    }
  }

  public void addItem(Item item) {
    if (itemIsGold(item)) {
      gold.merge((Gold) item);
    } else {
      items.add(item);
    }
  }

  public WallType facingWallType() {
    return facingWall().getType();
  }

  public List<Item> facingWallTradingList() {
    return facingWall().getTradingList();
  }

  public Wall facingWall() {
    return currentRoom().getWallAtDirection(getDirection());
  }

  public Response throughWallAtDirection(Direction direction) {
    Wall wall = currentRoom().getWallAtDirection(direction);
    return wall.getThrough(this);
  }

  public Room currentRoom() {
    return game.getRoomFromNumber(currentRoomNumber);
  }

  public void setCurrentRoomNumber(int currentRoomNumber) {
    this.currentRoomNumber = currentRoomNumber;
  }

  public boolean currentRoomIsLit() {
    return currentRoom().isLightOn()
            || (isFlashLightOn() && hasItem(FlashLight.getInstance()));
  }

  private boolean itemIsGold(Item item) {
    return item.getType() == ItemType.GOLD;
  }

  public boolean hasItem(Item item) {
    return items.contains(item);
  }

  public boolean removeItem(Item item) {
    return items.remove(item);
  }

  @Transient
  public List<Item> getItems() {
    return items;
  }

  public Direction getDirection() {
    return direction;
  }

  @Transient
  public Gold getGold() {
    return gold;
  }

  public boolean isFlashLightOn() {
    return isFlashLightOn;
  }

  public void setFlashLightOn(boolean flashLightOn) {
    isFlashLightOn = flashLightOn;
  }


  public String getUserName() {
    return userName;
  }

  public int getCurrentRoomNumber() {
    return currentRoomNumber;
  }

  @Transient
  public Game getGame() {
    return game;
  }

  public void turnLeft() {
    direction = direction.leftDirection();
  }

  public void turnRight() {
    direction = direction.rightDirection();
  }

  public Direction oppositeDirection() {
    return direction.oppositeDirection();
  }

  public int getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

  public boolean isInTradeMode() {
    return inTradeMode;
  }

  public void setInTradeMode(boolean inTradeMode) {
    this.inTradeMode = inTradeMode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return userName.equals(player.userName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName);
  }
}
