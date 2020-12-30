package com.mazing.map;

import com.mazing.game.Game;

public interface GameMap {

  default void setUp(Game game) {
    setUpRooms(game);
    setUpCharacter(game);
    setUpStopWatch(game);
  }

  void setUpRooms(Game game);

  void setUpCharacter(Game game);

  void setUpStopWatch(Game game);
}
