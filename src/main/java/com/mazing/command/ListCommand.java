package com.mazing.command;

import java.util.List;
import com.mazing.item.*;
import com.mazing.map.*;
import com.mazing.game.*;
import com.mazing.wall.*;

public class ListCommand extends TradingCommand {


  @Override
  public void applyEffect() {
    if (getGame().getFacingWallType() != WallType.SELLER) {
      setResponse(new Response(ResponseType.INVALID,
          "You can only trade with a seller"));
    }
    else{
      List<Item> items = getGame().getFacingWallTradingList();
      StringBuilder list = new StringBuilder();
      list.append("com.mazing.wall.Seller Items:\n");
      for (Item item : items) {
        list.append(item + " : " + item.getPrice() + " com.mazing.item.Gold\n");
      }
      setResponse(new Response(ResponseType.STATUS,
          list.toString()));
    }
  }

}
