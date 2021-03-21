package com.mazing.entities;

public class Response {

  private final ResponseType type;

  private final String description;

  public Response(ResponseType type, String description) {
    this.type = type;
    this.description = description;
  }

  public ResponseType getType() {
    return type;
  }
  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return type + "\n" + description;
  }
}
