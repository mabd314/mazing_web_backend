package com.mazing.game;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.command.*;
import com.mazing.wall.*;

public class Starter {
  public static void start(){
    Game game = new Game();
    game.start();
  }

  public static void main(String[] args){
    start();
  }
}
