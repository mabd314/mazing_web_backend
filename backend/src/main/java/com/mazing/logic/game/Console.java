package com.mazing.logic.game;

import com.mazing.logic.command.Command;
import com.mazing.logic.command.MainCommand;
import com.mazing.logic.command.TradingCommand;
import com.mazing.logic.map.GameMap;
import com.mazing.logic.map.JsonGameMap;

import java.io.IOException;
import java.util.Scanner;

public class Console {
  private static final Scanner scanner = new Scanner(System.in);

  public Console() {}

  public static void initializeGame(Game game) {
    String mapName = chooseMap();
    setUpMap(mapName, game);
    setUpGame(game);
    executeMainCommand(game);
  }

  private static String chooseMap() {
    System.out.println("Choose Map (hard,medium,custom)");
    System.out.print("Map: ");
    return scanner.nextLine();
  }

  private static void setUpMap(String mapName, Game game) {
    try {
      GameMap map = new JsonGameMap("json/" + mapName + ".json");
      map.setUp(game);
    } catch (IOException e) {
      System.out.println("Map not Recognized");
      initializeGame(game);
    }
  }

  private static void setUpGame(Game game) {
    game.getStopWatch().startCounting();
    game.setWon(false);
    game.setCurrentRoomId(1);
    MessagePrinter.printStartingMessage(game);
  }

  public static void executeMainCommand(Game game) {
    System.out.print("$ ");
    String query = scanner.nextLine();
    String commandString = getCommandStringFromQuery(query);
    String arg = getArgFromQuery(query);
    Command command = MainCommand.getCommand(commandString, arg);
    command.setGame(game);
    command.execute();
  }

  public static void executeTradingCommand(Game game) {
    System.out.print(">> ");
    String line = scanner.nextLine();
    String[] parts = line.split(" ");
    String commandString = parts[0];
    String arg = parts.length == 1 ? " " : parts[1];
    Command command = TradingCommand.getCommand(commandString, arg);
    command.setGame(game);
    command.execute();
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
