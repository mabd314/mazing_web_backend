package com.mazing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    WallRepo wallRepo;
    @Autowired
    GameRepo gameRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    RoomRepo roomRepo;

    @Override
    public void run(String... args) {
        Repositories.playerRepo=playerRepo;
        Repositories.gameRepo=gameRepo;
        Repositories.roomRepo=roomRepo;
        Repositories.itemRepo=itemRepo;
        Repositories.wallRepo=wallRepo;
    }
}

