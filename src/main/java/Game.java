import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Game {
  private static final Scanner scanner=new Scanner(System.in);

  private Character character;
  private int currentRoomId;
  private StopWatch stopWatch;
  private List<Room> rooms;

  public Room getRoomFromId(int id) {
    for (Room room : rooms) {
      if (room.getId() == id) {
        return room;
      }
    }
    return new End();
  }

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }
  public boolean isCurrentRoomLit(){
    return getCurrentRoom().isLit(this);
  }

  public String getFacingWallDescription(){
    return getFacingWall().getDescription();
  }

  public List<Item> getCharacterItems(){
    return character.getItems();
  }
  public Room getCurrentRoom() {
    return getRoomFromId(currentRoomId);
  }

  public Direction getDirection(){
    return character.getDirection();
  }

  public Direction getOppositeDirection(){
    return character.getOppositeDirection();
  }

  public List<Item> getFacingWallTradingList(){
    return getFacingWall().getTradingList();
  }

  public Gold getGold(){
    return getCharacter().getGold();
  }

  public WallType getFacingWallType(){
    return getFacingWall().getType();
  }


  public void setCurrentRoomId(int currentRoomId) {
    this.currentRoomId = currentRoomId;
  }

  public StopWatch getStopWatch() {
    return stopWatch;
  }

  public void setStopWatch(StopWatch stopWatch) {
    this.stopWatch = stopWatch;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

  private String chooseMap() {
    System.out.println("Choose Map (hard,medium,custom)");
    System.out.print("Map: ");
    String mapName= scanner.nextLine();
    return mapName;
  }

  public void initializeGame(){
    String mapName=chooseMap();
    try{
      Map map = new JsonMap("json/" + mapName + ".json");
      map.setUpRooms(this);
      map.setUpCharacter(this);
      stopWatch.start();
      MessagePrinter.printStartingMessage(this);
    }catch(IOException e){
      System.out.println("Map not recognized");
      initializeGame();
    }
  }

  public void start(){
    initializeGame();
    executeMainCommand();
  }

  public Wall getFacingWall() {
    return getCurrentRoom().getWall(getCharacter().getDirection());
  }

  public Response getThrough(Direction direction) {
    Wall wall = getCurrentRoom().getWall(direction);
    return wall.getThrough(this);
  }

  public void executeMainCommand() {
    System.out.print("$ ");
    String line = scanner.nextLine();
    String[] parts = line.split(" ");
    String commandString = parts[0];
    String arg = parts.length == 1 ? " " : parts[1];
    Command command=MainCommand.getCommand(commandString,arg);
    command.setGame(this);
    command.execute();
  }

  public void executeTradeCommand() {
    System.out.print(">> ");
    String line = scanner.nextLine();
    String[] parts = line.split(" ");
    String commandString = parts[0];
    String arg = parts.length == 1 ? " " : parts[1];
    Command command=TradingCommand.getCommand(commandString,arg);
    command.setGame(this);
    command.execute();
  }


}
