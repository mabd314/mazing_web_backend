package com.mazing.item;

import com.mazing.game.Game;
import com.mazing.game.Response;
import com.mazing.wall.Wall;
import java.util.Objects;

public class Key extends Item {

  private final int id;

  public Key(int id) {
    this.id = id;
  }

  @Override
  public ItemType getType() {
    return ItemType.KEY;
  }

  @Override
  public Response use(Game game) {
    Wall facing = game.getFacingWall();
    return facing.toggleWithKey(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Key key = (Key) o;
    return id == key.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return super.toString() + id;
  }
}
