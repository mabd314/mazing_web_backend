import java.util.ArrayList;
import java.util.List;

public class Character {

  private final List<Item> items;
  private final Gold gold;
  private Direction direction;
  private boolean isFlashLightOn;

  public Character(Direction direction) {
    items = new ArrayList<>();
    this.direction = direction;
    gold = new Gold(0);
    isFlashLightOn = false;
  }

  public void addItem(Item item) {
    if (item.getType() != ItemType.GOLD) {
      items.add(item);
    } else {
      gold.merge((Gold) item);
    }
  }

  public void addItems(List<Item> items) {
    for (Item item : items) {
      addItem(item);
    }
  }

  public boolean hasItem(Item item) {
    return items.contains(item);
  }

  public boolean removeItem(Item item) {
    return items.remove(item);
  }

  public List<Item> getItems() {
    return items;
  }

  public Direction getDirection() {
    return direction;
  }

  public Gold getGold() {
    return gold;
  }

  public boolean isFlashLightOn() {
    return isFlashLightOn;
  }

  public void setFlashLightOn(boolean flashLightOn) {
    isFlashLightOn = flashLightOn;
  }

  public void turnLeft() {
    switch (direction) {
      case EAST:
        direction = Direction.NORTH;
        break;
      case WEST:
        direction = Direction.SOUTH;
        break;
      case NORTH:
        direction = Direction.WEST;
        break;
      case SOUTH:
        direction = Direction.EAST;
        break;
    }
  }

  public void turnRight() {
    switch (direction) {
      case EAST:
        direction = Direction.SOUTH;
        break;
      case WEST:
        direction = Direction.NORTH;
        break;
      case NORTH:
        direction = Direction.EAST;
        break;
      case SOUTH:
        direction = Direction.WEST;
        break;
    }
  }

  public Direction getOppositeDirection() {
    switch (getDirection()) {
      case EAST:
        return Direction.WEST;
      case WEST:
        return Direction.EAST;
      case NORTH:
        return Direction.SOUTH;
      default:
        return Direction.NORTH;
    }
  }


}
