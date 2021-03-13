package com.mazing;

import com.mazing.logic.game.Console;
import com.mazing.logic.game.Game;
import com.mazing.logic.game.Player;
import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController
public class GameController {

    @RequestMapping(value="games/execute/{userName}",method = RequestMethod.GET)
    public Response executeCommand(@PathVariable("userName") String userName,@RequestParam String query){
        try{
            PlayerEntity playerEntity=playerRepo.getOne(userName);
            Player player=new Player(playerEntity);
            Game game=player.getGame();
            if(game.isTimeOut())
                game.endGame();
            if(!game.isHasStarted())
                return new Response(ResponseType.INVALID,"Game has not started yet.");
            if(game.isHasEnded()&&game.getWinnerName().equals(""))
                return new Response(ResponseType.LOST,"Time Out, All players Lost");
            if(game.isHasEnded())
                return new Response(ResponseType.LOST,"Player "+game.getWinnerName()+" has won the game");
            if(playerEntity.isInTradeMode())
                return Console.executeTradingCommand(player,query);
            else
                return Console.executeMainCommand(player,query);
        }catch (Exception exception) {
            return new Response(ResponseType.FAILURE, exception.getMessage());
        }
    }

    @RequestMapping(value="games/{gameId}/start",method = RequestMethod.GET)
    public void startGame(@PathVariable("gameId") int gameId){
        Game game=new Game(gameRepo.findById(gameId).get());
        game.startGame();
    }

    @RequestMapping(value="games/{gameId}/playersNames",method = RequestMethod.GET)
    public List<PlayerName> getPlayerNames(@PathVariable int gameId){
        List<PlayerEntity> playerEntities = playerRepo.findByGameId(gameId);
        List<PlayerName> playerNames=new ArrayList<>();
        for(PlayerEntity playerEntity: playerEntities){
            playerNames.add(new PlayerName(playerEntity.getUserName()));
        }
        return playerNames;
    }

    @RequestMapping(value="/games",method = RequestMethod.GET)
    public List<GameEntity> getGames(){
        return gameRepo.findAll();
    }

    @RequestMapping(value="/games/{gameId}",method = RequestMethod.GET)
    public GameEntity getGame(@PathVariable int gameId){
        return gameRepo.findById(gameId).get();
    }

    @RequestMapping(value="/games/",method=RequestMethod.POST)
    public GameEntity postGame(@RequestBody GameEntity gameEntity){
        return gameRepo.save(gameEntity);
    }

    @RequestMapping(value="/games/list",method=RequestMethod.POST)
    public List<GameEntity> postGames(@RequestBody List<GameEntity> gameEntities){
        return gameRepo.saveAll(gameEntities);
    }

    @RequestMapping(value="/games/{gameId}",method = RequestMethod.DELETE)
    public void deleteGame(@PathVariable int gameId){
        gameRepo.deleteById(gameId);
    }
}

