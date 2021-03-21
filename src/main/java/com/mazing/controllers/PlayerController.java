package com.mazing.controllers;
import com.mazing.services.GameService;
import com.mazing.services.PlayerService;
import com.mazing.entities.GameSettingsEntity;
import com.mazing.entities.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;

import java.util.List;

@CrossOrigin()
@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @RequestMapping(value="/players/getPlayer",method=RequestMethod.POST)
    public PlayerEntity getPlayer(){
        return playerService.getActivePlayer();
    }

    @RequestMapping(value="/players/chooseGame/{gameId}", method=RequestMethod.POST)
    public PlayerEntity chooseGame(@PathVariable int gameId){
        return playerService.chooseGame(gameId);
    }

    @RequestMapping(value="/players/leaveGame", method=RequestMethod.POST)
    public PlayerEntity leaveGame(){
        return playerService.leaveGame();
    }
}