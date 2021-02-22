package com.mazing.game;

public class Starter {
  public static void start() {
    Game game = new Game();
    Console.initializeGame(game);
  }

  public static void main(String[] args) {
    start();
  }
}
