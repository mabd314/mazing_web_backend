public enum WallType {
  CHEST("You see a CHEST"),
  DOOR("You see a DOOR"),
  EMPTY("You see an EMPTY WALL"),
  MIRROR("You See YOURSELF"),
  PAINTING("You see a PAINTING"),
  SELLER("You see a SELLER"),
  NONE("None");

  private final String description;

  WallType(String description) {
    this.description = description;
  }

  public static WallType getWallType(String wallName) {
      if (wallName.toLowerCase().equals("wall")) {
          wallName = "empty";
      }
      for (WallType type : WallType.values()) {
          if (wallName.toUpperCase().equals(type.toString())) {
              return type;
          }
      }
    return WallType.NONE;
  }

  public String getDescription() {
    return description;
  }
}
