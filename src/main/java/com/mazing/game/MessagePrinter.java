package com.mazing.game;

public class MessagePrinter {
  public static void printTradingHelp() {
    System.out.println("Trading Commands:");
    System.out.println("  buy <item> : buy a certain item");
    System.out.println("  sell <item> : sell a certain item");
    System.out.println("  list : list seller items again");
    System.out.println("  end trade : get out of trading mode");
  }

  public static void printMainHelp() {
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
  }

  public static void printStartingMessage(Game game){
    System.out.println("You have " + game.getRemainingSecondsString()
        + " minutes to get out of the map\n" +
        "Type help at any time go get the list of commands\n" +
        game.getStopWatch().checkTime());
  }

  public static void printTimeElapsedMessage() {
    System.out.println(new Response(ResponseType.LOST,
        "Time elapsed. You could not get out in time. YOU LOSE!\nTry again by starting the game."));
  }
}
