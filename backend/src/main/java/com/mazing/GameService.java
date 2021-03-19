package com.mazing;

import org.springframework.stereotype.Service;

import java.util.List;

import static com.mazing.Repositories.*;

@Service
public class GameService {

    public void deleteGameById(int gameId){
        gameRepo.deleteById(gameId);
        gameSettingsRepo.deleteById(gameId);
        List<PlayerEntity> playerEntities = playerRepo.findByGameId(gameId);
        for(PlayerEntity playerEntity:playerEntities){
            playerEntity.setGameId(0);
            playerEntity.setCurrentRoomNumber(0);
            playerEntity.setFlashLightOn(false);
            playerEntity.setInTradeMode(false);
            playerEntity.setGoldCount(0);
            playerEntity.setDirection(null);
            itemRepo.deleteByUserName(playerEntity.getUserName());
        }
        List<WallEntity> wallEntities=wallRepo.findByGameId(gameId);
        for(WallEntity wallEntity:wallEntities){
            itemRepo.deleteByWallId(wallEntity.getWallId());
            wallRepo.delete(wallEntity);
        }
        roomRepo.deleteByGameId(gameId);
    }
}
