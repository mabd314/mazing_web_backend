package com.mazing;
import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;
import java.util.List;

@CrossOrigin()
@RestController
public class PlayerController {

    @RequestMapping(value="/players",method = RequestMethod.GET)
    public List<PlayerEntity> getPlayers(){
        return playerRepo.findAll();
    }

    @RequestMapping(value="/players/{userName}",method = RequestMethod.GET)
    public PlayerEntity getPlayer(@PathVariable String userName){
        return playerRepo.findById(userName).get();
    }

    @RequestMapping(value="/players",method=RequestMethod.POST)
    public PlayerEntity postPlayer(@RequestBody PlayerEntity playerEntity){
        return playerRepo.save(playerEntity);
    }

    @RequestMapping(value="/players/list",method=RequestMethod.POST)
    public List<PlayerEntity> postPlayers(@RequestBody List<PlayerEntity> playerEntities){
        return playerRepo.saveAll(playerEntities);
    }

    @RequestMapping(value="/players/{userName}",method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable String userName){
        playerRepo.deleteById(userName);
    }

    @RequestMapping(value="/players/{userName}/chooseGame/{gameId}", method=RequestMethod.GET)
    public PlayerEntity chooseGame(@PathVariable String userName,@PathVariable int gameId){
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

    @RequestMapping(value="/players/{userName}/leaveGame", method=RequestMethod.GET)
    public PlayerEntity leaveGame(@PathVariable String userName){
        PlayerEntity playerEntity=playerRepo.getOne(userName);
        playerEntity.setGameId(0);
        playerEntity.setInTradeMode(false);
        playerEntity.setGoldCount(0);
        playerEntity.setDirection(null);
        playerEntity.setCurrentRoomNumber(0);
        playerEntity.setFlashLightOn(false);
        return playerRepo.save(playerEntity);
    }
}