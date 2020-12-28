package com.mazing.game;

public class End extends Room {

  public End() {
    super(-1);
  }

  @Override
  public boolean checkWin() {
    return true;
  }
}
