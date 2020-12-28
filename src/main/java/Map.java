import java.io.FileNotFoundException;

public interface Map {

  void setUpRooms(Game game) throws FileNotFoundException;

  void setUpCharacter(Game game);

}
