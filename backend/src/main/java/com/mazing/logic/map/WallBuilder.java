package com.mazing.logic.map;

import com.mazing.entities.ItemEntity;
import com.mazing.Repositories;
import com.mazing.entities.WallEntity;
import com.mazing.logic.item.Item;
import com.mazing.logic.item.Key;
import com.mazing.logic.item.NoKey;
import com.mazing.logic.wall.*;

import java.util.List;

public class WallBuilder {


  public static Wall buildWallFromWallEntity(WallEntity wallEntity) {
    switch (wallEntity.getWallType()) {
      case DOOR -> {
        Wall wall=buildDoorFromEntity(wallEntity);
        wall.setWallId(wallEntity.getWallId());
        return wall;
      }
      case SELLER -> {
        Wall wall=buildSellerFromEntity(wallEntity);
        wall.setWallId(wallEntity.getWallId());
        return wall;
      }
      case CHEST -> {
        Wall wall=buildChestFromEntity(wallEntity);
        wall.setWallId(wallEntity.getWallId());
        return wall;
      }
      case PAINTING -> {
        Wall wall= buildPaintingFromEntity(wallEntity);
        wall.setWallId(wallEntity.getWallId());
        return wall;
      }
      case MIRROR -> {
        Wall wall= buildMirrorFromEntity(wallEntity);
        wall.setWallId(wallEntity.getWallId());
        return wall;
      }
      default -> {
        return Empty.getInstance();
      }
    }
  }

  private static Wall buildDoorFromEntity(WallEntity wallEntity) {
    if (wallEntity.isLocked()) {
      return new Door.Builder(wallEntity.getRoom1Number(), wallEntity.getRoom2Number(),wallEntity.getGameId())
          .lockedWithKey(wallEntity.getLockingKeyId())
          .build();
    }
    return new Door.Builder(wallEntity.getRoom1Number(), wallEntity.getRoom2Number(),wallEntity.getGameId())
        .build();
  }

  private static Wall buildSellerFromEntity(WallEntity wallEntity) {
    List<ItemEntity> itemEntities=Repositories.itemRepo.findByWallId(wallEntity.getWallId());
    List<Item> items = ItemBuilder.buildItemsFromEntity(itemEntities);
    return new Seller(items);
  }

  private static Wall buildChestFromEntity(WallEntity wallEntity) {
    List<ItemEntity> itemEntities=Repositories.itemRepo.findByWallId(wallEntity.getWallId());
    List<Item> items = ItemBuilder.buildItemsFromEntity(itemEntities);
    if (wallEntity.isLocked()) {
      return new Chest.Builder(items)
          .lockedWithKey(wallEntity.getLockingKeyId())
          .build();
    }

    return new Chest.Builder(items)
        .build();
  }

  private static Wall buildPaintingFromEntity(WallEntity wallEntity) {
    if (wallEntity.getHiddenKeyId() == 0) {
      return new Painting(NoKey.getInstance());
    }
    Key key = new Key(wallEntity.getHiddenKeyId());
    return new Painting(key);
  }

  private static Wall buildMirrorFromEntity(WallEntity wallEntity) {
    if (wallEntity.getHiddenKeyId() == 0) {
      return new Mirror(NoKey.getInstance());
    }
    Key key = new Key(wallEntity.getHiddenKeyId());
    return new Mirror(key);
  }


}
