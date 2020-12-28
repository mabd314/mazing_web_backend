package com.mazing.game;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.command.*;
import com.mazing.wall.*;

public class Response {

  private final ResponseType type;
  private final String description;

  public Response(ResponseType type,
      String description) {
    this.type = type;
    this.description = description;
  }

  public ResponseType getType() {
    return type;
  }

  @Override
  public String toString() {
    return type + "\n" + description;
  }
}

