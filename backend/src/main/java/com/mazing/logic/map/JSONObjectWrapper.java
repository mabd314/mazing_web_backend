package com.mazing.logic.map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONObjectWrapper {
  private final JSONObject jsonObject;

  public JSONObjectWrapper(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

  public int getInt(String key) {
    try {
      return jsonObject.get(key) == null ? 0 : (int) (long) jsonObject.get(key);
    } catch (ClassCastException e) {
      System.out.println("The object at key " + key + " can not be casted to an int");
      throw e;
    }
  }

  public JSONObjectWrapper getJsonObject(String key) {
    try {
      return jsonObject.get(key) == null
          ? new JSONObjectWrapper(new JSONObject())
          : new JSONObjectWrapper((JSONObject) jsonObject.get(key));
    } catch (ClassCastException e) {
      System.out.println("The object at key " + key + " can not be casted to a JSONObject");
      throw e;
    }
  }

  public Boolean getBoolean(String key) {
    try {
      return jsonObject.get(key) != null && (Boolean) jsonObject.get(key);
    } catch (ClassCastException e) {
      System.out.println("The object at key " + key + " can not be casted to a boolean");
      throw e;
    }
  }

  public JSONArrayWrapper getJsonArray(String key) {
    try {
      return jsonObject.get(key) == null
          ? new JSONArrayWrapper(new JSONArray())
          : new JSONArrayWrapper((JSONArray) jsonObject.get(key));
    } catch (ClassCastException e) {
      System.out.println("The object at key " + key + " can not be casted to a JSONArray");
      throw e;
    }
  }

  public String getString(String key) {
    try {
      return jsonObject.get(key) == null ? "" : (String) jsonObject.get(key);
    } catch (ClassCastException e) {
      System.out.println("The object at key " + key + " can not be casted to a String");
      throw e;
    }
  }
}
