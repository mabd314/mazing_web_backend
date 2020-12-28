import java.util.ArrayList;
import java.util.List;

public abstract class Wall {

  public abstract WallType getType();

  public Response getThrough(Game game) {
    return new Response(ResponseType.INVALID,
        "You can only move through doors");
  }

  public Response check(Game game) {
    return new Response(ResponseType.INVALID,
        "Cannot be checked");
  }

  public Response toggleWithKey(Key key) {
    return new Response(ResponseType.INVALID,
        "You can not use your key on " + this);
  }

  public List<Item> getTradingList() {
    return new ArrayList<>();
  }
}
