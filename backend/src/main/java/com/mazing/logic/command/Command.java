package com.mazing.logic.command;

import com.mazing.Response;
import com.mazing.logic.game.Game;
import com.mazing.logic.game.Player;

public interface Command {
  Game getGame();

  Player getPlayer();

  void setPlayer(Player player);

  Response getResponse();

  void setResponse(Response response);

  void execute();
}
