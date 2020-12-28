package com.mazing.game;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.command.*;
import com.mazing.wall.*;

public enum ResponseType {
  FAILURE,
  SUCCESS,
  INVALID,
  LOCKED,
  EMPTY,
  UNLOCKED,
  WON,
  STATUS,
  LOST,
}
