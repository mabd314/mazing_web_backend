package com.mazing;

import com.mazing.logic.game.Console;
import com.mazing.logic.game.Game;
import com.mazing.logic.game.Player;
import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;
import java.util.List;

@CrossOrigin()
@RestController
public class GameController {

    @RequestMapping(value="/execute/{playerName}",method = RequestMethod.GET)
    public Response executeCommand(@PathVariable("playerName") String playerName,@RequestParam String query){
        try{
            PlayerEntity playerEntity=playerRepo.getOne(playerName);
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

    @RequestMapping(value="/start/{gameId}",method = RequestMethod.GET)
    public void startGame(@PathVariable("gameId") int gameId){
        Game game=new Game(gameRepo.findById(gameId).get());
        game.startGame();
    }

    @RequestMapping(value="/players",method = RequestMethod.GET)
    public List<PlayerEntity> getPlayers(){
        return playerRepo.findAll();
    }

//    @RequestMapping(value="/players",method=RequestMethod.POST)
//    public PlayerEntity postPlayer(@RequestBody PlayerEntity playerEntity){
//        return playerRepo.save(playerEntity);
//    }

    @RequestMapping(value="/players",method=RequestMethod.POST)
    public List<PlayerEntity> postPlayers(@RequestBody List<PlayerEntity> playerEntities){
        return playerRepo.saveAll(playerEntities);
    }

    @RequestMapping(value="/games",method = RequestMethod.GET)
    public List<GameEntity> getGames(){
        return gameRepo.findAll();
    }

//    @RequestMapping(value="/games",method=RequestMethod.POST)
//    public GameEntity postGame(@RequestBody GameEntity gameEntity){
//        return gameRepo.save(gameEntity);
//    }

    @RequestMapping(value="/games",method=RequestMethod.POST)
    public List<GameEntity> postGames(@RequestBody List<GameEntity> gameEntities){
        return gameRepo.saveAll(gameEntities);
    }


    @RequestMapping(value="/items",method = RequestMethod.GET)
    public List<ItemEntity> getItems(){
        return itemRepo.findAll();
    }

//    @RequestMapping(value="/items",method=RequestMethod.POST)
//    public ItemEntity postItem(@RequestBody ItemEntity itemEntity){
//        return itemRepo.save(itemEntity);
//    }

    @RequestMapping(value="/items",method=RequestMethod.POST)
    public List<ItemEntity> postItems(@RequestBody List<ItemEntity> itemEntities){
        return itemRepo.saveAll(itemEntities);
    }


    @RequestMapping(value="/walls",method = RequestMethod.GET)
    public List<WallEntity> getWalls(){
        return wallRepo.findAll();
    }

//    @RequestMapping(value="/walls",method=RequestMethod.POST)
//    public WallEntity postWall(@RequestBody WallEntity wallEntity){
//        return wallRepo.save(wallEntity);
//    }

    @RequestMapping(value="/walls",method=RequestMethod.POST)
    public List<WallEntity> postWalls(@RequestBody List<WallEntity> wallEntities){
        return wallRepo.saveAll(wallEntities);
    }


    @RequestMapping(value="/rooms",method = RequestMethod.GET)
    public List<RoomEntity> getRooms(){
        return roomRepo.findAll();
    }

//    @RequestMapping(value="/rooms",method=RequestMethod.POST)
//    public RoomEntity postPlayer(@RequestBody RoomEntity roomEntity){
//        return roomRepo.save(roomEntity);
//    }

    @RequestMapping(value="/rooms",method=RequestMethod.POST)
    public List<RoomEntity> postRooms(@RequestBody List<RoomEntity> roomEntities){
        return roomRepo.saveAll(roomEntities);
    }


}

