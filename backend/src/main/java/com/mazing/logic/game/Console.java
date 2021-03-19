package com.mazing.logic.game;

import com.mazing.entities.Response;
import com.mazing.logic.command.Command;
import com.mazing.logic.command.MainCommand;
import com.mazing.logic.command.TradingCommand;

public class Console {

  public static Response executeMainCommand(Player player, String query) {
    String commandString = getCommandStringFromQuery(query);
    String arg = getArgFromQuery(query);
    Command command = MainCommand.getCommand(commandString, arg);
    command.setPlayer(player);
    command.execute();
    return command.getResponse();
  }

  public static Response executeTradingCommand(Player player, String query) {
    String commandString = getCommandStringFromQuery(query);
    String arg=getArgFromQuery(query);
    Command command = TradingCommand.getCommand(commandString, arg);
    command.setPlayer(player);
    command.execute();
    return command.getResponse();
  }

  private static String getCommandStringFromQuery(String query) {
    String[] parts = query.split(" ");
    return parts[0];
  }

  private static String getArgFromQuery(String query) {
    String[] parts = query.split(" ");
    return parts.length == 1 ? " " : parts[1];
  }
}
