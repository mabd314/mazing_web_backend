package com.mazing;

import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;
import java.util.List;

@CrossOrigin()
@RestController
public class GameSettingsController {

    @RequestMapping(value="/games_settings",method = RequestMethod.GET)
    public List<GameSettingsEntity> getGamesSettings(){
        return gameSettingsRepo.findAll();
    }

    @RequestMapping(value="/games_settings/{gameId}",method = RequestMethod.GET)
    public GameSettingsEntity getGameSettings(@PathVariable int gameId){
        return gameSettingsRepo.findByGameId(gameId);
    }

    @RequestMapping(value="/games_settings",method=RequestMethod.POST)
    public GameSettingsEntity postGameSettings(@RequestBody GameSettingsEntity gameSettingsEntity){
        return gameSettingsRepo.save(gameSettingsEntity);
    }

    @RequestMapping(value="/games_settings/list",method=RequestMethod.POST)
    public List<GameSettingsEntity> postGamesSettings(@RequestBody List<GameSettingsEntity> gameSettingsEntities){
        return gameSettingsRepo.saveAll(gameSettingsEntities);
    }

    @RequestMapping(value="/games_settings/{gameId}",method = RequestMethod.DELETE)
    public void deleteGameSettings(@PathVariable int gameId){
        GameSettingsEntity gameSettingsEntity=gameSettingsRepo.findByGameId(gameId);
        gameSettingsRepo.deleteById(gameSettingsEntity.getGameSettingId());
    }
}

