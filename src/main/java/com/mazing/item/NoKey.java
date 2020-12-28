package com.mazing.item;
import com.mazing.game.*;
import com.mazing.map.*;
import com.mazing.command.*;
import com.mazing.wall.*;

public class NoKey extends Key {

  private static NoKey instance;

  private NoKey() {
    super(-1);
  }

  public static synchronized NoKey getInstance() {
    if (instance == null) {
      instance = new NoKey();
    }
    return instance;
  }
}