import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandLine {

  private final static Scanner scanner = new Scanner(System.in);

  private static void left() {
    System.out.println(Commands.left());
    mainMenu();
  }

  private static void right() {
    System.out.println(Commands.right());
    mainMenu();
  }

  private static void forward() {
    System.out.println(Commands.forward());
    mainMenu();
  }

  private static void backward() {
    System.out.println(Commands.backward());
    mainMenu();
  }

  private static void playerstatus() {
    System.out.println(Commands.playerstatus());
    mainMenu();
  }

  private static void look() {
    System.out.println(Commands.look());
    mainMenu();
  }

  private static void check(String wallName) {
      if (wallName.equals(" ")) {
          System.out.println("wrong command");
      } else {
          System.out.println(Commands.check(wallName));
      }
    mainMenu();
  }

  private static void list() {
    System.out.println(Commands.printTradingList());
    tradeMenu();
  }

  private static void trade() {
    Response list = Commands.printTradingList();
    System.out.println(list);
      if (list.getType() != ResponseType.INVALID) {
          tradeMenu();
      }
    mainMenu();
  }

  private static void wrongMainCommand() {
    System.out.println("command not recognized");
    mainMenu();
  }

  private static void wrongTradeCommand() {
    System.out.println("command not recognized");
    tradeMenu();
  }

  private static void tradeMenu() {
    System.out.print(">> ");
    String line = scanner.nextLine();
    String[] parts = line.split(" ");
    String command = parts[0];
    String arg = parts.length == 1 ? " " : parts[1];
    switch (command.toLowerCase()) {
      case "buy":
        buy(arg);
        break;
      case "sell":
        sell(arg);
        break;
      case "list":
        list();
        break;
      case "end":
          if (!arg.toLowerCase().equals("trade")) {
              wrongTradeCommand();
          }
        break;
      case "help":
        tradeHelp();
        break;
      default:
        wrongTradeCommand();
    }
  }

  private static void tradeHelp() {
    System.out.println("Trading Commands:");
    System.out.println("  buy <item> : buy a certain item");
    System.out.println("  sell <item> : sell a certain item");
    System.out.println("  list : list seller items again");
    System.out.println("  end trade : get out of trading mode");
    tradeMenu();

  }

  private static void buy(String itemName) {
    System.out.println(Commands.buyItem(itemName));
    tradeMenu();
  }

  private static void sell(String itemName) {
    System.out.println(Commands.sellItem(itemName));
    tradeMenu();
  }


  private static void use(String itemName) {
    System.out.println(Commands.useItem(itemName));
    mainMenu();
  }

  private static void switchLights() {
    System.out.println(Commands.switchLights());
    mainMenu();
  }

  private static void quit() {
    System.out.println(Commands.quit());
    System.exit(0);
  }

  private static void restart() {
    System.out.println(Commands.restart());
    main(new String[0]);
  }

  private static void checkTime() {
    System.out.println(Commands.checkTime());
    mainMenu();
  }

  private static void chooseMap() {
    System.out.print("Map: ");
    String mapName = scanner.nextLine();

    try {
      Commands.start(mapName);
      System.out.println("You chose: " + mapName + "\n");
      System.out.println(Commands.startCounting());
      mainMenu();
    } catch (FileNotFoundException e) {
      System.out.println("Map not recognized, please choose another map\n");
      chooseMap();
    }
  }

  private static void help() {
    System.out.println("Navigation:\n  left\n  right\n  forward\n  backward\n");
    System.out.println("To check your status(Orientation,gold and items):\n  playerstatus\n");
    System.out.println("To get how much time you have left:\n  time\n");
    System.out.println("To close or restart the game:\n  quit\n  restart\n");
    System.out.println("To know what is in front of you:\n  look\n");
    System.out.println(
        "To check the object you are facing:\n  check <wallName>(door,chest,painting,mirror)\n-Checking a door will return its state(locked/unlocked) and what key is needed if it is locked\n-Checking a chest will loot items inside of it if it is unlocked,and what key is needed if it is locked\n-Checking a mirror/painting will acquire the key behind if there is one\n");
    System.out.println(
        "To use an item you have:\n  use <item>(keyN,flashlight)\n-Use flashlight to switch it on and off\n-Use keyN in front of the right door/chest to lock/unlock it\n");
    System.out.println(
        "To switch lights on and off, when there are lights in the room:\n  switchlights\n");
    System.out.println(
        "To initiate trading with a seller, when there is a seller in front of you:\n  trade\n");
    System.out.println(
        "Trading Commands, used in trading mode, after trade command:\n  buy <item>\n  sell <item>\n  list\n  end trade\n");
    mainMenu();
  }

  private static void mainMenu() {
    System.out.print("$ ");
    String line = scanner.nextLine();
    String[] parts = line.split(" ");
    String command = parts[0];
    String arg = parts.length == 1 ? " " : parts[1];
    switch (command.toLowerCase()) {
      case "left":
        left();
        break;
      case "right":
        right();
        break;
      case "forward":
        forward();
        break;
      case "backward":
        backward();
        break;
      case "playerstatus":
        playerstatus();
        break;
      case "look":
        look();
        break;
      case "check":
        check(arg);
        break;
      case "trade":
        trade();
        break;
      case "use":
        use(arg);
        break;
      case "switchlights":
        switchLights();
        break;
      case "quit":
        quit();
        break;
      case "restart":
        restart();
        break;
      case "time":
        checkTime();
        break;
      case "help":
        help();
        break;
      default:
        wrongMainCommand();
    }
  }


  public static void main(String[] args) {
    System.out.println("Choose Map (hard,medium,custom)");
    chooseMap();
  }
}
