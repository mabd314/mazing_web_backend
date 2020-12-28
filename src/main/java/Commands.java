import java.io.FileNotFoundException;
import java.util.List;

public class Commands {

  private static Game game;

  public static void start(String mapName) throws FileNotFoundException {
    game = new Game();
    game.start(mapName);
  }

  public static Response left() {
    game.getCharacter().turnLeft();
    return new Response(ResponseType.SUCCESS,
        "You turned left");
  }

  public static Response right() {
    game.getCharacter().turnRight();
    return new Response(ResponseType.SUCCESS,
        "You turned right");
  }

  public static Response forward() {
    return game.getThrough(game.getCharacter().getDirection());
  }

  public static Response backward() {
    return game.getThrough(game.getCharacter().getOppositeDirection());
  }

  public static Response playerstatus() {
    return new Response(ResponseType.STATUS,
        "Player is facing: " + game.getCharacter().getDirection() + "\n" +
            game.getCharacter().getGold() + "\n" +
            "Items: " + game.getCharacter().getItems());
  }

  public static Response look() {
      if (game.getCurrentRoom().isLit(game)) {
          return new Response(ResponseType.STATUS,
              game.getFacingWall().getType().getDescription());
      }
    return new Response(ResponseType.FAILURE,
        "The room is dark, you can not see anything");
  }

  public static Response check(String wallName) {
    Wall wall = game.getFacingWall();
    WallType type = WallType.getWallType(wallName);
      if (type == WallType.NONE) {
          return new Response(ResponseType.INVALID,
              "There is no " + wallName + " in this game");
      }
      if (type != wall.getType()) {
          return new Response(ResponseType.INVALID,
              "There is no " + wallName + " in front of you");
      }
    return wall.check(game);
  }

  public static Response printTradingList() {
      if (game.getFacingWall().getType() != WallType.SELLER) {
          return new Response(ResponseType.INVALID,
              "You can only trade with a seller");
      }
    List<Item> items = game.getFacingWall().getTradingList();
    StringBuilder list = new StringBuilder();
    list.append("Seller Items:\n");
      for (Item item : items) {
          list.append(item + " : " + item.getPrice() + " Gold\n");
      }
    return new Response(ResponseType.STATUS,
        list.toString());
  }

  public static Response buyItem(String itemName) {
    Item item = Item.getItemFromList(itemName, game.getFacingWall().getTradingList());
      if (item == NoItem.getInstance()) {
          return new Response(ResponseType.INVALID,
              "The seller does not have what you desire");
      }
      if (!game.getCharacter().getGold().payForItem(item)) {
          return new Response(ResponseType.FAILURE,
              "You do not have enough gold to buy this item");
      }
    game.getCharacter().addItem(item);
    return new Response(ResponseType.SUCCESS,
        "You bought an item: " + item);
  }

  public static Response sellItem(String itemName) {
    Item item = Item.getItemFromList(itemName, game.getFacingWall().getTradingList());
      if (item == NoItem.getInstance()) {
          return new Response(ResponseType.INVALID,
              "The seller does not want to buy this item");
      }
      if (!game.getCharacter().removeItem(item)) {
          return new Response(ResponseType.INVALID,
              "How come you want to sell an item that you do not have?");
      }
    game.getCharacter().getGold().getPaidForItem(item);
    return new Response(ResponseType.SUCCESS,
        "You Sold an item: " + item + " for " + item.getPrice() + " gold");
  }

  public static Response useItem(String itemName) {
    Item item = Item.getItemFromList(itemName, game.getCharacter().getItems());
      if (item == NoItem.getInstance()) {
          return new Response(ResponseType.INVALID,
              "How come you want to use an item that you do not have?");
      }
    return item.use(game);
  }

  public static Response switchLights() {
      if (!game.getCurrentRoom().isThereLight()) {
          return new Response(ResponseType.FAILURE,
              "There are no lights in this room");
      }
    game.getCurrentRoom().toggleLight();
    return new Response(ResponseType.SUCCESS,
        "Lights are " +
            (game.getCurrentRoom().isLightOn() ? "ON" : "OFF"));
  }

  public static Response checkTime() {
    return new Response(ResponseType.STATUS,
        game.getStopWatch().checkTime());
  }

  public static Response startCounting() {
    game.getStopWatch().startCounting();
    game.getStopWatch().start();
    return new Response(ResponseType.STATUS,
        "You have " + game.getStopWatch().getTimeString(game.getStopWatch().getRemainingSeconds())
            + " minutes to get out of the map\n" +
            "Type help at any time go get the list of commands\n" +
            game.getStopWatch().checkTime());
  }

  public static Response quit() {
    return new Response(ResponseType.LOST,
        "You quit the game. YOU LOSE.");
  }

  public static Response restart() {
    game.getStopWatch().stopTimer();
    Door.clear();
    return new Response(ResponseType.LOST,
        "You restarted the game. YOU LOSE.");
  }

  public static Response timeElapsed() {
    return new Response(ResponseType.LOST,
        "Time elapsed. You could not get out in time. YOU LOSE!\nTry again by starting the game.");
  }


}