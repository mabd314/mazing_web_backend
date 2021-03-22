package com.mazing.services;

import com.mazing.entities.GameEntity;
import com.mazing.entities.GameSettingsEntity;
import com.mazing.entities.ItemEntity;
import com.mazing.entities.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mazing.Repositories.*;

@Service
public class PlayerService {

    @Autowired GameService gameService;

    @Value("${auth0.audience}")
    private String audience;

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userName=jwt.getClaimAsString(audience+"/username");
        System.out.println(jwt.getClaims());
        System.out.println(jwt.getAudience());
        System.out.println(userName);
        return userName;
    }

    public PlayerEntity getActivePlayer() {
        String userName= getUserName();
        if(playerRepo.findById(userName).isPresent())
            return playerRepo.findById(userName).get();
        PlayerEntity playerEntity= new PlayerEntity();
        playerEntity.setUserName(userName);
        return playerRepo.save(playerEntity);
    }

    public PlayerEntity chooseGame(int gameId) {
        String userName= getUserName();
        PlayerEntity playerEntity=playerRepo.getOne(userName);
        GameSettingsEntity gameSettingsEntity=gameSettingsRepo.getOne(gameId);
        playerEntity.setGameId(gameId);
        playerEntity.setInTradeMode(false);
        playerEntity.setGoldCount(gameSettingsEntity.getStartingGoldCount());
        playerEntity.setDirection(gameSettingsEntity.getStartingDirection());
        playerEntity.setCurrentRoomNumber(gameSettingsEntity.getStartingCurrentRoomNumber());
        playerEntity.setFlashLightOn(gameSettingsEntity.isStartingFlashLightOn());
        return playerRepo.save(playerEntity);
    }

    public synchronized PlayerEntity leaveGame() {
        String userName= getUserName();
        PlayerEntity playerEntity=playerRepo.getOne(userName);
        int gameId=playerEntity.getGameId();
        if(isPlayerLastPlayer(gameId))
            gameService.deleteGameById(gameId);
        else if(isPlayerCreator(gameId,userName))
            assignAnotherCreator(gameId,userName);
        playerEntity.setGameId(0);
        playerEntity.setInTradeMode(false);
        playerEntity.setGoldCount(0);
        playerEntity.setDirection(null);
        playerEntity.setCurrentRoomNumber(0);
        playerEntity.setFlashLightOn(false);
        itemRepo.deleteByUserName(userName); //edit to drop or divide.
        return playerRepo.save(playerEntity);
    }

    private boolean isPlayerLastPlayer(int gameId){
        return playerRepo.findByGameId(gameId).size()==1;
    }

    private boolean isPlayerCreator(int gameId,String userName){
        return gameRepo.findById(gameId).get().getCreator().equals(userName);
    }

    private void assignAnotherCreator(int gameId,String userName){
        GameEntity gameEntity=gameRepo.findById(gameId).get();
        List<PlayerEntity> playerEntities=playerRepo.findByGameId(gameId);
        String newCreator="";
        for(PlayerEntity playerEntity:playerEntities) {
            if (playerEntity.getUserName().equals(userName))
                continue;
            newCreator = playerEntity.getUserName();
            break;
        }
        gameEntity.setCreator(newCreator);
        gameEntity.save();
    }
}
