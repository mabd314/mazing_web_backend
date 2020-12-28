import java.util.List;

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
      list.append("Seller Items:\n");
      for (Item item : items) {
        list.append(item + " : " + item.getPrice() + " Gold\n");
      }
      setResponse(new Response(ResponseType.STATUS,
          list.toString()));
    }
  }

}
