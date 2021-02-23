package com.mazing.logic.map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONArrayWrapper {
  private final JSONArray jsonArray;
  private final List<JSONObjectWrapper> jsonList;

  public JSONArrayWrapper(JSONArray jsonArray) {
    this.jsonArray = jsonArray;
    jsonList = new ArrayList<>();
    for (Object obj : jsonArray) {
      JSONObjectWrapper jsonObject = new JSONObjectWrapper((JSONObject) obj);
      jsonList.add(jsonObject);
    }
  }

  public List<JSONObjectWrapper> getList() {
    return jsonList;
  }
}
