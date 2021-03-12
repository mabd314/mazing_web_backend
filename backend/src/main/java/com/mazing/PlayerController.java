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

//    @RequestMapping(value="/players",method=RequestMethod.POST)
//    public PlayerEntity postPlayer(@RequestBody PlayerEntity playerEntity){
//        return playerRepo.save(playerEntity);
//    }

    @RequestMapping(value="/players",method=RequestMethod.POST)
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
        GameSettingsEntity gameSettingsEntity=gameSettingsRepo.findByGameId(gameId);
        playerEntity.setGameId(gameId);
        playerEntity.setInTradeMode(false);
        playerEntity.setGoldCount(gameSettingsEntity.getStartingGoldCount());
        playerEntity.setDirection(gameSettingsEntity.getStartingDirection());
        playerEntity.setCurrentRoomId(gameSettingsEntity.getStartingCurrentRoomId());
        playerEntity.setFlashLightOn(gameSettingsEntity.isStartingFlashLightOn());
        return playerRepo.save(playerEntity);
    }
}