package com.mazing;
import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;
import java.util.List;

@CrossOrigin()
@RestController
public class WallController {

    @RequestMapping(value="/walls",method = RequestMethod.GET)
    public List<WallEntity> getItems(){
        return wallRepo.findAll();
    }

    @RequestMapping(value="/walls/{wallId}",method = RequestMethod.GET)
    public WallEntity getWalls(@PathVariable int wallId){
        return wallRepo.findById(wallId).get();
    }

    @RequestMapping(value="/walls",method=RequestMethod.POST)
    public WallEntity postWall(@RequestBody WallEntity wallEntity){
        return wallRepo.save(wallEntity);
    }

    @RequestMapping(value="/walls/list",method=RequestMethod.POST)
    public List<WallEntity> postWalls(@RequestBody List<WallEntity> wallEntities){
        return wallRepo.saveAll(wallEntities);
    }

    @RequestMapping(value="/walls/{wallId}",method = RequestMethod.DELETE)
    public void deleteWall(@PathVariable int wallId){
        wallRepo.deleteById(wallId);
    }
}



