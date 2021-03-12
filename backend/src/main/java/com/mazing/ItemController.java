package com.mazing;
import org.springframework.web.bind.annotation.*;

import static com.mazing.Repositories.*;
import java.util.List;

@CrossOrigin()
@RestController
public class ItemController {

    @RequestMapping(value="/items",method = RequestMethod.GET)
    public List<ItemEntity> getPlayers(){
        return itemRepo.findAll();
    }

    @RequestMapping(value="/items/{itemId}",method = RequestMethod.GET)
    public ItemEntity getItems(@PathVariable int itemId){
        return itemRepo.findById(itemId).get();
    }
//
//    @RequestMapping(value="/items",method=RequestMethod.POST)
//    public ItemEntity postItem(@RequestBody ItemEntity itemEntity){
//        return itemRepo.save(itemEntity);
//    }

    @RequestMapping(value="/items",method=RequestMethod.POST)
    public List<ItemEntity> postItems(@RequestBody List<ItemEntity> itemEntities){
        return itemRepo.saveAll(itemEntities);
    }

    @RequestMapping(value="/items/{itemId}",method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable int itemId){
        itemRepo.deleteById(itemId);
    }
}



