package com.mazing.controllers;

import com.mazing.entities.*;
import com.mazing.services.GameService;
import com.mazing.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;

import java.util.List;

@CrossOrigin()
@RestController
public class GameController {

    @Autowired
    GameService gameService;

    @RequestMapping(value="games/execute",method = RequestMethod.POST)
    public Response executeCommand(@RequestParam String query){
        return gameService.executeCommand(query);
    }

    @RequestMapping(value="games/{gameId}/start",method = RequestMethod.GET)
    public void startGame(@PathVariable("gameId") int gameId){
        gameService.startGame(gameId);
    }

    @RequestMapping(value="games/{gameId}/playersNames",method = RequestMethod.GET)
    public List<PlayerName> getPlayerNames(@PathVariable int gameId){
        return gameService.getPlayerNames(gameId);
    }

    @RequestMapping(value="/games",method = RequestMethod.GET)
    public List<GameEntity> getGames(){
        return gameRepo.findAll();
    }

    @RequestMapping(value="/games/create",method = RequestMethod.POST)
    public int createGame(@RequestBody GameConfigEntity gameConfigEntity){
        return gameService.createGame(gameConfigEntity);
    }

    @RequestMapping(value="/walls",method = RequestMethod.GET)
    public List<WallEntity> getWalls(){
        return wallRepo.findAll();
    }
}

