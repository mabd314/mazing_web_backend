package com.mazing;
import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;
import java.util.List;

@CrossOrigin()
@RestController
public class RoomController {

    @RequestMapping(value="/rooms",method = RequestMethod.GET)
    public List<RoomEntity> getItems(){
        return roomRepo.findAll();
    }

    @RequestMapping(value="/rooms/{roomNumber}/{gameId}",method = RequestMethod.GET)
    public RoomEntity getRooms(@PathVariable int roomNumber,@PathVariable int gameId){
        return roomRepo.findById(new RoomId(roomNumber,gameId)).get();
    }

    @RequestMapping(value="/rooms",method=RequestMethod.POST)
    public RoomEntity postRoom(@RequestBody RoomEntity roomEntity){
        return roomRepo.save(roomEntity);
    }

    @RequestMapping(value="/rooms/list",method=RequestMethod.POST)
    public List<RoomEntity> postRooms(@RequestBody List<RoomEntity> roomEntities){
        return roomRepo.saveAll(roomEntities);
    }

    @RequestMapping(value="/rooms/{roomNumber}/{gameId}",method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable int roomNumber,@PathVariable int gameId){
        roomRepo.deleteById(new RoomId(roomNumber,gameId));
    }
}



