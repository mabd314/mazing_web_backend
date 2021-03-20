package com.mazing.services;

import com.mazing.entities.GameEntity;
import com.mazing.entities.GameSettingsEntity;
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
        String userName=jwt.getClaimAsString(audience+"/email");
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

    public PlayerEntity leaveGame() {
        String userName= getUserName();
        PlayerEntity playerEntity=playerRepo.getOne(userName);
        int gameId=playerEntity.getGameId();
        playerEntity.setGameId(0);
        playerEntity.setInTradeMode(false);
        playerEntity.setGoldCount(0);
        playerEntity.setDirection(null);
        playerEntity.setCurrentRoomNumber(0);
        playerEntity.setFlashLightOn(false);
        boolean isThereGame=gameRepo.findById(gameId).isPresent();
        if(!isThereGame)
            return playerRepo.save(playerEntity);
        GameEntity gameEntity=gameRepo.findById(gameId).get();
        List<PlayerEntity> playerEntities=playerRepo.findByGameId(gameId);
        if(playerEntities.size()<=1)
            gameService.deleteGameById(gameId);
        isThereGame=gameRepo.findById(gameId).isPresent();
        if(isThereGame){
            boolean leaverIsCreator=(gameEntity.getCreator().equals(userName));
            if(leaverIsCreator){
                String newCreator="";
                for(PlayerEntity pe:playerEntities){
                    if(pe.getUserName().equals(userName))
                        continue;
                    newCreator=pe.getUserName();
                    break;
                }
                gameEntity.setCreator(newCreator);
                gameEntity.save();
            }
        }
        return playerRepo.save(playerEntity);
    }
}
