package com.mazing;
import com.mazing.logic.game.Player;
import com.mazing.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin()
@RestController
public class PlayerController {

    @Autowired UserService userService;
    @Autowired GameService gameService;

    @RequestMapping(value="/players",method = RequestMethod.GET)
    public List<PlayerEntity> getPlayers(){
        return playerRepo.findAll();
    }

//    @RequestMapping(value="/players/{userName}",method = RequestMethod.GET)
//    public PlayerEntity getPlayer(@PathVariable String userName){
//        return playerRepo.findById(userName).get();
//    }

    @RequestMapping(value="/players/getPlayer",method=RequestMethod.POST)
    public PlayerEntity getPlayer(){
        String userName= userService.getUserName();
        if(playerRepo.findById(userName).isPresent())
            return playerRepo.findById(userName).get();
        PlayerEntity playerEntity= new PlayerEntity();
        playerEntity.setUserName(userName);
        return playerRepo.save(playerEntity);
    }

//    @RequestMapping(value="/players/list",method=RequestMethod.POST)
//    public List<PlayerEntity> postPlayers(@RequestBody List<PlayerEntity> playerEntities){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        System.out.println(currentPrincipalName);
//        System.out.println(authentication.getCredentials());
//        System.out.println(authentication.getDetails());
//        System.out.println(authentication.getPrincipal());
//        Jwt jwt = (Jwt) authentication.getPrincipal();
//        System.out.println(jwt.getClaims());
//        System.out.println(jwt.getClaimAsString("https://SpringTest.com/email"));
//        return playerRepo.saveAll(playerEntities);
//    }

//    @RequestMapping(value="/players/{userName}",method = RequestMethod.DELETE)
//    public void deletePlayer(@PathVariable String userName){
//        playerRepo.deleteById(userName);
//    }

    @RequestMapping(value="/players/chooseGame/{gameId}", method=RequestMethod.POST)
    public PlayerEntity chooseGame(@PathVariable int gameId){
        String userName= userService.getUserName();
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

    @RequestMapping(value="/players/leaveGame", method=RequestMethod.POST)
    public PlayerEntity leaveGame(){
        String userName= userService.getUserName();
        PlayerEntity playerEntity=playerRepo.getOne(userName);
        List<PlayerEntity> playerEntities=playerRepo.findByGameId(playerEntity.getGameId());
        if(playerEntities.size()<=1 && gameRepo.findById(playerEntity.getGameId()).isPresent())
            gameService.deleteGameById(playerEntity.getGameId());
        playerEntity.setGameId(0);
        playerEntity.setInTradeMode(false);
        playerEntity.setGoldCount(0);
        playerEntity.setDirection(null);
        playerEntity.setCurrentRoomNumber(0);
        playerEntity.setFlashLightOn(false);
        return playerRepo.save(playerEntity);
    }

}