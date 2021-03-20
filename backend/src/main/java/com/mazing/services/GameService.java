package com.mazing.services;

import com.mazing.entities.*;
import com.mazing.logic.game.Console;
import com.mazing.logic.game.Game;
import com.mazing.logic.game.Player;
import com.mazing.logic.wall.WallType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mazing.Repositories.*;

@Service
public class GameService {

    @Autowired
    PlayerService playerService;

    public void deleteGameById(int gameId){
        gameRepo.deleteById(gameId);
        gameSettingsRepo.deleteById(gameId);
        List<PlayerEntity> playerEntities = playerRepo.findByGameId(gameId);
        for(PlayerEntity playerEntity:playerEntities){
            playerEntity.setGameId(0);
            playerEntity.setCurrentRoomNumber(0);
            playerEntity.setFlashLightOn(false);
            playerEntity.setInTradeMode(false);
            playerEntity.setGoldCount(0);
            playerEntity.setDirection(null);
            itemRepo.deleteByUserName(playerEntity.getUserName());
        }
        List<WallEntity> wallEntities=wallRepo.findByGameId(gameId);
        for(WallEntity wallEntity:wallEntities){
            itemRepo.deleteByWallId(wallEntity.getWallId());
            wallRepo.delete(wallEntity);
        }
        roomRepo.deleteByGameId(gameId);
    }

    public Response executeCommand(String commandQuery){
//        try{
            String userName = playerService.getUserName();
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
                return Console.executeTradingCommand(player,commandQuery);
            else
                return Console.executeMainCommand(player,commandQuery);
//        }catch (Exception exception) {
//            System.out.println(exception);
//            return new Response(ResponseType.FAILURE, exception.getMessage());
//        }
    }

    public boolean startGame(int gameId) {
        String userName=playerService.getUserName();
        Game game=new Game(gameRepo.findById(gameId).get());
        if(!game.getCreator().equals(userName))
            return false;
        game.startGame();
        return true;
    }

    public List<PlayerName> getPlayerNames(int gameId) {
        List<PlayerEntity> playerEntities = playerRepo.findByGameId(gameId);
        List<PlayerName> playerNames=new ArrayList<>();
        for(PlayerEntity playerEntity: playerEntities){
            playerNames.add(new PlayerName(playerEntity.getUserName()));
        }
        return playerNames;
    }

    public int createGame(GameConfigEntity gameConfigEntity) {
        String userName=playerService.getUserName();
        GameSettingsConfigEntity gameSettings=gameConfigEntity.getGameSettings();
        GameEntity gameEntity=new GameEntity();
        gameEntity.setSecondsNeeded(gameSettings.getSecondsNeeded());
        gameEntity.setWinnerName("");
        gameEntity.setCreator(userName);
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
