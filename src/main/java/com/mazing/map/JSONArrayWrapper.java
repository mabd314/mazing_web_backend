package com.mazing.map;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONArrayWrapper {
  private JSONArray jsonArray;
  private List<JSONObjectWrapper> jsonList;

  public JSONArrayWrapper(JSONArray jsonArray) {
    this.jsonArray = jsonArray;
    jsonList=new ArrayList<>();
    for(Object obj:jsonArray){
      JSONObjectWrapper jsonObject=new JSONObjectWrapper((JSONObject)obj);
      jsonList.add(jsonObject);
    }
  }

  public List<JSONObjectWrapper> getList(){
    return jsonList;
  }


}
