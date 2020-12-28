public class CheckCommand extends MainCommand {
  String wallName;

  public CheckCommand(String wallName) {
    this.wallName=wallName;
  }

  @Override
  public void applyEffect() {
    Wall wall = getGame().getFacingWall();
    WallType type = WallType.getWallType(wallName);
    if (type == WallType.NONE) {
      setResponse(new Response(ResponseType.INVALID,
          "There is no " + wallName + " in this game"));
    }
    else if (type != wall.getType()) {
      setResponse(new Response(ResponseType.INVALID,
          "There is no " + wallName + " in front of you"));
    }
    else{
      setResponse(wall.check(getGame()));
    }
  }
}
