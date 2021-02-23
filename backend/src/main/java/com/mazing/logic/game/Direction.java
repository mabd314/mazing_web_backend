package com.mazing.logic.game;

public enum Direction {
  EAST,
  WEST,
  NORTH,
  SOUTH;

  public Direction rightDirection(){
    switch (this){
      case EAST ->{return Direction.SOUTH;}
      case WEST -> {return Direction.NORTH;}
      case NORTH -> {return Direction.EAST;}
      default -> {return Direction.WEST;}
    }
  }
  public Direction leftDirection(){
    switch (this){
      case EAST ->{return Direction.NORTH;}
      case WEST -> {return Direction.SOUTH;}
      case NORTH -> {return Direction.WEST;}
      default -> {return Direction.EAST;}
    }
  }
  public Direction oppositeDirection(){
    switch (this){
      case EAST ->{return Direction.WEST;}
      case WEST -> {return Direction.EAST;}
      case NORTH -> {return Direction.SOUTH;}
      default -> {return Direction.NORTH;}
    }
  }

}
