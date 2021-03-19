package com.mazing;

import com.mazing.logic.game.Console;
import com.mazing.logic.game.Game;
import com.mazing.logic.game.Player;
import com.mazing.logic.wall.WallType;
import com.mazing.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController
public class GameController {

    @Autowired
    UserService userService;

    @RequestMapping(value="games/execute",method = RequestMethod.POST)
    public Response executeCommand(@RequestParam String query){
        try{
            String userName = userService.getUserName();
            PlayerEntity playerEntity=playerRepo.getOne(userName);
            Player player=new Player(playerEntity);
            Game game=player.getGame();
            if(game.isTimeOut())
                game.endGame();
            if(!game.isHasStarted())
                return new Response(ResponseType.INVALID,"Game has not started yet.");
            if(game.isHasEnded()&&game.getWinnerName().equals(""))
                return new Response(ResponseType.LOST,"Time Out, All players Lost");
            if(game.isHasEnded()&&game.getWinnerName().equals(player.getUserName()))
                return new Response(ResponseType.WON,"You won the game! You can leave it now.");
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

    @RequestMapping(value="/games/create",method = RequestMethod.POST)
    public int createGame(@RequestBody GameConfigEntity gameConfigEntity){
        GameSettingsConfigEntity gameSettings=gameConfigEntity.getGameSettings();
        GameEntity gameEntity=new GameEntity();
        gameEntity.setSecondsNeeded(gameSettings.getSecondsNeeded());
        gameEntity.save();
        int gameId=gameEntity.getGameId();
        GameSettingsEntity gameSettingsEntity=new GameSettingsEntity(gameId,gameSettings.getStartingDirection(),gameSettings.isStartingFlashLightOn(),gameSettings.getStartingCurrentRoomNumber(),gameSettings.getStartingGoldCount());
        gameSettingsRepo.save(gameSettingsEntity);

        List<RoomConfigEntity> rooms=gameConfigEntity.getRooms();
        for(RoomConfigEntity room:rooms){
            RoomEntity roomEntity=new RoomEntity(room.getRoomNumber(),gameId,0,0,0,0,room.isThereLight(),room.isLightOn(),room.isEndRoom());
            roomEntity.save();
        }

        List<WallConfigEntity> walls=gameConfigEntity.getWalls();
        for(WallConfigEntity wall:walls){
            WallEntity wallEntity=new WallEntity(wall.getLockingKeyId(),wall.getHiddenKeyId(),wall.isLocked(),wall.getRoom1Number(),wall.getRoom2Number(),gameId,wall.getWallType());
            wallEntity.save();
            int wallId=wallEntity.getWallId();
            RoomEntity roomEntity=roomRepo.getOne(new RoomId(wall.getRoomNumber(),gameId));
            switch (wall.getWallDirection()){
                case EAST -> roomEntity.setEastWallId(wallId);
                case WEST -> roomEntity.setWestWallId(wallId);
                case NORTH -> roomEntity.setNorthWallId(wallId);
                case SOUTH -> roomEntity.setSouthWallId(wallId);
            }
            roomEntity.save();
            if(wall.getWallType()== WallType.DOOR){
                int otherRoomNumber=wall.getRoomNumber()==wall.getRoom1Number()?wall.getRoom2Number():wall.getRoom1Number();
                RoomEntity otherRoomEntity=roomRepo.getOne(new RoomId(otherRoomNumber,gameId));
                switch (wall.getWallDirection()){
                    case EAST -> otherRoomEntity.setWestWallId(wallId);
                    case WEST -> otherRoomEntity.setEastWallId(wallId);
                    case NORTH -> otherRoomEntity.setSouthWallId(wallId);
                    case SOUTH -> otherRoomEntity.setNorthWallId(wallId);
                }
                otherRoomEntity.save();
            }
        }

        List<ItemConfigEntity> items=gameConfigEntity.getItems();
        for(ItemConfigEntity item:items){
            ItemEntity itemEntity=new ItemEntity(item.getPrice(),item.getKeyId(),item.getGoldCount(),item.getItemType(),0,"");
            RoomEntity roomEntity=roomRepo.getOne(new RoomId(item.getRoomNumber(),gameId));
            int wallId=0;
            switch (item.getWallDirection()){
                case WEST -> wallId=roomEntity.getWestWallId();
                case EAST -> wallId=roomEntity.getEastWallId();
                case NORTH -> wallId=roomEntity.getNorthWallId();
                case SOUTH -> wallId=roomEntity.getSouthWallId();
            }
            itemEntity.setWallId(wallId);
            itemEntity.save();
        }
        return gameId;
    }
}

