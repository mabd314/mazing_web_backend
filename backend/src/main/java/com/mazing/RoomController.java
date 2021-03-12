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

    @RequestMapping(value="/rooms/{roomId}",method = RequestMethod.GET)
    public RoomEntity getRooms(@PathVariable int roomId){
        return roomRepo.findById(roomId).get();
    }

//    @RequestMapping(value="/rooms",method=RequestMethod.POST)
//    public RoomEntity postRoom(@RequestBody RoomEntity roomEntity){
//        return roomRepo.save(roomEntity);
//    }

    @RequestMapping(value="/rooms",method=RequestMethod.POST)
    public List<RoomEntity> postRooms(@RequestBody List<RoomEntity> roomEntities){
        return roomRepo.saveAll(roomEntities);
    }

    @RequestMapping(value="/rooms/{roomId}",method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable int roomId){
        roomRepo.deleteById(roomId);
    }
}



