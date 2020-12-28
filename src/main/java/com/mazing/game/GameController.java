package com.mazing.game;

import com.mazing.command.Command;
import com.mazing.command.MainCommand;
import com.mazing.command.TradingCommand;
import com.mazing.map.JsonMaze;
import com.mazing.map.Maze;
import java.io.IOException;
import java.util.Scanner;

public class GameController {
  private static final Scanner scanner = new Scanner(System.in);

  public GameController(){

  }
  public static void initializeGame(Game game) {
    String mapName = chooseMap();
    try {
      Maze maze = new JsonMaze("json/" + mapName + ".json");
      maze.setUpRooms(game);
      maze.setUpCharacter(game);
      game.getStopWatch().startCounting();
      game.getStopWatch().start();
      MessagePrinter.printStartingMessage(game);
    } catch (IOException e) {
      System.out.println("Map not Recognized");
      initializeGame(game);
    }
  }

  private static String chooseMap() {
    System.out.println("Choose Map (hard,medium,custom)");
    System.out.print("Map: ");
    return scanner.nextLine();
  }

  public static void executeMainCommand(Game game) {
    System.out.print("$ ");
    String line = scanner.nextLine();
    String[] parts = line.split(" ");
    String commandString = parts[0];
    String arg = parts.length == 1 ? " " : parts[1];
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

}
