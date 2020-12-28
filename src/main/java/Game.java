import java.io.FileNotFoundException;
import java.util.List;

public class Game {

  private Character character;
  private int currentRoomId;
  private StopWatch stopWatch;
  List<Room> rooms;

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


  public Room getCurrentRoom() {
    return getRoomFromId(currentRoomId);
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

  public void start(String mapName) throws FileNotFoundException {
    Map map = new JsonMap("json/" + mapName + ".json");
    map.setUpRooms(this);
    map.setUpCharacter(this);
  }

  public Wall getFacingWall() {
    return getCurrentRoom().getWall(getCharacter().getDirection());
  }

  public Response getThrough(Direction direction) {
    Wall wall = getCurrentRoom().getWall(direction);
    return wall.getThrough(this);
  }

}
